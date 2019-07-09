/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.layout.seo.internal;

import com.liferay.layout.seo.internal.configuration.SEOCompanyConfiguration;
import com.liferay.layout.seo.kernel.SEOLink;
import com.liferay.layout.seo.kernel.SEOLinkManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(service = SEOLinkManager.class)
@ProviderType
public class SEOLinkManagerImpl implements SEOLinkManager {

	@Override
	public List<SEOLink> getLocalizedSEOLinks(
			long companyId, Locale locale, String canonicalURL,
			Map<Locale, String> alternateURLs)
		throws PortalException {

		List<SEOLink> seoLinks = new ArrayList<>(alternateURLs.size() + 2);

		seoLinks.add(
			new SEOLinkImpl(
				_html.escapeAttribute(
					_getCanonicalURL(
						companyId, locale, canonicalURL, alternateURLs)),
				null, SEOLink.Relationship.CANONICAL));

		alternateURLs.forEach(
			(urlLocale, url) -> seoLinks.add(
				new SEOLinkImpl(
					_html.escapeAttribute(url),
					LocaleUtil.toW3cLanguageId(urlLocale),
					SEOLink.Relationship.ALTERNATE)));

		String defaultLocaleURL = alternateURLs.get(LocaleUtil.getDefault());

		if (defaultLocaleURL == null) {
			return seoLinks;
		}

		seoLinks.add(
			new SEOLinkImpl(
				_html.escapeAttribute(defaultLocaleURL), "x-default",
				SEOLink.Relationship.ALTERNATE));

		return seoLinks;
	}

	private String _getCanonicalURL(
			long companyId, Locale locale, String canonicalURL,
			Map<Locale, String> alternateURLs)
		throws ConfigurationException {

		SEOCompanyConfiguration seoCompanyConfiguration =
			_configurationProvider.getCompanyConfiguration(
				SEOCompanyConfiguration.class, companyId);

		if (Objects.equals(
				seoCompanyConfiguration.configuration(), "classic")) {

			return canonicalURL;
		}

		return alternateURLs.getOrDefault(locale, canonicalURL);
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Html _html;

}