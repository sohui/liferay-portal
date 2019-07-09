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

package com.liferay.portal.search.aggregation.bucket;

import com.liferay.portal.search.aggregation.Aggregation;
import com.liferay.portal.search.query.Query;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface FiltersAggregation extends Aggregation {

	public void addKeyedQuery(String key, Query query);

	public List<KeyedQuery> getKeyedQueries();

	public Boolean getOtherBucket();

	public String getOtherBucketKey();

	public void setOtherBucket(Boolean otherBucket);

	public void setOtherBucketKey(String otherBucketKey);

	public interface KeyedQuery {

		public String getKey();

		public Query getQuery();

	}

}