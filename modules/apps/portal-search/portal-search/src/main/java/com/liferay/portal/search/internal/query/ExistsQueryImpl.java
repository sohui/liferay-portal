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

package com.liferay.portal.search.internal.query;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.search.query.ExistsQuery;
import com.liferay.portal.search.query.QueryVisitor;

/**
 * @author Michael C. Han
 */
public class ExistsQueryImpl extends BaseQueryImpl implements ExistsQuery {

	public ExistsQueryImpl(String field) {
		_field = field;
	}

	@Override
	public <T> T accept(QueryVisitor<T> queryVisitor) {
		return queryVisitor.visit(this);
	}

	public String getField() {
		return _field;
	}

	public int getSortOrder() {
		return 1;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{(");
		sb.append(_field);
		sb.append("), ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private static final long serialVersionUID = 1L;

	private final String _field;

}