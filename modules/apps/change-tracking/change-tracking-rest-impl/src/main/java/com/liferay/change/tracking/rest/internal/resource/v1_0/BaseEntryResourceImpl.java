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

package com.liferay.change.tracking.rest.internal.resource.v1_0;

import com.liferay.change.tracking.rest.dto.v1_0.Collection;
import com.liferay.change.tracking.rest.dto.v1_0.Entry;
import com.liferay.change.tracking.rest.resource.v1_0.EntryResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import javax.validation.constraints.NotNull;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author Máté Thurzó
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseEntryResourceImpl implements EntryResource {

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "collectionId"),
			@Parameter(in = ParameterIn.QUERY, name = "changeTypesFilter"),
			@Parameter(in = ParameterIn.QUERY, name = "classNameIdsFilter"),
			@Parameter(in = ParameterIn.QUERY, name = "collision"),
			@Parameter(in = ParameterIn.QUERY, name = "groupIdsFilter"),
			@Parameter(in = ParameterIn.QUERY, name = "status"),
			@Parameter(in = ParameterIn.QUERY, name = "userIdsFilter"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize"),
			@Parameter(in = ParameterIn.QUERY, name = "sort")
		}
	)
	@Path("/collections/{collectionId}/entries")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Entry")})
	public Page<Entry> getCollectionEntriesPage(
			@NotNull @Parameter(hidden = true) @PathParam("collectionId") Long
				collectionId,
			@Parameter(hidden = true) @QueryParam("changeTypesFilter") String[]
				changeTypesFilter,
			@Parameter(hidden = true) @QueryParam("classNameIdsFilter") String[]
				classNameIdsFilter,
			@DefaultValue("false") @Parameter(hidden = true)
			@QueryParam("collision") Boolean collision,
			@Parameter(hidden = true) @QueryParam("groupIdsFilter") String[]
				groupIdsFilter,
			@DefaultValue("2") @Parameter(hidden = true) @QueryParam("status")
				Integer status,
			@Parameter(hidden = true) @QueryParam("userIdsFilter") String[]
				userIdsFilter,
			@Context Pagination pagination, @Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@GET
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "entryId")})
	@Path("/entries/{entryId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Entry")})
	public Entry getEntry(
			@NotNull @Parameter(hidden = true) @PathParam("entryId") Long
				entryId)
		throws Exception {

		return new Entry();
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	public void setContextUser(User contextUser) {
		this.contextUser = contextUser;
	}

	protected void preparePatch(Entry entry, Entry existingEntry) {
	}

	protected <T, R> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	@Context
	protected AcceptLanguage contextAcceptLanguage;

	@Context
	protected Company contextCompany;

	@Context
	protected UriInfo contextUriInfo;

	@Context
	protected User contextUser;

}