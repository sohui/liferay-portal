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

import com.liferay.change.tracking.rest.client.dto.v1_0.CollectionUpdate;
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
public class CollectionUpdateSerDes {

	public static CollectionUpdate toDTO(String json) {
		CollectionUpdateJSONParser collectionUpdateJSONParser =
			new CollectionUpdateJSONParser();

		return collectionUpdateJSONParser.parseToDTO(json);
	}

	public static CollectionUpdate[] toDTOs(String json) {
		CollectionUpdateJSONParser collectionUpdateJSONParser =
			new CollectionUpdateJSONParser();

		return collectionUpdateJSONParser.parseToDTOs(json);
	}

	public static String toJSON(CollectionUpdate collectionUpdate) {
		if (collectionUpdate == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (collectionUpdate.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(collectionUpdate.getDescription()));

			sb.append("\"");
		}

		if (collectionUpdate.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(collectionUpdate.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CollectionUpdateJSONParser collectionUpdateJSONParser =
			new CollectionUpdateJSONParser();

		return collectionUpdateJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(CollectionUpdate collectionUpdate) {
		if (collectionUpdate == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		if (collectionUpdate.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description",
				String.valueOf(collectionUpdate.getDescription()));
		}

		if (collectionUpdate.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(collectionUpdate.getName()));
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

	private static class CollectionUpdateJSONParser
		extends BaseJSONParser<CollectionUpdate> {

		@Override
		protected CollectionUpdate createDTO() {
			return new CollectionUpdate();
		}

		@Override
		protected CollectionUpdate[] createDTOArray(int size) {
			return new CollectionUpdate[size];
		}

		@Override
		protected void setField(
			CollectionUpdate collectionUpdate, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					collectionUpdate.setDescription(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					collectionUpdate.setName((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}