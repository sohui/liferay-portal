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

package com.liferay.source.formatter.checks;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.source.formatter.util.FileUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * @author Alan Huang
 * @author Hugo Huijser
 */
public class JSONPackageJSONCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		if (!absolutePath.endsWith("/package.json") ||
			(!absolutePath.contains("/modules/apps/") &&
			 !absolutePath.contains("/modules/private/apps/"))) {

			return content;
		}

		JSONObject jsonObject = new JSONObject(content);

		content = _fixDependencyVersions(
			fileName, absolutePath, content, jsonObject);

		String dirName = absolutePath.substring(0, absolutePath.length() - 12);

		if (!FileUtil.exists(dirName + "build.gradle") &&
			!FileUtil.exists(dirName + "bnd.bnd")) {

			return content;
		}

		_checkIncorrectEntry(fileName, jsonObject, "devDependencies");

		if (jsonObject.isNull("scripts")) {
			return content;
		}

		JSONObject scriptsJSONObject = jsonObject.getJSONObject("scripts");

		if (absolutePath.contains("/modules/apps/frontend-theme")) {
			_checkScript(
				fileName, scriptsJSONObject, "build",
				"liferay-npm-scripts theme build", false);
		}
		else {
			_checkScript(
				fileName, scriptsJSONObject, "build",
				"liferay-npm-scripts build", false);
		}

		_checkScript(
			fileName, scriptsJSONObject, "checkFormat",
			"liferay-npm-scripts check", true);
		_checkScript(
			fileName, scriptsJSONObject, "format", "liferay-npm-scripts fix",
			true);

		return content;
	}

	private void _checkIncorrectEntry(
		String fileName, JSONObject jsonObject, String entryName) {

		if (!jsonObject.isNull(entryName)) {
			addMessage(fileName, "Entry '" + entryName + "' is not allowed");
		}
	}

	private void _checkScript(
		String fileName, JSONObject scriptsJSONObject, String key,
		String expectedValue, boolean requiredScript) {

		if (scriptsJSONObject.isNull(key)) {
			if (requiredScript) {
				addMessage(
					fileName, "Missing entry '" + key + "' in 'scripts'");
			}

			return;
		}

		String value = scriptsJSONObject.getString(key);

		if (!value.contains(expectedValue)) {
			addMessage(
				fileName,
				StringBundler.concat(
					"Value '", value, "' for entry '", key,
					"' does not contain '", expectedValue, "'"));
		}
	}

	private String _fixDependencyVersions(
			String fileName, String absolutePath, String content,
			JSONObject jsonObject)
		throws IOException {

		if (jsonObject.isNull("dependencies")) {
			return content;
		}

		Map<String, String> expectedDependencyVersionsMap =
			_getExpectedDependencyVersionsMap(absolutePath);

		JSONObject dependenciesJSONObject = jsonObject.getJSONObject(
			"dependencies");

		List<String> enforceMinorReleaseRangePackageNames = getAttributeValues(
			_ENFORCE_MINOR_RELEASE_RANGE_DEPENDENCY_NAMES, absolutePath);

		Iterator<String> keys = dependenciesJSONObject.keys();

		while (keys.hasNext()) {
			String dependencyName = keys.next();

			String actualVersion = dependenciesJSONObject.getString(
				dependencyName);
			String expectedVersion = expectedDependencyVersionsMap.get(
				dependencyName);

			if ((expectedVersion != null) &&
				!expectedVersion.equals(actualVersion)) {

				content = StringUtil.replace(
					content,
					StringBundler.concat(
						"\"", dependencyName, "\": \"", actualVersion, "\""),
					StringBundler.concat(
						"\"", dependencyName, "\": \"", expectedVersion, "\""));
			}

			if (!actualVersion.startsWith(StringPool.CARET) &&
				enforceMinorReleaseRangePackageNames.contains(dependencyName)) {

				addMessage(
					fileName,
					StringBundler.concat(
						"Version for '", dependencyName,
						"' should start with '^'"));
			}
		}

		return content;
	}

	private Map<String, String> _getDependencyVersionsMap(
			String fileName, String absolutePath, String regex)
		throws IOException {

		Map<String, String> dependencyVersionsMap = new HashMap<>();

		String content = getPortalContent(fileName, absolutePath);

		if (Validator.isNull(content)) {
			return dependencyVersionsMap;
		}

		JSONObject jsonObject = new JSONObject(content);

		JSONObject dependenciesJSONObject = jsonObject.getJSONObject(
			"dependencies");

		Iterator<String> keys = dependenciesJSONObject.keys();

		while (keys.hasNext()) {
			String dependencyName = keys.next();

			if (dependencyName.matches(regex)) {
				dependencyVersionsMap.put(
					dependencyName,
					dependenciesJSONObject.getString(dependencyName));
			}
		}

		return dependencyVersionsMap;
	}

	private synchronized Map<String, String> _getExpectedDependencyVersionsMap(
			String absolutePath)
		throws IOException {

		if (_expectedDependencyVersionsMap != null) {
			return _expectedDependencyVersionsMap;
		}

		_expectedDependencyVersionsMap = new HashMap<>();

		_expectedDependencyVersionsMap.putAll(
			_getDependencyVersionsMap(
				"modules/apps/frontend-js/frontend-js-metal-web/package.json",
				absolutePath, "metal(-.*)?"));
		_expectedDependencyVersionsMap.putAll(
			_getDependencyVersionsMap(
				"modules/apps/frontend-js/frontend-js-spa-web/package.json",
				absolutePath, "senna"));
		_expectedDependencyVersionsMap.putAll(
			_getDependencyVersionsMap(
				"modules/apps/frontend-taglib/frontend-taglib-clay" +
					"/package.json",
				absolutePath, "clay-.*"));

		return _expectedDependencyVersionsMap;
	}

	private static final String _ENFORCE_MINOR_RELEASE_RANGE_DEPENDENCY_NAMES =
		"enforceMinorReleaseRangeDependencyNames";

	private Map<String, String> _expectedDependencyVersionsMap;

}