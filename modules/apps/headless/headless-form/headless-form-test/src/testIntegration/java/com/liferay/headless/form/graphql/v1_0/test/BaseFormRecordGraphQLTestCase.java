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

package com.liferay.headless.form.graphql.v1_0.test;

import com.liferay.headless.form.client.dto.v1_0.FormRecord;
import com.liferay.headless.form.client.http.HttpInvoker;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseFormRecordGraphQLTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testGetFormRecord() throws Exception {
		FormRecord postFormRecord = testGetFormRecord_addFormRecord();

		List<GraphQLField> graphQLFields = new ArrayList<>();

		graphQLFields.add(new GraphQLField("id"));

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"getFormRecord",
				new HashMap<String, Object>() {
					{
						put("formRecordId", postFormRecord.getId());
					}
				},
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
			_invoke(graphQLField.toString()));

		JSONObject dataJSONObject = responseJSONObject.getJSONObject("data");

		Assert.assertTrue(
			equals(
				postFormRecord, dataJSONObject.getJSONObject("getFormRecord")));
	}

	protected FormRecord testGetFormRecord_addFormRecord() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetFormFormRecordByLatestDraft() throws Exception {
		FormRecord postFormRecord =
			testGetFormFormRecordByLatestDraft_addFormRecord();

		List<GraphQLField> graphQLFields = new ArrayList<>();

		graphQLFields.add(new GraphQLField("id"));

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		GraphQLField graphQLField = new GraphQLField(
			"query",
			new GraphQLField(
				"getFormFormRecordByLatestDraft",
				new HashMap<String, Object>() {
					{
						put("formRecordId", postFormRecord.getId());
					}
				},
				graphQLFields.toArray(new GraphQLField[0])));

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
			_invoke(graphQLField.toString()));

		JSONObject dataJSONObject = responseJSONObject.getJSONObject("data");

		Assert.assertTrue(
			equals(
				postFormRecord,
				dataJSONObject.getJSONObject(
					"getFormFormRecordByLatestDraft")));
	}

	protected FormRecord testGetFormFormRecordByLatestDraft_addFormRecord()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected boolean equals(FormRecord formRecord, JSONObject jsonObject) {
		List<String> fieldNames = new ArrayList(
			Arrays.asList(getAdditionalAssertFieldNames()));

		fieldNames.add("id");

		for (String fieldName : fieldNames) {
			if (Objects.equals("draft", fieldName)) {
				if (!Objects.equals(
						formRecord.getDraft(),
						(Boolean)jsonObject.getBoolean("draft"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("formId", fieldName)) {
				if (!Objects.equals(
						formRecord.getFormId(),
						(Long)jsonObject.getLong("formId"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", fieldName)) {
				if (!Objects.equals(
						formRecord.getId(), (Long)jsonObject.getLong("id"))) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected FormRecord randomFormRecord() throws Exception {
		return new FormRecord() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				datePublished = RandomTestUtil.nextDate();
				draft = RandomTestUtil.randomBoolean();
				formId = RandomTestUtil.randomLong();
				id = RandomTestUtil.randomLong();
			}
		};
	}

	protected Company testCompany;
	protected Group testGroup;

	private String _invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		JSONObject jsonObject = JSONUtil.put("query", query);

		httpInvoker.body(jsonObject.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	private class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

}