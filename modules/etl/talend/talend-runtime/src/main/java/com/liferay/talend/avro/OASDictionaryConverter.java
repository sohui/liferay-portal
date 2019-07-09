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

package com.liferay.talend.avro;

import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.IndexedRecord;

/**
 * @author Igor Beslic
 */
public class OASDictionaryConverter
	extends BaseConverter<JsonObject, IndexedRecord> {

	public OASDictionaryConverter(Schema schema) {
		super(JsonObject.class, schema);

		initConverters(schema);
	}

	@Override
	public IndexedRecord convertToAvro(JsonObject contentJsonObject) {
		IndexedRecord record = new GenericData.Record(getSchema());

		contentJsonObject.forEach(
			(entryKey, entryValue) -> {
				record.put(0, entryKey);
				record.put(1, _asText(entryValue));
			});

		return record;
	}

	@Override
	public JsonObject convertToDatum(IndexedRecord value) {
		throw new UnsupportedOperationException();
	}

	private String _asText(JsonValue jsonValue) {
		JsonString jsonString = (JsonString)jsonValue;

		return jsonString.getString();
	}

}