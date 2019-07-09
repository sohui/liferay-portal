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

package com.liferay.portal.tools.service.builder.test.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.tools.service.builder.test.model.BigDecimalEntry;
import com.liferay.portal.tools.service.builder.test.model.BigDecimalEntryModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.math.BigDecimal;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the BigDecimalEntry service. Represents a row in the &quot;BigDecimalEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>BigDecimalEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BigDecimalEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BigDecimalEntryImpl
 * @generated
 */
@ProviderType
public class BigDecimalEntryModelImpl
	extends BaseModelImpl<BigDecimalEntry> implements BigDecimalEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a big decimal entry model instance should use the <code>BigDecimalEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "BigDecimalEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"bigDecimalEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"bigDecimalValue", Types.DECIMAL}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("bigDecimalEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("bigDecimalValue", Types.DECIMAL);
	}

	public static final String TABLE_SQL_CREATE =
		"create table BigDecimalEntry (bigDecimalEntryId LONG not null primary key,companyId LONG,bigDecimalValue DECIMAL(30, 16) null)";

	public static final String TABLE_SQL_DROP = "drop table BigDecimalEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY bigDecimalEntry.bigDecimalValue ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY BigDecimalEntry.bigDecimalValue ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.entity.cache.enabled.com.liferay.portal.tools.service.builder.test.model.BigDecimalEntry"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.finder.cache.enabled.com.liferay.portal.tools.service.builder.test.model.BigDecimalEntry"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"value.object.column.bitmask.enabled.com.liferay.portal.tools.service.builder.test.model.BigDecimalEntry"),
		true);

	public static final long BIGDECIMALVALUE_COLUMN_BITMASK = 1L;

	public static final String MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_NAME =
		"BigDecimalEntries_LVEntries";

	public static final Object[][]
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_COLUMNS = {
			{"companyId", Types.BIGINT}, {"bigDecimalEntryId", Types.BIGINT},
			{"lvEntryId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_BIGDECIMALENTRIES_LVENTRIES_SQL_CREATE =
			"create table BigDecimalEntries_LVEntries (companyId LONG not null,bigDecimalEntryId LONG not null,lvEntryId LONG not null,primary key (bigDecimalEntryId, lvEntryId))";

	public static final boolean
		FINDER_CACHE_ENABLED_BIGDECIMALENTRIES_LVENTRIES =
			GetterUtil.getBoolean(
				com.liferay.portal.tools.service.builder.test.service.util.
					ServiceProps.get(
						"value.object.finder.cache.enabled.BigDecimalEntries_LVEntries"),
				true);

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.tools.service.builder.test.service.util.ServiceProps.
			get(
				"lock.expiration.time.com.liferay.portal.tools.service.builder.test.model.BigDecimalEntry"));

	public BigDecimalEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _bigDecimalEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBigDecimalEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bigDecimalEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BigDecimalEntry.class;
	}

	@Override
	public String getModelClassName() {
		return BigDecimalEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<BigDecimalEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<BigDecimalEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BigDecimalEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((BigDecimalEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<BigDecimalEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<BigDecimalEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(BigDecimalEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<BigDecimalEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<BigDecimalEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, BigDecimalEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			BigDecimalEntry.class.getClassLoader(), BigDecimalEntry.class,
			ModelWrapper.class);

		try {
			Constructor<BigDecimalEntry> constructor =
				(Constructor<BigDecimalEntry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<BigDecimalEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<BigDecimalEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<BigDecimalEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<BigDecimalEntry, Object>>();
		Map<String, BiConsumer<BigDecimalEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<BigDecimalEntry, ?>>();

		attributeGetterFunctions.put(
			"bigDecimalEntryId",
			new Function<BigDecimalEntry, Object>() {

				@Override
				public Object apply(BigDecimalEntry bigDecimalEntry) {
					return bigDecimalEntry.getBigDecimalEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"bigDecimalEntryId",
			new BiConsumer<BigDecimalEntry, Object>() {

				@Override
				public void accept(
					BigDecimalEntry bigDecimalEntry, Object bigDecimalEntryId) {

					bigDecimalEntry.setBigDecimalEntryId(
						(Long)bigDecimalEntryId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<BigDecimalEntry, Object>() {

				@Override
				public Object apply(BigDecimalEntry bigDecimalEntry) {
					return bigDecimalEntry.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<BigDecimalEntry, Object>() {

				@Override
				public void accept(
					BigDecimalEntry bigDecimalEntry, Object companyId) {

					bigDecimalEntry.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"bigDecimalValue",
			new Function<BigDecimalEntry, Object>() {

				@Override
				public Object apply(BigDecimalEntry bigDecimalEntry) {
					return bigDecimalEntry.getBigDecimalValue();
				}

			});
		attributeSetterBiConsumers.put(
			"bigDecimalValue",
			new BiConsumer<BigDecimalEntry, Object>() {

				@Override
				public void accept(
					BigDecimalEntry bigDecimalEntry, Object bigDecimalValue) {

					bigDecimalEntry.setBigDecimalValue(
						(BigDecimal)bigDecimalValue);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getBigDecimalEntryId() {
		return _bigDecimalEntryId;
	}

	@Override
	public void setBigDecimalEntryId(long bigDecimalEntryId) {
		_bigDecimalEntryId = bigDecimalEntryId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public BigDecimal getBigDecimalValue() {
		return _bigDecimalValue;
	}

	@Override
	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		_columnBitmask = -1L;

		if (_originalBigDecimalValue == null) {
			_originalBigDecimalValue = _bigDecimalValue;
		}

		_bigDecimalValue = bigDecimalValue;
	}

	public BigDecimal getOriginalBigDecimalValue() {
		return _originalBigDecimalValue;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), BigDecimalEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BigDecimalEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, BigDecimalEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		BigDecimalEntryImpl bigDecimalEntryImpl = new BigDecimalEntryImpl();

		bigDecimalEntryImpl.setBigDecimalEntryId(getBigDecimalEntryId());
		bigDecimalEntryImpl.setCompanyId(getCompanyId());
		bigDecimalEntryImpl.setBigDecimalValue(getBigDecimalValue());

		bigDecimalEntryImpl.resetOriginalValues();

		return bigDecimalEntryImpl;
	}

	@Override
	public int compareTo(BigDecimalEntry bigDecimalEntry) {
		int value = 0;

		value = getBigDecimalValue().compareTo(
			bigDecimalEntry.getBigDecimalValue());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BigDecimalEntry)) {
			return false;
		}

		BigDecimalEntry bigDecimalEntry = (BigDecimalEntry)obj;

		long primaryKey = bigDecimalEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		BigDecimalEntryModelImpl bigDecimalEntryModelImpl = this;

		bigDecimalEntryModelImpl._originalBigDecimalValue =
			bigDecimalEntryModelImpl._bigDecimalValue;

		bigDecimalEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<BigDecimalEntry> toCacheModel() {
		BigDecimalEntryCacheModel bigDecimalEntryCacheModel =
			new BigDecimalEntryCacheModel();

		bigDecimalEntryCacheModel.bigDecimalEntryId = getBigDecimalEntryId();

		bigDecimalEntryCacheModel.companyId = getCompanyId();

		bigDecimalEntryCacheModel.bigDecimalValue = getBigDecimalValue();

		return bigDecimalEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<BigDecimalEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<BigDecimalEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BigDecimalEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((BigDecimalEntry)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<BigDecimalEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<BigDecimalEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<BigDecimalEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((BigDecimalEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, BigDecimalEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _bigDecimalEntryId;
	private long _companyId;
	private BigDecimal _bigDecimalValue;
	private BigDecimal _originalBigDecimalValue;
	private long _columnBitmask;
	private BigDecimalEntry _escapedModel;

}