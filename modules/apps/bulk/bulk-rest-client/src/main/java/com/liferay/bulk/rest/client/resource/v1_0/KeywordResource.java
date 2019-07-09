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

package com.liferay.bulk.rest.client.resource.v1_0;

import com.liferay.bulk.rest.client.dto.v1_0.Keyword;
import com.liferay.bulk.rest.client.http.HttpInvoker;
import com.liferay.bulk.rest.client.pagination.Page;
import com.liferay.bulk.rest.client.serdes.v1_0.KeywordSerDes;

import java.util.Locale;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Alejandro Tardín
 * @generated
 */
@Generated("")
public interface KeywordResource {

	public static Builder builder() {
		return new Builder();
	}

	public void patchKeywordBatch(
			com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
				keywordBulkSelection)
		throws Exception;

	public HttpInvoker.HttpResponse patchKeywordBatchHttpResponse(
			com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
				keywordBulkSelection)
		throws Exception;

	public void putKeywordBatch(
			com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
				keywordBulkSelection)
		throws Exception;

	public HttpInvoker.HttpResponse putKeywordBatchHttpResponse(
			com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
				keywordBulkSelection)
		throws Exception;

	public Page<Keyword> postKeywordsCommonPage(
			com.liferay.bulk.rest.client.dto.v1_0.DocumentBulkSelection
				documentBulkSelection)
		throws Exception;

	public HttpInvoker.HttpResponse postKeywordsCommonPageHttpResponse(
			com.liferay.bulk.rest.client.dto.v1_0.DocumentBulkSelection
				documentBulkSelection)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public KeywordResource build() {
			return new KeywordResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		private Builder() {
		}

		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class KeywordResourceImpl implements KeywordResource {

		public void patchKeywordBatch(
				com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
					keywordBulkSelection)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				patchKeywordBatchHttpResponse(keywordBulkSelection);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse patchKeywordBatchHttpResponse(
				com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
					keywordBulkSelection)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				keywordBulkSelection.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PATCH);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/bulk/v1.0/keywords/batch");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putKeywordBatch(
				com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
					keywordBulkSelection)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = putKeywordBatchHttpResponse(
				keywordBulkSelection);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putKeywordBatchHttpResponse(
				com.liferay.bulk.rest.client.dto.v1_0.KeywordBulkSelection
					keywordBulkSelection)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				keywordBulkSelection.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/bulk/v1.0/keywords/batch");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Keyword> postKeywordsCommonPage(
				com.liferay.bulk.rest.client.dto.v1_0.DocumentBulkSelection
					documentBulkSelection)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postKeywordsCommonPageHttpResponse(documentBulkSelection);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, KeywordSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse postKeywordsCommonPageHttpResponse(
				com.liferay.bulk.rest.client.dto.v1_0.DocumentBulkSelection
					documentBulkSelection)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				documentBulkSelection.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/bulk/v1.0/keywords/common");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private KeywordResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			KeywordResource.class.getName());

		private Builder _builder;

	}

}