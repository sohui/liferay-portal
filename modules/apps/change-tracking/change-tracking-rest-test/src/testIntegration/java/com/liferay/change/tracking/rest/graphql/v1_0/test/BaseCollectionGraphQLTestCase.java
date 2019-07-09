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

package com.liferay.change.tracking.rest.graphql.v1_0.test;

import com.liferay.change.tracking.rest.client.dto.v1_0.Collection;
import com.liferay.change.tracking.rest.client.dto.v1_0.Entry;
import com.liferay.change.tracking.rest.client.http.HttpInvoker;
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
 * @author Máté Thurzó
 * @generated
 */
@Generated("")
public abstract class BaseCollectionGraphQLTestCase {

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
	public void testGetCollection() throws Exception {
		Assert.assertTrue(true);
	}

	protected boolean equals(Collection collection, JSONObject jsonObject) {
		List<String> fieldNames = new ArrayList(
			Arrays.asList(getAdditionalAssertFieldNames()));

		fieldNames.add("id");

		for (String fieldName : fieldNames) {
			if (Objects.equals("additionCount", fieldName)) {
				if (!Objects.equals(
						collection.getAdditionCount(),
						(Long)jsonObject.getLong("additionCount"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("collectionId", fieldName)) {
				if (!Objects.equals(
						collection.getCollectionId(),
						(Long)jsonObject.getLong("collectionId"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("companyId", fieldName)) {
				if (!Objects.equals(
						collection.getCompanyId(),
						(Long)jsonObject.getLong("companyId"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("deletionCount", fieldName)) {
				if (!Objects.equals(
						collection.getDeletionCount(),
						(Long)jsonObject.getLong("deletionCount"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", fieldName)) {
				if (!Objects.equals(
						collection.getDescription(),
						(String)jsonObject.getString("description"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modificationCount", fieldName)) {
				if (!Objects.equals(
						collection.getModificationCount(),
						(Long)jsonObject.getLong("modificationCount"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", fieldName)) {
				if (!Objects.equals(
						collection.getName(),
						(String)jsonObject.getString("name"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("statusByUserName", fieldName)) {
				if (!Objects.equals(
						collection.getStatusByUserName(),
						(String)jsonObject.getString("statusByUserName"))) {

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

	protected Collection randomCollection() throws Exception {
		return new Collection() {
			{
				additionCount = RandomTestUtil.randomLong();
				collectionId = RandomTestUtil.randomLong();
				companyId = RandomTestUtil.randomLong();
				dateStatus = RandomTestUtil.nextDate();
				deletionCount = RandomTestUtil.randomLong();
				description = RandomTestUtil.randomString();
				modificationCount = RandomTestUtil.randomLong();
				name = RandomTestUtil.randomString();
				statusByUserName = RandomTestUtil.randomString();
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