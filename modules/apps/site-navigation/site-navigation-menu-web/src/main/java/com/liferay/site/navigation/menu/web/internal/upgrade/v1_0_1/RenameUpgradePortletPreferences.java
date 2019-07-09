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

package com.liferay.site.navigation.menu.web.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.site.navigation.menu.web.internal.constants.SiteNavigationMenuPortletKeys;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Balázs Sáfrány-Kovalik
 */
public class RenameUpgradePortletPreferences
	extends com.liferay.portal.kernel.upgrade.RenameUpgradePortletPreferences {

	public RenameUpgradePortletPreferences() {
		_preferenceNamesMap.put("includedLayouts", "expandedLevels");
		_preferenceNamesMap.put("rootLayoutLevel", "rootMenuItemLevel");
		_preferenceNamesMap.put("rootLayoutType", "rootMenuItemType");
	}

	@Override
	protected String[] getPortletIds() {
		return new String[] {
			SiteNavigationMenuPortletKeys.SITE_NAVIGATION_MENU + "%"
		};
	}

	@Override
	protected Map<String, String> getPreferenceNamesMap() {
		return _preferenceNamesMap;
	}

	@Override
	protected void updatePortletPreferences() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			String whereClause = getUpdatePortletPreferencesWhereClause();

			updatePortletPreferencesWithOwnerType(
				PortletKeys.PREFS_OWNER_TYPE_ARCHIVED, whereClause, "ownerId",
				"PortletItem", "portletItemId");

			updatePortletPreferencesWithOwnerType(
				PortletKeys.PREFS_OWNER_TYPE_COMPANY, whereClause, "ownerId",
				"Company", "companyId");

			updatePortletPreferencesWithOwnerType(
				PortletKeys.PREFS_OWNER_TYPE_GROUP, whereClause, "ownerId",
				"Group_", "groupId");

			updatePortletPreferencesWithOwnerType(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, whereClause, "plid",
				"Layout", "plid");

			updatePortletPreferencesWithOwnerType(
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, whereClause, "plid",
				"LayoutRevision", "layoutRevisionId");

			updatePortletPreferencesWithOwnerType(
				PortletKeys.PREFS_OWNER_TYPE_ORGANIZATION, whereClause,
				"ownerId", "Organization_", "organizationId");

			updatePortletPreferencesWithOwnerType(
				PortletKeys.PREFS_OWNER_TYPE_USER, whereClause, "ownerId",
				"User_", "userId");
		}
	}

	private final Map<String, String> _preferenceNamesMap = new HashMap<>();

}