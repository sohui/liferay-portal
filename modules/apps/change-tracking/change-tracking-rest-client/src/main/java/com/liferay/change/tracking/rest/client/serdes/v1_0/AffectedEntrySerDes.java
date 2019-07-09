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

import com.liferay.change.tracking.rest.client.dto.v1_0.AffectedEntry;
import com.liferay.change.tracking.rest.client.dto.v1_0.Entry;
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
public class AffectedEntrySerDes {

	public static AffectedEntry toDTO(String json) {
		AffectedEntryJSONParser affectedEntryJSONParser =
			new AffectedEntryJSONParser();

		return affectedEntryJSONParser.parseToDTO(json);
	}

	public static AffectedEntry[] toDTOs(String json) {
		AffectedEntryJSONParser affectedEntryJSONParser =
			new AffectedEntryJSONParser();

		return affectedEntryJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AffectedEntry affectedEntry) {
		if (affectedEntry == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (affectedEntry.getContentType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentType\": ");

			sb.append("\"");

			sb.append(_escape(affectedEntry.getContentType()));

			sb.append("\"");
		}

		if (affectedEntry.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append("\"");

			sb.append(_escape(affectedEntry.getTitle()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AffectedEntryJSONParser affectedEntryJSONParser =
			new AffectedEntryJSONParser();

		return affectedEntryJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AffectedEntry affectedEntry) {
		if (affectedEntry == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		if (affectedEntry.getContentType() == null) {
			map.put("contentType", null);
		}
		else {
			map.put(
				"contentType", String.valueOf(affectedEntry.getContentType()));
		}

		if (affectedEntry.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(affectedEntry.getTitle()));
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

	private static class AffectedEntryJSONParser
		extends BaseJSONParser<AffectedEntry> {

		@Override
		protected AffectedEntry createDTO() {
			return new AffectedEntry();
		}

		@Override
		protected AffectedEntry[] createDTOArray(int size) {
			return new AffectedEntry[size];
		}

		@Override
		protected void setField(
			AffectedEntry affectedEntry, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "contentType")) {
				if (jsonParserFieldValue != null) {
					affectedEntry.setContentType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					affectedEntry.setTitle((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}