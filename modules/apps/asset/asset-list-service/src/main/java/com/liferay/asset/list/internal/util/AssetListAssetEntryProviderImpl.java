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

package com.liferay.asset.list.internal.util;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.list.constants.AssetListEntryTypeConstants;
import com.liferay.asset.list.internal.dynamic.data.mapping.util.DDMIndexerUtil;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntryAssetEntryRel;
import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.asset.list.service.AssetListEntryAssetEntryRelLocalService;
import com.liferay.asset.list.service.AssetListEntrySegmentsEntryRelLocalService;
import com.liferay.asset.list.util.AssetListAssetEntryProvider;
import com.liferay.asset.util.AssetHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.constants.SegmentsConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sarai Díaz
 */
@Component(immediate = true, service = AssetListAssetEntryProvider.class)
public class AssetListAssetEntryProviderImpl
	implements AssetListAssetEntryProvider {

	@Override
	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long segmentsEntryId) {

		return getAssetEntries(
			assetListEntry, segmentsEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	@Override
	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long segmentsEntryId, int start,
		int end) {

		if (Objects.equals(
				assetListEntry.getType(),
				AssetListEntryTypeConstants.TYPE_MANUAL)) {

			return _getManualAssetEntries(
				assetListEntry, segmentsEntryId, start, end);
		}

		return _getDynamicAssetEntries(
			assetListEntry, segmentsEntryId, start, end);
	}

	@Override
	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long[] segmentsEntryIds) {

		return getAssetEntries(
			assetListEntry,
			_getFirstSegmentsEntryId(assetListEntry, segmentsEntryIds));
	}

	@Override
	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long[] segmentsEntryIds, int start,
		int end) {

		return getAssetEntries(
			assetListEntry,
			_getFirstSegmentsEntryId(assetListEntry, segmentsEntryIds), start,
			end);
	}

	@Override
	public int getAssetEntriesCount(
		AssetListEntry assetListEntry, long segmentsEntryId) {

		if (Objects.equals(
				assetListEntry.getType(),
				AssetListEntryTypeConstants.TYPE_MANUAL)) {

			return _assetListEntryAssetEntryRelLocalService.
				getAssetListEntryAssetEntryRelsCount(
					assetListEntry.getAssetListEntryId(), segmentsEntryId);
		}

		return _assetEntryLocalService.getEntriesCount(
			getAssetEntryQuery(assetListEntry, segmentsEntryId));
	}

	@Override
	public int getAssetEntriesCount(
		AssetListEntry assetListEntry, long[] segmentsEntryIds) {

		return getAssetEntriesCount(
			assetListEntry,
			_getFirstSegmentsEntryId(assetListEntry, segmentsEntryIds));
	}

	@Override
	public AssetEntryQuery getAssetEntryQuery(
		AssetListEntry assetListEntry, long segmentsEntryId) {

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		UnicodeProperties properties = new UnicodeProperties(true);

		properties.fastLoad(assetListEntry.getTypeSettings(segmentsEntryId));

		_setCategoriesAndTags(
			assetListEntry, assetEntryQuery, properties,
			_getAssetCategoryIds(properties), _getAssetTagNames(properties));

		long[] groupIds = GetterUtil.getLongValues(
			StringUtil.split(
				properties.getProperty("groupIds", StringPool.BLANK)));

		if (ArrayUtil.isEmpty(groupIds)) {
			groupIds = new long[] {assetListEntry.getGroupId()};
		}

		assetEntryQuery.setGroupIds(groupIds);

		boolean anyAssetType = GetterUtil.getBoolean(
			properties.getProperty("anyAssetType", null), true);
		long[] availableClassNameIds =
			AssetRendererFactoryRegistryUtil.getClassNameIds(
				assetListEntry.getCompanyId());
		long[] classTypeIds = {};

		if (!anyAssetType) {
			long[] classNameIds = _getClassNameIds(
				properties, availableClassNameIds);

			assetEntryQuery.setClassNameIds(classNameIds);

			for (long classNameId : classNameIds) {
				String className = _portal.getClassName(classNameId);

				classTypeIds = ArrayUtil.append(
					classTypeIds,
					_getClassTypeIds(assetListEntry, properties, className));
			}

			assetEntryQuery.setClassTypeIds(classTypeIds);
		}
		else {
			assetEntryQuery.setClassNameIds(availableClassNameIds);
		}

		String ddmStructureFieldName = properties.getProperty(
			"ddmStructureFieldName");

		String ddmStructureFieldValue = properties.getProperty(
			"ddmStructureFieldValue");

		if (Validator.isNotNull(ddmStructureFieldName) &&
			Validator.isNotNull(ddmStructureFieldValue) &&
			(classTypeIds.length == 1)) {

			assetEntryQuery.setAttribute(
				"ddmStructureFieldName",
				DDMIndexerUtil.encodeName(
					classTypeIds[0], ddmStructureFieldName,
					LocaleUtil.getMostRelevantLocale()));
			assetEntryQuery.setAttribute(
				"ddmStructureFieldValue", ddmStructureFieldValue);
		}

		String orderByColumn1 = GetterUtil.getString(
			properties.getProperty("orderByColumn1", "modifiedDate"));

		assetEntryQuery.setOrderByCol1(orderByColumn1);

		String orderByColumn2 = GetterUtil.getString(
			properties.getProperty("orderByColumn2", "title"));

		assetEntryQuery.setOrderByCol2(orderByColumn2);

		String orderByType1 = GetterUtil.getString(
			properties.getProperty("orderByType1", "DESC"));

		assetEntryQuery.setOrderByType1(orderByType1);

		String orderByType2 = GetterUtil.getString(
			properties.getProperty("orderByType2", "ASC"));

		assetEntryQuery.setOrderByType2(orderByType2);

		return assetEntryQuery;
	}

	@Override
	public AssetEntryQuery getAssetEntryQuery(
		AssetListEntry assetListEntry, long[] segmentsEntryIds) {

		return getAssetEntryQuery(
			assetListEntry,
			_getFirstSegmentsEntryId(assetListEntry, segmentsEntryIds));
	}

	private static long[] _getAssetCategoryIds(UnicodeProperties properties) {
		long[] assetCategoryIds = new long[0];

		for (int i = 0; true; i++) {
			String[] queryValues = StringUtil.split(
				properties.getProperty("queryValues" + i, null));

			if (ArrayUtil.isEmpty(queryValues)) {
				break;
			}

			boolean queryContains = GetterUtil.getBoolean(
				properties.getProperty("queryContains" + i, StringPool.BLANK));
			boolean queryAndOperator = GetterUtil.getBoolean(
				properties.getProperty(
					"queryAndOperator" + i, StringPool.BLANK));
			String queryName = properties.getProperty(
				"queryName" + i, StringPool.BLANK);

			if (Objects.equals(queryName, "assetCategories") && queryContains &&
				(queryAndOperator || (queryValues.length == 1))) {

				assetCategoryIds = ArrayUtil.append(
					assetCategoryIds, GetterUtil.getLongValues(queryValues));
			}
		}

		return assetCategoryIds;
	}

	private static String[] _getAssetTagNames(UnicodeProperties properties) {
		String[] allAssetTagNames = new String[0];

		for (int i = 0; true; i++) {
			String[] queryValues = StringUtil.split(
				properties.getProperty("queryValues" + i, null));

			if (ArrayUtil.isEmpty(queryValues)) {
				break;
			}

			boolean queryContains = GetterUtil.getBoolean(
				properties.getProperty("queryContains" + i, StringPool.BLANK));
			boolean queryAndOperator = GetterUtil.getBoolean(
				properties.getProperty(
					"queryAndOperator" + i, StringPool.BLANK));
			String queryName = properties.getProperty(
				"queryName" + i, StringPool.BLANK);

			if (!Objects.equals(queryName, "assetCategories") &&
				queryContains &&
				(queryAndOperator || (queryValues.length == 1))) {

				allAssetTagNames = queryValues;
			}
		}

		return allAssetTagNames;
	}

	private long[] _filterAssetCategoryIds(long[] assetCategoryIds) {
		List<Long> assetCategoryIdsList = new ArrayList<>();

		for (long assetCategoryId : assetCategoryIds) {
			AssetCategory category =
				_assetCategoryLocalService.fetchAssetCategory(assetCategoryId);

			if (category == null) {
				continue;
			}

			assetCategoryIdsList.add(assetCategoryId);
		}

		return ArrayUtil.toArray(assetCategoryIdsList.toArray(new Long[0]));
	}

	private long[] _getClassNameIds(
		UnicodeProperties properties, long[] availableClassNameIds) {

		boolean anyAssetType = GetterUtil.getBoolean(
			properties.getProperty("anyAssetType", Boolean.TRUE.toString()));

		if (anyAssetType) {
			return availableClassNameIds;
		}

		long defaultClassNameId = GetterUtil.getLong(
			properties.getProperty("anyAssetType", null));

		if (defaultClassNameId > 0) {
			return new long[] {defaultClassNameId};
		}

		long[] classNameIds = GetterUtil.getLongValues(
			StringUtil.split(properties.getProperty("classNameIds", null)));

		if (ArrayUtil.isNotEmpty(classNameIds)) {
			return classNameIds;
		}

		return availableClassNameIds;
	}

	private long[] _getClassTypeIds(
		AssetListEntry assetListEntry, UnicodeProperties properties,
		String className) {

		long[] availableClassTypeIds = {};

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory != null) {
			ClassTypeReader classTypeReader =
				assetRendererFactory.getClassTypeReader();

			try {
				List<ClassType> classTypes =
					classTypeReader.getAvailableClassTypes(
						_portal.getSharedContentSiteGroupIds(
							assetListEntry.getCompanyId(),
							assetListEntry.getGroupId(),
							assetListEntry.getUserId()),
						LocaleUtil.getDefault());

				Stream<ClassType> stream = classTypes.stream();

				availableClassTypeIds = stream.mapToLong(
					ClassType::getClassTypeId
				).toArray();
			}
			catch (PortalException pe) {
				_log.error(
					"Unable to get class types for class name " + className,
					pe);
			}
		}

		Class<?> clazz = assetRendererFactory.getClass();

		boolean anyAssetType = GetterUtil.getBoolean(
			properties.getProperty(
				"anyClassType" + clazz.getSimpleName(),
				Boolean.TRUE.toString()));

		if (anyAssetType) {
			return availableClassTypeIds;
		}

		long anyClassTypeId = GetterUtil.getLong(
			properties.getProperty(
				"anyClassType" + clazz.getSimpleName(), null),
			-1);

		if (anyClassTypeId > -1) {
			return new long[] {anyClassTypeId};
		}

		long[] classTypeIds = StringUtil.split(
			properties.getProperty(
				"classTypeIds" + clazz.getSimpleName(), null),
			0L);

		if (classTypeIds != null) {
			return classTypeIds;
		}

		return availableClassTypeIds;
	}

	private List<AssetEntry> _getDynamicAssetEntries(
		AssetListEntry assetListEntry, long segmentsEntryId, int start,
		int end) {

		AssetEntryQuery assetEntryQuery = getAssetEntryQuery(
			assetListEntry, segmentsEntryId);

		assetEntryQuery.setEnd(end);
		assetEntryQuery.setStart(start);

		return _search(assetListEntry.getCompanyId(), assetEntryQuery);
	}

	private long _getFirstSegmentsEntryId(
		AssetListEntry assetListEntry, long[] segmentsEntryIds) {

		LongStream stream = Arrays.stream(segmentsEntryIds);

		return stream.filter(
			segmentsEntryId -> {
				if (segmentsEntryId ==
						SegmentsConstants.SEGMENTS_ENTRY_ID_DEFAULT) {

					return false;
				}

				AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel =
					_assetListEntrySegmentsEntryRelLocalService.
						fetchAssetListEntrySegmentsEntryRel(
							assetListEntry.getAssetListEntryId(),
							segmentsEntryId);

				return assetListEntrySegmentsEntryRel != null;
			}
		).findFirst(
		).orElse(
			SegmentsConstants.SEGMENTS_ENTRY_ID_DEFAULT
		);
	}

	private List<AssetEntry> _getManualAssetEntries(
		AssetListEntry assetListEntry, long segmentsEntryId, int start,
		int end) {

		List<AssetListEntryAssetEntryRel> assetListEntryAssetEntryRels =
			_assetListEntryAssetEntryRelLocalService.
				getAssetListEntryAssetEntryRels(
					assetListEntry.getAssetListEntryId(), segmentsEntryId,
					start, end);

		Stream<AssetListEntryAssetEntryRel> stream =
			assetListEntryAssetEntryRels.stream();

		return stream.map(
			assetListEntryAssetEntryRel -> _assetEntryLocalService.fetchEntry(
				assetListEntryAssetEntryRel.getAssetEntryId())
		).collect(
			Collectors.toList()
		);
	}

	private List<AssetEntry> _search(
		long companyId, AssetEntryQuery assetEntryQuery) {

		SearchContext searchContext = new SearchContext();

		String ddmStructureFieldName = GetterUtil.getString(
			assetEntryQuery.getAttribute("ddmStructureFieldName"));
		Serializable ddmStructureFieldValue = assetEntryQuery.getAttribute(
			"ddmStructureFieldValue");

		if (Validator.isNotNull(ddmStructureFieldName) &&
			Validator.isNotNull(ddmStructureFieldValue)) {

			searchContext.setAttribute(
				"ddmStructureFieldName", ddmStructureFieldName);
			searchContext.setAttribute(
				"ddmStructureFieldValue", ddmStructureFieldValue);
		}

		searchContext.setClassTypeIds(assetEntryQuery.getClassTypeIds());
		searchContext.setCompanyId(companyId);
		searchContext.setEnd(assetEntryQuery.getEnd());
		searchContext.setStart(assetEntryQuery.getStart());

		try {
			Hits hits = _assetHelper.search(
				searchContext, assetEntryQuery, assetEntryQuery.getStart(),
				assetEntryQuery.getEnd());

			return _assetHelper.getAssetEntries(hits);
		}
		catch (Exception e) {
			_log.error("Unable to get asset entries", e);
		}

		return Collections.emptyList();
	}

	private void _setCategoriesAndTags(
		AssetListEntry assetListEntry, AssetEntryQuery assetEntryQuery,
		UnicodeProperties properties, long[] overrideAllAssetCategoryIds,
		String[] overrideAllAssetTagNames) {

		long[] allAssetCategoryIds = new long[0];
		long[] anyAssetCategoryIds = new long[0];
		long[] notAllAssetCategoryIds = new long[0];
		long[] notAnyAssetCategoryIds = new long[0];

		String[] allAssetTagNames = new String[0];
		String[] anyAssetTagNames = new String[0];
		String[] notAllAssetTagNames = new String[0];
		String[] notAnyAssetTagNames = new String[0];

		for (int i = 0; true; i++) {
			String[] queryValues = StringUtil.split(
				properties.getProperty("queryValues" + i, null));

			if (ArrayUtil.isEmpty(queryValues)) {
				break;
			}

			boolean queryContains = GetterUtil.getBoolean(
				properties.getProperty("queryContains" + i, StringPool.BLANK));
			boolean queryAndOperator = GetterUtil.getBoolean(
				properties.getProperty(
					"queryAndOperator" + i, StringPool.BLANK));
			String queryName = properties.getProperty(
				"queryName" + i, StringPool.BLANK);

			if (Objects.equals(queryName, "assetCategories")) {
				long[] assetCategoryIds = GetterUtil.getLongValues(queryValues);

				if (queryContains && queryAndOperator) {
					allAssetCategoryIds = assetCategoryIds;
				}
				else if (queryContains && !queryAndOperator) {
					anyAssetCategoryIds = assetCategoryIds;
				}
				else if (!queryContains && queryAndOperator) {
					notAllAssetCategoryIds = assetCategoryIds;
				}
				else {
					notAnyAssetCategoryIds = assetCategoryIds;
				}
			}
			else {
				if (queryContains && queryAndOperator) {
					allAssetTagNames = queryValues;
				}
				else if (queryContains && !queryAndOperator) {
					anyAssetTagNames = queryValues;
				}
				else if (!queryContains && queryAndOperator) {
					notAllAssetTagNames = queryValues;
				}
				else {
					notAnyAssetTagNames = queryValues;
				}
			}
		}

		if (overrideAllAssetCategoryIds != null) {
			allAssetCategoryIds = overrideAllAssetCategoryIds;
		}

		allAssetCategoryIds = _filterAssetCategoryIds(allAssetCategoryIds);

		assetEntryQuery.setAllCategoryIds(allAssetCategoryIds);

		if (overrideAllAssetTagNames != null) {
			allAssetTagNames = overrideAllAssetTagNames;
		}

		long siteGroupId = _portal.getSiteGroupId(assetListEntry.getGroupId());

		for (String assetTagName : allAssetTagNames) {
			long[] allAssetTagIds = _assetTagLocalService.getTagIds(
				new long[] {siteGroupId}, assetTagName);

			assetEntryQuery.addAllTagIdsArray(allAssetTagIds);
		}

		assetEntryQuery.setAnyCategoryIds(anyAssetCategoryIds);

		long[] anyAssetTagIds = _assetTagLocalService.getTagIds(
			siteGroupId, anyAssetTagNames);

		assetEntryQuery.setAnyTagIds(anyAssetTagIds);

		assetEntryQuery.setNotAllCategoryIds(notAllAssetCategoryIds);

		for (String assetTagName : notAllAssetTagNames) {
			long[] notAllAssetTagIds = _assetTagLocalService.getTagIds(
				new long[] {siteGroupId}, assetTagName);

			assetEntryQuery.addNotAllTagIdsArray(notAllAssetTagIds);
		}

		assetEntryQuery.setNotAnyCategoryIds(notAnyAssetCategoryIds);

		long[] notAnyAssetTagIds = _assetTagLocalService.getTagIds(
			siteGroupId, notAnyAssetTagNames);

		assetEntryQuery.setNotAnyTagIds(notAnyAssetTagIds);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetListAssetEntryProviderImpl.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetHelper _assetHelper;

	@Reference
	private AssetListEntryAssetEntryRelLocalService
		_assetListEntryAssetEntryRelLocalService;

	@Reference
	private AssetListEntrySegmentsEntryRelLocalService
		_assetListEntrySegmentsEntryRelLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private Portal _portal;

}