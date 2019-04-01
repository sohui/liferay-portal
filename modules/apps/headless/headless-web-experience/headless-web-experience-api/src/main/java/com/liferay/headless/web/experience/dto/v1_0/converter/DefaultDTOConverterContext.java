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

package com.liferay.headless.web.experience.dto.v1_0.converter;

import java.util.Locale;
import java.util.Optional;

import javax.ws.rs.core.UriInfo;

/**
 * @author Rubén Pulido
 * @author Víctor Galán
 */
public class DefaultDTOConverterContext implements DTOConverterContext {

	public DefaultDTOConverterContext(
		Locale locale, long resourcePrimKey) {

		_locale = locale;
		_resourcePrimKey = resourcePrimKey;
	}

	public DefaultDTOConverterContext(
		Locale locale, long resourcePrimKey, UriInfo uriInfo) {

		_locale = locale;
		_resourcePrimKey = resourcePrimKey;
		_uriInfo = uriInfo;
	}

	@Override
	public Locale getLocale() {
		return _locale;
	}

	@Override
	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	@Override
	public Optional<UriInfo> getUriInfoOptional() {
		return Optional.ofNullable(_uriInfo);
	}

	private final Locale _locale;
	private final long _resourcePrimKey;
	private UriInfo _uriInfo;

}