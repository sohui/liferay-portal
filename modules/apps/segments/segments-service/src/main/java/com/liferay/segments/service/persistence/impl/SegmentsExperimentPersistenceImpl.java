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

package com.liferay.segments.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.segments.exception.NoSuchExperimentException;
import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.model.impl.SegmentsExperimentImpl;
import com.liferay.segments.model.impl.SegmentsExperimentModelImpl;
import com.liferay.segments.service.persistence.SegmentsExperimentPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence implementation for the segments experiment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Garcia
 * @generated
 */
@ProviderType
public class SegmentsExperimentPersistenceImpl
	extends BasePersistenceImpl<SegmentsExperiment>
	implements SegmentsExperimentPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SegmentsExperimentUtil</code> to access the segments experiment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SegmentsExperimentImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the segments experiments where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the segments experiments where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @return the range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the segments experiments where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the segments experiments where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<SegmentsExperiment> list = null;

		if (retrieveFromCache) {
			list = (List<SegmentsExperiment>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SegmentsExperiment segmentsExperiment : list) {
					if (!uuid.equals(segmentsExperiment.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first segments experiment in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByUuid_First(
			String uuid,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByUuid_First(
			uuid, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the first segments experiment in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByUuid_First(
		String uuid, OrderByComparator<SegmentsExperiment> orderByComparator) {

		List<SegmentsExperiment> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last segments experiment in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByUuid_Last(
			String uuid,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByUuid_Last(
			uuid, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the last segments experiment in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByUuid_Last(
		String uuid, OrderByComparator<SegmentsExperiment> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SegmentsExperiment> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the segments experiments before and after the current segments experiment in the ordered set where uuid = &#63;.
	 *
	 * @param segmentsExperimentId the primary key of the current segments experiment
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experiment
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment[] findByUuid_PrevAndNext(
			long segmentsExperimentId, String uuid,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		uuid = Objects.toString(uuid, "");

		SegmentsExperiment segmentsExperiment = findByPrimaryKey(
			segmentsExperimentId);

		Session session = null;

		try {
			session = openSession();

			SegmentsExperiment[] array = new SegmentsExperimentImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, segmentsExperiment, uuid, orderByComparator, true);

			array[1] = segmentsExperiment;

			array[2] = getByUuid_PrevAndNext(
				session, segmentsExperiment, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SegmentsExperiment getByUuid_PrevAndNext(
		Session session, SegmentsExperiment segmentsExperiment, String uuid,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						segmentsExperiment)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<SegmentsExperiment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the segments experiments where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SegmentsExperiment segmentsExperiment :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(segmentsExperiment);
		}
	}

	/**
	 * Returns the number of segments experiments where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching segments experiments
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SEGMENTSEXPERIMENT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"segmentsExperiment.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(segmentsExperiment.uuid IS NULL OR segmentsExperiment.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the segments experiment where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchExperimentException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByUUID_G(String uuid, long groupId)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByUUID_G(uuid, groupId);

		if (segmentsExperiment == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchExperimentException(msg.toString());
		}

		return segmentsExperiment;
	}

	/**
	 * Returns the segments experiment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the segments experiment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof SegmentsExperiment) {
			SegmentsExperiment segmentsExperiment = (SegmentsExperiment)result;

			if (!Objects.equals(uuid, segmentsExperiment.getUuid()) ||
				(groupId != segmentsExperiment.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<SegmentsExperiment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					SegmentsExperiment segmentsExperiment = list.get(0);

					result = segmentsExperiment;

					cacheResult(segmentsExperiment);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByUUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SegmentsExperiment)result;
		}
	}

	/**
	 * Removes the segments experiment where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the segments experiment that was removed
	 */
	@Override
	public SegmentsExperiment removeByUUID_G(String uuid, long groupId)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = findByUUID_G(uuid, groupId);

		return remove(segmentsExperiment);
	}

	/**
	 * Returns the number of segments experiments where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching segments experiments
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SEGMENTSEXPERIMENT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"segmentsExperiment.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(segmentsExperiment.uuid IS NULL OR segmentsExperiment.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"segmentsExperiment.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the segments experiments where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the segments experiments where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @return the range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the segments experiments where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the segments experiments where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] {uuid, companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<SegmentsExperiment> list = null;

		if (retrieveFromCache) {
			list = (List<SegmentsExperiment>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SegmentsExperiment segmentsExperiment : list) {
					if (!uuid.equals(segmentsExperiment.getUuid()) ||
						(companyId != segmentsExperiment.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first segments experiment in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the first segments experiment in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		List<SegmentsExperiment> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last segments experiment in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the last segments experiment in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SegmentsExperiment> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the segments experiments before and after the current segments experiment in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param segmentsExperimentId the primary key of the current segments experiment
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experiment
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment[] findByUuid_C_PrevAndNext(
			long segmentsExperimentId, String uuid, long companyId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		uuid = Objects.toString(uuid, "");

		SegmentsExperiment segmentsExperiment = findByPrimaryKey(
			segmentsExperimentId);

		Session session = null;

		try {
			session = openSession();

			SegmentsExperiment[] array = new SegmentsExperimentImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, segmentsExperiment, uuid, companyId, orderByComparator,
				true);

			array[1] = segmentsExperiment;

			array[2] = getByUuid_C_PrevAndNext(
				session, segmentsExperiment, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SegmentsExperiment getByUuid_C_PrevAndNext(
		Session session, SegmentsExperiment segmentsExperiment, String uuid,
		long companyId, OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						segmentsExperiment)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<SegmentsExperiment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the segments experiments where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SegmentsExperiment segmentsExperiment :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(segmentsExperiment);
		}
	}

	/**
	 * Returns the number of segments experiments where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching segments experiments
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SEGMENTSEXPERIMENT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"segmentsExperiment.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(segmentsExperiment.uuid IS NULL OR segmentsExperiment.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"segmentsExperiment.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the segments experiments where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the segments experiments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @return the range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the segments experiments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the segments experiments where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByGroupId;
			finderArgs = new Object[] {groupId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<SegmentsExperiment> list = null;

		if (retrieveFromCache) {
			list = (List<SegmentsExperiment>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SegmentsExperiment segmentsExperiment : list) {
					if ((groupId != segmentsExperiment.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first segments experiment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByGroupId_First(
			long groupId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByGroupId_First(
			groupId, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the first segments experiment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByGroupId_First(
		long groupId, OrderByComparator<SegmentsExperiment> orderByComparator) {

		List<SegmentsExperiment> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last segments experiment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByGroupId_Last(
			long groupId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the last segments experiment in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByGroupId_Last(
		long groupId, OrderByComparator<SegmentsExperiment> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SegmentsExperiment> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the segments experiments before and after the current segments experiment in the ordered set where groupId = &#63;.
	 *
	 * @param segmentsExperimentId the primary key of the current segments experiment
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experiment
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment[] findByGroupId_PrevAndNext(
			long segmentsExperimentId, long groupId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = findByPrimaryKey(
			segmentsExperimentId);

		Session session = null;

		try {
			session = openSession();

			SegmentsExperiment[] array = new SegmentsExperimentImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, segmentsExperiment, groupId, orderByComparator, true);

			array[1] = segmentsExperiment;

			array[2] = getByGroupId_PrevAndNext(
				session, segmentsExperiment, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SegmentsExperiment getByGroupId_PrevAndNext(
		Session session, SegmentsExperiment segmentsExperiment, long groupId,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						segmentsExperiment)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<SegmentsExperiment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the segments experiments where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (SegmentsExperiment segmentsExperiment :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(segmentsExperiment);
		}
	}

	/**
	 * Returns the number of segments experiments where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching segments experiments
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SEGMENTSEXPERIMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"segmentsExperiment.groupId = ?";

	private FinderPath _finderPathWithPaginationFindBySegmentsExperienceId;
	private FinderPath _finderPathWithoutPaginationFindBySegmentsExperienceId;
	private FinderPath _finderPathCountBySegmentsExperienceId;

	/**
	 * Returns all the segments experiments where segmentsExperienceId = &#63;.
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @return the matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findBySegmentsExperienceId(
		long segmentsExperienceId) {

		return findBySegmentsExperienceId(
			segmentsExperienceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the segments experiments where segmentsExperienceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @return the range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findBySegmentsExperienceId(
		long segmentsExperienceId, int start, int end) {

		return findBySegmentsExperienceId(
			segmentsExperienceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the segments experiments where segmentsExperienceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findBySegmentsExperienceId(
		long segmentsExperienceId, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		return findBySegmentsExperienceId(
			segmentsExperienceId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the segments experiments where segmentsExperienceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findBySegmentsExperienceId(
		long segmentsExperienceId, int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindBySegmentsExperienceId;
			finderArgs = new Object[] {segmentsExperienceId};
		}
		else {
			finderPath = _finderPathWithPaginationFindBySegmentsExperienceId;
			finderArgs = new Object[] {
				segmentsExperienceId, start, end, orderByComparator
			};
		}

		List<SegmentsExperiment> list = null;

		if (retrieveFromCache) {
			list = (List<SegmentsExperiment>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SegmentsExperiment segmentsExperiment : list) {
					if ((segmentsExperienceId !=
							segmentsExperiment.getSegmentsExperienceId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

			query.append(
				_FINDER_COLUMN_SEGMENTSEXPERIENCEID_SEGMENTSEXPERIENCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(segmentsExperienceId);

				if (!pagination) {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first segments experiment in the ordered set where segmentsExperienceId = &#63;.
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findBySegmentsExperienceId_First(
			long segmentsExperienceId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment =
			fetchBySegmentsExperienceId_First(
				segmentsExperienceId, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("segmentsExperienceId=");
		msg.append(segmentsExperienceId);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the first segments experiment in the ordered set where segmentsExperienceId = &#63;.
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchBySegmentsExperienceId_First(
		long segmentsExperienceId,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		List<SegmentsExperiment> list = findBySegmentsExperienceId(
			segmentsExperienceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last segments experiment in the ordered set where segmentsExperienceId = &#63;.
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findBySegmentsExperienceId_Last(
			long segmentsExperienceId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment =
			fetchBySegmentsExperienceId_Last(
				segmentsExperienceId, orderByComparator);

		if (segmentsExperiment != null) {
			return segmentsExperiment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("segmentsExperienceId=");
		msg.append(segmentsExperienceId);

		msg.append("}");

		throw new NoSuchExperimentException(msg.toString());
	}

	/**
	 * Returns the last segments experiment in the ordered set where segmentsExperienceId = &#63;.
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchBySegmentsExperienceId_Last(
		long segmentsExperienceId,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		int count = countBySegmentsExperienceId(segmentsExperienceId);

		if (count == 0) {
			return null;
		}

		List<SegmentsExperiment> list = findBySegmentsExperienceId(
			segmentsExperienceId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the segments experiments before and after the current segments experiment in the ordered set where segmentsExperienceId = &#63;.
	 *
	 * @param segmentsExperimentId the primary key of the current segments experiment
	 * @param segmentsExperienceId the segments experience ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experiment
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment[] findBySegmentsExperienceId_PrevAndNext(
			long segmentsExperimentId, long segmentsExperienceId,
			OrderByComparator<SegmentsExperiment> orderByComparator)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = findByPrimaryKey(
			segmentsExperimentId);

		Session session = null;

		try {
			session = openSession();

			SegmentsExperiment[] array = new SegmentsExperimentImpl[3];

			array[0] = getBySegmentsExperienceId_PrevAndNext(
				session, segmentsExperiment, segmentsExperienceId,
				orderByComparator, true);

			array[1] = segmentsExperiment;

			array[2] = getBySegmentsExperienceId_PrevAndNext(
				session, segmentsExperiment, segmentsExperienceId,
				orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SegmentsExperiment getBySegmentsExperienceId_PrevAndNext(
		Session session, SegmentsExperiment segmentsExperiment,
		long segmentsExperienceId,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

		query.append(
			_FINDER_COLUMN_SEGMENTSEXPERIENCEID_SEGMENTSEXPERIENCEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(segmentsExperienceId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						segmentsExperiment)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<SegmentsExperiment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the segments experiments where segmentsExperienceId = &#63; from the database.
	 *
	 * @param segmentsExperienceId the segments experience ID
	 */
	@Override
	public void removeBySegmentsExperienceId(long segmentsExperienceId) {
		for (SegmentsExperiment segmentsExperiment :
				findBySegmentsExperienceId(
					segmentsExperienceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(segmentsExperiment);
		}
	}

	/**
	 * Returns the number of segments experiments where segmentsExperienceId = &#63;.
	 *
	 * @param segmentsExperienceId the segments experience ID
	 * @return the number of matching segments experiments
	 */
	@Override
	public int countBySegmentsExperienceId(long segmentsExperienceId) {
		FinderPath finderPath = _finderPathCountBySegmentsExperienceId;

		Object[] finderArgs = new Object[] {segmentsExperienceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SEGMENTSEXPERIMENT_WHERE);

			query.append(
				_FINDER_COLUMN_SEGMENTSEXPERIENCEID_SEGMENTSEXPERIENCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(segmentsExperienceId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_SEGMENTSEXPERIENCEID_SEGMENTSEXPERIENCEID_2 =
			"segmentsExperiment.segmentsExperienceId = ?";

	private FinderPath _finderPathFetchByG_S;
	private FinderPath _finderPathCountByG_S;

	/**
	 * Returns the segments experiment where groupId = &#63; and segmentsExperimentKey = &#63; or throws a <code>NoSuchExperimentException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperimentKey the segments experiment key
	 * @return the matching segments experiment
	 * @throws NoSuchExperimentException if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment findByG_S(
			long groupId, String segmentsExperimentKey)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByG_S(
			groupId, segmentsExperimentKey);

		if (segmentsExperiment == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", segmentsExperimentKey=");
			msg.append(segmentsExperimentKey);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchExperimentException(msg.toString());
		}

		return segmentsExperiment;
	}

	/**
	 * Returns the segments experiment where groupId = &#63; and segmentsExperimentKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperimentKey the segments experiment key
	 * @return the matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByG_S(
		long groupId, String segmentsExperimentKey) {

		return fetchByG_S(groupId, segmentsExperimentKey, true);
	}

	/**
	 * Returns the segments experiment where groupId = &#63; and segmentsExperimentKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperimentKey the segments experiment key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching segments experiment, or <code>null</code> if a matching segments experiment could not be found
	 */
	@Override
	public SegmentsExperiment fetchByG_S(
		long groupId, String segmentsExperimentKey, boolean retrieveFromCache) {

		segmentsExperimentKey = Objects.toString(segmentsExperimentKey, "");

		Object[] finderArgs = new Object[] {groupId, segmentsExperimentKey};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByG_S, finderArgs, this);
		}

		if (result instanceof SegmentsExperiment) {
			SegmentsExperiment segmentsExperiment = (SegmentsExperiment)result;

			if ((groupId != segmentsExperiment.getGroupId()) ||
				!Objects.equals(
					segmentsExperimentKey,
					segmentsExperiment.getSegmentsExperimentKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SEGMENTSEXPERIMENT_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			boolean bindSegmentsExperimentKey = false;

			if (segmentsExperimentKey.isEmpty()) {
				query.append(_FINDER_COLUMN_G_S_SEGMENTSEXPERIMENTKEY_3);
			}
			else {
				bindSegmentsExperimentKey = true;

				query.append(_FINDER_COLUMN_G_S_SEGMENTSEXPERIMENTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSegmentsExperimentKey) {
					qPos.add(segmentsExperimentKey);
				}

				List<SegmentsExperiment> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByG_S, finderArgs, list);
				}
				else {
					SegmentsExperiment segmentsExperiment = list.get(0);

					result = segmentsExperiment;

					cacheResult(segmentsExperiment);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByG_S, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SegmentsExperiment)result;
		}
	}

	/**
	 * Removes the segments experiment where groupId = &#63; and segmentsExperimentKey = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperimentKey the segments experiment key
	 * @return the segments experiment that was removed
	 */
	@Override
	public SegmentsExperiment removeByG_S(
			long groupId, String segmentsExperimentKey)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = findByG_S(
			groupId, segmentsExperimentKey);

		return remove(segmentsExperiment);
	}

	/**
	 * Returns the number of segments experiments where groupId = &#63; and segmentsExperimentKey = &#63;.
	 *
	 * @param groupId the group ID
	 * @param segmentsExperimentKey the segments experiment key
	 * @return the number of matching segments experiments
	 */
	@Override
	public int countByG_S(long groupId, String segmentsExperimentKey) {
		segmentsExperimentKey = Objects.toString(segmentsExperimentKey, "");

		FinderPath finderPath = _finderPathCountByG_S;

		Object[] finderArgs = new Object[] {groupId, segmentsExperimentKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SEGMENTSEXPERIMENT_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			boolean bindSegmentsExperimentKey = false;

			if (segmentsExperimentKey.isEmpty()) {
				query.append(_FINDER_COLUMN_G_S_SEGMENTSEXPERIMENTKEY_3);
			}
			else {
				bindSegmentsExperimentKey = true;

				query.append(_FINDER_COLUMN_G_S_SEGMENTSEXPERIMENTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSegmentsExperimentKey) {
					qPos.add(segmentsExperimentKey);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_S_GROUPID_2 =
		"segmentsExperiment.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_S_SEGMENTSEXPERIMENTKEY_2 =
		"segmentsExperiment.segmentsExperimentKey = ?";

	private static final String _FINDER_COLUMN_G_S_SEGMENTSEXPERIMENTKEY_3 =
		"(segmentsExperiment.segmentsExperimentKey IS NULL OR segmentsExperiment.segmentsExperimentKey = '')";

	public SegmentsExperimentPersistenceImpl() {
		setModelClass(SegmentsExperiment.class);

		setModelImplClass(SegmentsExperimentImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the segments experiment in the entity cache if it is enabled.
	 *
	 * @param segmentsExperiment the segments experiment
	 */
	@Override
	public void cacheResult(SegmentsExperiment segmentsExperiment) {
		entityCache.putResult(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentImpl.class, segmentsExperiment.getPrimaryKey(),
			segmentsExperiment);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				segmentsExperiment.getUuid(), segmentsExperiment.getGroupId()
			},
			segmentsExperiment);

		finderCache.putResult(
			_finderPathFetchByG_S,
			new Object[] {
				segmentsExperiment.getGroupId(),
				segmentsExperiment.getSegmentsExperimentKey()
			},
			segmentsExperiment);

		segmentsExperiment.resetOriginalValues();
	}

	/**
	 * Caches the segments experiments in the entity cache if it is enabled.
	 *
	 * @param segmentsExperiments the segments experiments
	 */
	@Override
	public void cacheResult(List<SegmentsExperiment> segmentsExperiments) {
		for (SegmentsExperiment segmentsExperiment : segmentsExperiments) {
			if (entityCache.getResult(
					SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
					SegmentsExperimentImpl.class,
					segmentsExperiment.getPrimaryKey()) == null) {

				cacheResult(segmentsExperiment);
			}
			else {
				segmentsExperiment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all segments experiments.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SegmentsExperimentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the segments experiment.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SegmentsExperiment segmentsExperiment) {
		entityCache.removeResult(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentImpl.class, segmentsExperiment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(SegmentsExperimentModelImpl)segmentsExperiment, true);
	}

	@Override
	public void clearCache(List<SegmentsExperiment> segmentsExperiments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SegmentsExperiment segmentsExperiment : segmentsExperiments) {
			entityCache.removeResult(
				SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
				SegmentsExperimentImpl.class,
				segmentsExperiment.getPrimaryKey());

			clearUniqueFindersCache(
				(SegmentsExperimentModelImpl)segmentsExperiment, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SegmentsExperimentModelImpl segmentsExperimentModelImpl) {

		Object[] args = new Object[] {
			segmentsExperimentModelImpl.getUuid(),
			segmentsExperimentModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, segmentsExperimentModelImpl, false);

		args = new Object[] {
			segmentsExperimentModelImpl.getGroupId(),
			segmentsExperimentModelImpl.getSegmentsExperimentKey()
		};

		finderCache.putResult(
			_finderPathCountByG_S, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByG_S, args, segmentsExperimentModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SegmentsExperimentModelImpl segmentsExperimentModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				segmentsExperimentModelImpl.getUuid(),
				segmentsExperimentModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((segmentsExperimentModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				segmentsExperimentModelImpl.getOriginalUuid(),
				segmentsExperimentModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				segmentsExperimentModelImpl.getGroupId(),
				segmentsExperimentModelImpl.getSegmentsExperimentKey()
			};

			finderCache.removeResult(_finderPathCountByG_S, args);
			finderCache.removeResult(_finderPathFetchByG_S, args);
		}

		if ((segmentsExperimentModelImpl.getColumnBitmask() &
			 _finderPathFetchByG_S.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				segmentsExperimentModelImpl.getOriginalGroupId(),
				segmentsExperimentModelImpl.getOriginalSegmentsExperimentKey()
			};

			finderCache.removeResult(_finderPathCountByG_S, args);
			finderCache.removeResult(_finderPathFetchByG_S, args);
		}
	}

	/**
	 * Creates a new segments experiment with the primary key. Does not add the segments experiment to the database.
	 *
	 * @param segmentsExperimentId the primary key for the new segments experiment
	 * @return the new segments experiment
	 */
	@Override
	public SegmentsExperiment create(long segmentsExperimentId) {
		SegmentsExperiment segmentsExperiment = new SegmentsExperimentImpl();

		segmentsExperiment.setNew(true);
		segmentsExperiment.setPrimaryKey(segmentsExperimentId);

		String uuid = PortalUUIDUtil.generate();

		segmentsExperiment.setUuid(uuid);

		segmentsExperiment.setCompanyId(CompanyThreadLocal.getCompanyId());

		return segmentsExperiment;
	}

	/**
	 * Removes the segments experiment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param segmentsExperimentId the primary key of the segments experiment
	 * @return the segments experiment that was removed
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment remove(long segmentsExperimentId)
		throws NoSuchExperimentException {

		return remove((Serializable)segmentsExperimentId);
	}

	/**
	 * Removes the segments experiment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the segments experiment
	 * @return the segments experiment that was removed
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment remove(Serializable primaryKey)
		throws NoSuchExperimentException {

		Session session = null;

		try {
			session = openSession();

			SegmentsExperiment segmentsExperiment =
				(SegmentsExperiment)session.get(
					SegmentsExperimentImpl.class, primaryKey);

			if (segmentsExperiment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExperimentException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(segmentsExperiment);
		}
		catch (NoSuchExperimentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SegmentsExperiment removeImpl(
		SegmentsExperiment segmentsExperiment) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(segmentsExperiment)) {
				segmentsExperiment = (SegmentsExperiment)session.get(
					SegmentsExperimentImpl.class,
					segmentsExperiment.getPrimaryKeyObj());
			}

			if (segmentsExperiment != null) {
				session.delete(segmentsExperiment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (segmentsExperiment != null) {
			clearCache(segmentsExperiment);
		}

		return segmentsExperiment;
	}

	@Override
	public SegmentsExperiment updateImpl(
		SegmentsExperiment segmentsExperiment) {

		boolean isNew = segmentsExperiment.isNew();

		if (!(segmentsExperiment instanceof SegmentsExperimentModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(segmentsExperiment.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					segmentsExperiment);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in segmentsExperiment proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SegmentsExperiment implementation " +
					segmentsExperiment.getClass());
		}

		SegmentsExperimentModelImpl segmentsExperimentModelImpl =
			(SegmentsExperimentModelImpl)segmentsExperiment;

		if (Validator.isNull(segmentsExperiment.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			segmentsExperiment.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (segmentsExperiment.getCreateDate() == null)) {
			if (serviceContext == null) {
				segmentsExperiment.setCreateDate(now);
			}
			else {
				segmentsExperiment.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!segmentsExperimentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				segmentsExperiment.setModifiedDate(now);
			}
			else {
				segmentsExperiment.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (segmentsExperiment.isNew()) {
				session.save(segmentsExperiment);

				segmentsExperiment.setNew(false);
			}
			else {
				segmentsExperiment = (SegmentsExperiment)session.merge(
					segmentsExperiment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SegmentsExperimentModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				segmentsExperimentModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				segmentsExperimentModelImpl.getUuid(),
				segmentsExperimentModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {segmentsExperimentModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				segmentsExperimentModelImpl.getSegmentsExperienceId()
			};

			finderCache.removeResult(
				_finderPathCountBySegmentsExperienceId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBySegmentsExperienceId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((segmentsExperimentModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					segmentsExperimentModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {segmentsExperimentModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((segmentsExperimentModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					segmentsExperimentModelImpl.getOriginalUuid(),
					segmentsExperimentModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					segmentsExperimentModelImpl.getUuid(),
					segmentsExperimentModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((segmentsExperimentModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					segmentsExperimentModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {segmentsExperimentModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((segmentsExperimentModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBySegmentsExperienceId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					segmentsExperimentModelImpl.
						getOriginalSegmentsExperienceId()
				};

				finderCache.removeResult(
					_finderPathCountBySegmentsExperienceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySegmentsExperienceId,
					args);

				args = new Object[] {
					segmentsExperimentModelImpl.getSegmentsExperienceId()
				};

				finderCache.removeResult(
					_finderPathCountBySegmentsExperienceId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySegmentsExperienceId,
					args);
			}
		}

		entityCache.putResult(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentImpl.class, segmentsExperiment.getPrimaryKey(),
			segmentsExperiment, false);

		clearUniqueFindersCache(segmentsExperimentModelImpl, false);
		cacheUniqueFindersCache(segmentsExperimentModelImpl);

		segmentsExperiment.resetOriginalValues();

		return segmentsExperiment;
	}

	/**
	 * Returns the segments experiment with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the segments experiment
	 * @return the segments experiment
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExperimentException {

		SegmentsExperiment segmentsExperiment = fetchByPrimaryKey(primaryKey);

		if (segmentsExperiment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExperimentException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return segmentsExperiment;
	}

	/**
	 * Returns the segments experiment with the primary key or throws a <code>NoSuchExperimentException</code> if it could not be found.
	 *
	 * @param segmentsExperimentId the primary key of the segments experiment
	 * @return the segments experiment
	 * @throws NoSuchExperimentException if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment findByPrimaryKey(long segmentsExperimentId)
		throws NoSuchExperimentException {

		return findByPrimaryKey((Serializable)segmentsExperimentId);
	}

	/**
	 * Returns the segments experiment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param segmentsExperimentId the primary key of the segments experiment
	 * @return the segments experiment, or <code>null</code> if a segments experiment with the primary key could not be found
	 */
	@Override
	public SegmentsExperiment fetchByPrimaryKey(long segmentsExperimentId) {
		return fetchByPrimaryKey((Serializable)segmentsExperimentId);
	}

	/**
	 * Returns all the segments experiments.
	 *
	 * @return the segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the segments experiments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @return the range of segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the segments experiments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findAll(
		int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the segments experiments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiments
	 * @param end the upper bound of the range of segments experiments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of segments experiments
	 */
	@Override
	public List<SegmentsExperiment> findAll(
		int start, int end,
		OrderByComparator<SegmentsExperiment> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<SegmentsExperiment> list = null;

		if (retrieveFromCache) {
			list = (List<SegmentsExperiment>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SEGMENTSEXPERIMENT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SEGMENTSEXPERIMENT;

				if (pagination) {
					sql = sql.concat(SegmentsExperimentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SegmentsExperiment>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the segments experiments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SegmentsExperiment segmentsExperiment : findAll()) {
			remove(segmentsExperiment);
		}
	}

	/**
	 * Returns the number of segments experiments.
	 *
	 * @return the number of segments experiments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SEGMENTSEXPERIMENT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "segmentsExperimentId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SEGMENTSEXPERIMENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SegmentsExperimentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the segments experiment persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			SegmentsExperimentModelImpl.UUID_COLUMN_BITMASK |
			SegmentsExperimentModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			SegmentsExperimentModelImpl.UUID_COLUMN_BITMASK |
			SegmentsExperimentModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			SegmentsExperimentModelImpl.UUID_COLUMN_BITMASK |
			SegmentsExperimentModelImpl.COMPANYID_COLUMN_BITMASK |
			SegmentsExperimentModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			SegmentsExperimentModelImpl.GROUPID_COLUMN_BITMASK |
			SegmentsExperimentModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindBySegmentsExperienceId = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySegmentsExperienceId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBySegmentsExperienceId = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySegmentsExperienceId", new String[] {Long.class.getName()},
			SegmentsExperimentModelImpl.SEGMENTSEXPERIENCEID_COLUMN_BITMASK |
			SegmentsExperimentModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountBySegmentsExperienceId = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySegmentsExperienceId", new String[] {Long.class.getName()});

		_finderPathFetchByG_S = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_S",
			new String[] {Long.class.getName(), String.class.getName()},
			SegmentsExperimentModelImpl.GROUPID_COLUMN_BITMASK |
			SegmentsExperimentModelImpl.SEGMENTSEXPERIMENTKEY_COLUMN_BITMASK);

		_finderPathCountByG_S = new FinderPath(
			SegmentsExperimentModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] {Long.class.getName(), String.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(SegmentsExperimentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SEGMENTSEXPERIMENT =
		"SELECT segmentsExperiment FROM SegmentsExperiment segmentsExperiment";

	private static final String _SQL_SELECT_SEGMENTSEXPERIMENT_WHERE =
		"SELECT segmentsExperiment FROM SegmentsExperiment segmentsExperiment WHERE ";

	private static final String _SQL_COUNT_SEGMENTSEXPERIMENT =
		"SELECT COUNT(segmentsExperiment) FROM SegmentsExperiment segmentsExperiment";

	private static final String _SQL_COUNT_SEGMENTSEXPERIMENT_WHERE =
		"SELECT COUNT(segmentsExperiment) FROM SegmentsExperiment segmentsExperiment WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "segmentsExperiment.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SegmentsExperiment exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SegmentsExperiment exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsExperimentPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}