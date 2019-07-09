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

package com.liferay.change.tracking.rest.client.serdes.v1_0;

import com.liferay.change.tracking.rest.client.dto.v1_0.Entry;
import com.liferay.change.tracking.rest.client.dto.v1_0.SettingsUpdate;
import com.liferay.change.tracking.rest.client.json.BaseJSONParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

/**
 * @author Máté Thurzó
 * @generated
 */
@Generated("")
public class SettingsUpdateSerDes {

	public static SettingsUpdate toDTO(String json) {
		SettingsUpdateJSONParser settingsUpdateJSONParser =
			new SettingsUpdateJSONParser();

		return settingsUpdateJSONParser.parseToDTO(json);
	}

	public static SettingsUpdate[] toDTOs(String json) {
		SettingsUpdateJSONParser settingsUpdateJSONParser =
			new SettingsUpdateJSONParser();

		return settingsUpdateJSONParser.parseToDTOs(json);
	}

	public static String toJSON(SettingsUpdate settingsUpdate) {
		if (settingsUpdate == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (settingsUpdate.getChangeTrackingEnabled() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"changeTrackingEnabled\": ");

			sb.append(settingsUpdate.getChangeTrackingEnabled());
		}

		if (settingsUpdate.getCheckoutCTCollectionConfirmationEnabled() !=
				null) {

			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"checkoutCTCollectionConfirmationEnabled\": ");

			sb.append(
				settingsUpdate.getCheckoutCTCollectionConfirmationEnabled());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SettingsUpdateJSONParser settingsUpdateJSONParser =
			new SettingsUpdateJSONParser();

		return settingsUpdateJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(SettingsUpdate settingsUpdate) {
		if (settingsUpdate == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		if (settingsUpdate.getChangeTrackingEnabled() == null) {
			map.put("changeTrackingEnabled", null);
		}
		else {
			map.put(
				"changeTrackingEnabled",
				String.valueOf(settingsUpdate.getChangeTrackingEnabled()));
		}

		if (settingsUpdate.getCheckoutCTCollectionConfirmationEnabled() ==
				null) {

			map.put("checkoutCTCollectionConfirmationEnabled", null);
		}
		else {
			map.put(
				"checkoutCTCollectionConfirmationEnabled",
				String.valueOf(
					settingsUpdate.
						getCheckoutCTCollectionConfirmationEnabled()));
		}

		return map;
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		string = string.replace("\\", "\\\\");

		return string.replace("\"", "\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static class SettingsUpdateJSONParser
		extends BaseJSONParser<SettingsUpdate> {

		@Override
		protected SettingsUpdate createDTO() {
			return new SettingsUpdate();
		}

		@Override
		protected SettingsUpdate[] createDTOArray(int size) {
			return new SettingsUpdate[size];
		}

		@Override
		protected void setField(
			SettingsUpdate settingsUpdate, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "changeTrackingEnabled")) {
				if (jsonParserFieldValue != null) {
					settingsUpdate.setChangeTrackingEnabled(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName,
						"checkoutCTCollectionConfirmationEnabled")) {

				if (jsonParserFieldValue != null) {
					settingsUpdate.setCheckoutCTCollectionConfirmationEnabled(
						(Boolean)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}