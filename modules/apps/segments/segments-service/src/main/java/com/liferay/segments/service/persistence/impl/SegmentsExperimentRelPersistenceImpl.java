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
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.segments.exception.NoSuchExperimentRelException;
import com.liferay.segments.model.SegmentsExperimentRel;
import com.liferay.segments.model.impl.SegmentsExperimentRelImpl;
import com.liferay.segments.model.impl.SegmentsExperimentRelModelImpl;
import com.liferay.segments.service.persistence.SegmentsExperimentRelPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence implementation for the segments experiment rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Garcia
 * @generated
 */
@ProviderType
public class SegmentsExperimentRelPersistenceImpl
	extends BasePersistenceImpl<SegmentsExperimentRel>
	implements SegmentsExperimentRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SegmentsExperimentRelUtil</code> to access the segments experiment rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SegmentsExperimentRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBySegmentsExperimentId;
	private FinderPath _finderPathWithoutPaginationFindBySegmentsExperimentId;
	private FinderPath _finderPathCountBySegmentsExperimentId;

	/**
	 * Returns all the segments experiment rels where segmentsExperimentId = &#63;.
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @return the matching segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findBySegmentsExperimentId(
		long segmentsExperimentId) {

		return findBySegmentsExperimentId(
			segmentsExperimentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the segments experiment rels where segmentsExperimentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @param start the lower bound of the range of segments experiment rels
	 * @param end the upper bound of the range of segments experiment rels (not inclusive)
	 * @return the range of matching segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findBySegmentsExperimentId(
		long segmentsExperimentId, int start, int end) {

		return findBySegmentsExperimentId(
			segmentsExperimentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the segments experiment rels where segmentsExperimentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @param start the lower bound of the range of segments experiment rels
	 * @param end the upper bound of the range of segments experiment rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findBySegmentsExperimentId(
		long segmentsExperimentId, int start, int end,
		OrderByComparator<SegmentsExperimentRel> orderByComparator) {

		return findBySegmentsExperimentId(
			segmentsExperimentId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the segments experiment rels where segmentsExperimentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @param start the lower bound of the range of segments experiment rels
	 * @param end the upper bound of the range of segments experiment rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findBySegmentsExperimentId(
		long segmentsExperimentId, int start, int end,
		OrderByComparator<SegmentsExperimentRel> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindBySegmentsExperimentId;
			finderArgs = new Object[] {segmentsExperimentId};
		}
		else {
			finderPath = _finderPathWithPaginationFindBySegmentsExperimentId;
			finderArgs = new Object[] {
				segmentsExperimentId, start, end, orderByComparator
			};
		}

		List<SegmentsExperimentRel> list = null;

		if (retrieveFromCache) {
			list = (List<SegmentsExperimentRel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SegmentsExperimentRel segmentsExperimentRel : list) {
					if ((segmentsExperimentId !=
							segmentsExperimentRel.getSegmentsExperimentId())) {

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

			query.append(_SQL_SELECT_SEGMENTSEXPERIMENTREL_WHERE);

			query.append(
				_FINDER_COLUMN_SEGMENTSEXPERIMENTID_SEGMENTSEXPERIMENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(SegmentsExperimentRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(segmentsExperimentId);

				if (!pagination) {
					list = (List<SegmentsExperimentRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SegmentsExperimentRel>)QueryUtil.list(
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
	 * Returns the first segments experiment rel in the ordered set where segmentsExperimentId = &#63;.
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment rel
	 * @throws NoSuchExperimentRelException if a matching segments experiment rel could not be found
	 */
	@Override
	public SegmentsExperimentRel findBySegmentsExperimentId_First(
			long segmentsExperimentId,
			OrderByComparator<SegmentsExperimentRel> orderByComparator)
		throws NoSuchExperimentRelException {

		SegmentsExperimentRel segmentsExperimentRel =
			fetchBySegmentsExperimentId_First(
				segmentsExperimentId, orderByComparator);

		if (segmentsExperimentRel != null) {
			return segmentsExperimentRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("segmentsExperimentId=");
		msg.append(segmentsExperimentId);

		msg.append("}");

		throw new NoSuchExperimentRelException(msg.toString());
	}

	/**
	 * Returns the first segments experiment rel in the ordered set where segmentsExperimentId = &#63;.
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching segments experiment rel, or <code>null</code> if a matching segments experiment rel could not be found
	 */
	@Override
	public SegmentsExperimentRel fetchBySegmentsExperimentId_First(
		long segmentsExperimentId,
		OrderByComparator<SegmentsExperimentRel> orderByComparator) {

		List<SegmentsExperimentRel> list = findBySegmentsExperimentId(
			segmentsExperimentId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last segments experiment rel in the ordered set where segmentsExperimentId = &#63;.
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment rel
	 * @throws NoSuchExperimentRelException if a matching segments experiment rel could not be found
	 */
	@Override
	public SegmentsExperimentRel findBySegmentsExperimentId_Last(
			long segmentsExperimentId,
			OrderByComparator<SegmentsExperimentRel> orderByComparator)
		throws NoSuchExperimentRelException {

		SegmentsExperimentRel segmentsExperimentRel =
			fetchBySegmentsExperimentId_Last(
				segmentsExperimentId, orderByComparator);

		if (segmentsExperimentRel != null) {
			return segmentsExperimentRel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("segmentsExperimentId=");
		msg.append(segmentsExperimentId);

		msg.append("}");

		throw new NoSuchExperimentRelException(msg.toString());
	}

	/**
	 * Returns the last segments experiment rel in the ordered set where segmentsExperimentId = &#63;.
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching segments experiment rel, or <code>null</code> if a matching segments experiment rel could not be found
	 */
	@Override
	public SegmentsExperimentRel fetchBySegmentsExperimentId_Last(
		long segmentsExperimentId,
		OrderByComparator<SegmentsExperimentRel> orderByComparator) {

		int count = countBySegmentsExperimentId(segmentsExperimentId);

		if (count == 0) {
			return null;
		}

		List<SegmentsExperimentRel> list = findBySegmentsExperimentId(
			segmentsExperimentId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the segments experiment rels before and after the current segments experiment rel in the ordered set where segmentsExperimentId = &#63;.
	 *
	 * @param segmentsExperimentRelId the primary key of the current segments experiment rel
	 * @param segmentsExperimentId the segments experiment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next segments experiment rel
	 * @throws NoSuchExperimentRelException if a segments experiment rel with the primary key could not be found
	 */
	@Override
	public SegmentsExperimentRel[] findBySegmentsExperimentId_PrevAndNext(
			long segmentsExperimentRelId, long segmentsExperimentId,
			OrderByComparator<SegmentsExperimentRel> orderByComparator)
		throws NoSuchExperimentRelException {

		SegmentsExperimentRel segmentsExperimentRel = findByPrimaryKey(
			segmentsExperimentRelId);

		Session session = null;

		try {
			session = openSession();

			SegmentsExperimentRel[] array = new SegmentsExperimentRelImpl[3];

			array[0] = getBySegmentsExperimentId_PrevAndNext(
				session, segmentsExperimentRel, segmentsExperimentId,
				orderByComparator, true);

			array[1] = segmentsExperimentRel;

			array[2] = getBySegmentsExperimentId_PrevAndNext(
				session, segmentsExperimentRel, segmentsExperimentId,
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

	protected SegmentsExperimentRel getBySegmentsExperimentId_PrevAndNext(
		Session session, SegmentsExperimentRel segmentsExperimentRel,
		long segmentsExperimentId,
		OrderByComparator<SegmentsExperimentRel> orderByComparator,
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

		query.append(_SQL_SELECT_SEGMENTSEXPERIMENTREL_WHERE);

		query.append(
			_FINDER_COLUMN_SEGMENTSEXPERIMENTID_SEGMENTSEXPERIMENTID_2);

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
			query.append(SegmentsExperimentRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(segmentsExperimentId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						segmentsExperimentRel)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<SegmentsExperimentRel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the segments experiment rels where segmentsExperimentId = &#63; from the database.
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 */
	@Override
	public void removeBySegmentsExperimentId(long segmentsExperimentId) {
		for (SegmentsExperimentRel segmentsExperimentRel :
				findBySegmentsExperimentId(
					segmentsExperimentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(segmentsExperimentRel);
		}
	}

	/**
	 * Returns the number of segments experiment rels where segmentsExperimentId = &#63;.
	 *
	 * @param segmentsExperimentId the segments experiment ID
	 * @return the number of matching segments experiment rels
	 */
	@Override
	public int countBySegmentsExperimentId(long segmentsExperimentId) {
		FinderPath finderPath = _finderPathCountBySegmentsExperimentId;

		Object[] finderArgs = new Object[] {segmentsExperimentId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SEGMENTSEXPERIMENTREL_WHERE);

			query.append(
				_FINDER_COLUMN_SEGMENTSEXPERIMENTID_SEGMENTSEXPERIMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(segmentsExperimentId);

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
		_FINDER_COLUMN_SEGMENTSEXPERIMENTID_SEGMENTSEXPERIMENTID_2 =
			"segmentsExperimentRel.segmentsExperimentId = ?";

	public SegmentsExperimentRelPersistenceImpl() {
		setModelClass(SegmentsExperimentRel.class);

		setModelImplClass(SegmentsExperimentRelImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED);
	}

	/**
	 * Caches the segments experiment rel in the entity cache if it is enabled.
	 *
	 * @param segmentsExperimentRel the segments experiment rel
	 */
	@Override
	public void cacheResult(SegmentsExperimentRel segmentsExperimentRel) {
		entityCache.putResult(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelImpl.class,
			segmentsExperimentRel.getPrimaryKey(), segmentsExperimentRel);

		segmentsExperimentRel.resetOriginalValues();
	}

	/**
	 * Caches the segments experiment rels in the entity cache if it is enabled.
	 *
	 * @param segmentsExperimentRels the segments experiment rels
	 */
	@Override
	public void cacheResult(
		List<SegmentsExperimentRel> segmentsExperimentRels) {

		for (SegmentsExperimentRel segmentsExperimentRel :
				segmentsExperimentRels) {

			if (entityCache.getResult(
					SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
					SegmentsExperimentRelImpl.class,
					segmentsExperimentRel.getPrimaryKey()) == null) {

				cacheResult(segmentsExperimentRel);
			}
			else {
				segmentsExperimentRel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all segments experiment rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SegmentsExperimentRelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the segments experiment rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SegmentsExperimentRel segmentsExperimentRel) {
		entityCache.removeResult(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelImpl.class,
			segmentsExperimentRel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SegmentsExperimentRel> segmentsExperimentRels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SegmentsExperimentRel segmentsExperimentRel :
				segmentsExperimentRels) {

			entityCache.removeResult(
				SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
				SegmentsExperimentRelImpl.class,
				segmentsExperimentRel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new segments experiment rel with the primary key. Does not add the segments experiment rel to the database.
	 *
	 * @param segmentsExperimentRelId the primary key for the new segments experiment rel
	 * @return the new segments experiment rel
	 */
	@Override
	public SegmentsExperimentRel create(long segmentsExperimentRelId) {
		SegmentsExperimentRel segmentsExperimentRel =
			new SegmentsExperimentRelImpl();

		segmentsExperimentRel.setNew(true);
		segmentsExperimentRel.setPrimaryKey(segmentsExperimentRelId);

		segmentsExperimentRel.setCompanyId(CompanyThreadLocal.getCompanyId());

		return segmentsExperimentRel;
	}

	/**
	 * Removes the segments experiment rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param segmentsExperimentRelId the primary key of the segments experiment rel
	 * @return the segments experiment rel that was removed
	 * @throws NoSuchExperimentRelException if a segments experiment rel with the primary key could not be found
	 */
	@Override
	public SegmentsExperimentRel remove(long segmentsExperimentRelId)
		throws NoSuchExperimentRelException {

		return remove((Serializable)segmentsExperimentRelId);
	}

	/**
	 * Removes the segments experiment rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the segments experiment rel
	 * @return the segments experiment rel that was removed
	 * @throws NoSuchExperimentRelException if a segments experiment rel with the primary key could not be found
	 */
	@Override
	public SegmentsExperimentRel remove(Serializable primaryKey)
		throws NoSuchExperimentRelException {

		Session session = null;

		try {
			session = openSession();

			SegmentsExperimentRel segmentsExperimentRel =
				(SegmentsExperimentRel)session.get(
					SegmentsExperimentRelImpl.class, primaryKey);

			if (segmentsExperimentRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExperimentRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(segmentsExperimentRel);
		}
		catch (NoSuchExperimentRelException nsee) {
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
	protected SegmentsExperimentRel removeImpl(
		SegmentsExperimentRel segmentsExperimentRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(segmentsExperimentRel)) {
				segmentsExperimentRel = (SegmentsExperimentRel)session.get(
					SegmentsExperimentRelImpl.class,
					segmentsExperimentRel.getPrimaryKeyObj());
			}

			if (segmentsExperimentRel != null) {
				session.delete(segmentsExperimentRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (segmentsExperimentRel != null) {
			clearCache(segmentsExperimentRel);
		}

		return segmentsExperimentRel;
	}

	@Override
	public SegmentsExperimentRel updateImpl(
		SegmentsExperimentRel segmentsExperimentRel) {

		boolean isNew = segmentsExperimentRel.isNew();

		if (!(segmentsExperimentRel instanceof
				SegmentsExperimentRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(segmentsExperimentRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					segmentsExperimentRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in segmentsExperimentRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SegmentsExperimentRel implementation " +
					segmentsExperimentRel.getClass());
		}

		SegmentsExperimentRelModelImpl segmentsExperimentRelModelImpl =
			(SegmentsExperimentRelModelImpl)segmentsExperimentRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (segmentsExperimentRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				segmentsExperimentRel.setCreateDate(now);
			}
			else {
				segmentsExperimentRel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!segmentsExperimentRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				segmentsExperimentRel.setModifiedDate(now);
			}
			else {
				segmentsExperimentRel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (segmentsExperimentRel.isNew()) {
				session.save(segmentsExperimentRel);

				segmentsExperimentRel.setNew(false);
			}
			else {
				segmentsExperimentRel = (SegmentsExperimentRel)session.merge(
					segmentsExperimentRel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SegmentsExperimentRelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				segmentsExperimentRelModelImpl.getSegmentsExperimentId()
			};

			finderCache.removeResult(
				_finderPathCountBySegmentsExperimentId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBySegmentsExperimentId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((segmentsExperimentRelModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBySegmentsExperimentId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					segmentsExperimentRelModelImpl.
						getOriginalSegmentsExperimentId()
				};

				finderCache.removeResult(
					_finderPathCountBySegmentsExperimentId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySegmentsExperimentId,
					args);

				args = new Object[] {
					segmentsExperimentRelModelImpl.getSegmentsExperimentId()
				};

				finderCache.removeResult(
					_finderPathCountBySegmentsExperimentId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySegmentsExperimentId,
					args);
			}
		}

		entityCache.putResult(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelImpl.class,
			segmentsExperimentRel.getPrimaryKey(), segmentsExperimentRel,
			false);

		segmentsExperimentRel.resetOriginalValues();

		return segmentsExperimentRel;
	}

	/**
	 * Returns the segments experiment rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the segments experiment rel
	 * @return the segments experiment rel
	 * @throws NoSuchExperimentRelException if a segments experiment rel with the primary key could not be found
	 */
	@Override
	public SegmentsExperimentRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExperimentRelException {

		SegmentsExperimentRel segmentsExperimentRel = fetchByPrimaryKey(
			primaryKey);

		if (segmentsExperimentRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExperimentRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return segmentsExperimentRel;
	}

	/**
	 * Returns the segments experiment rel with the primary key or throws a <code>NoSuchExperimentRelException</code> if it could not be found.
	 *
	 * @param segmentsExperimentRelId the primary key of the segments experiment rel
	 * @return the segments experiment rel
	 * @throws NoSuchExperimentRelException if a segments experiment rel with the primary key could not be found
	 */
	@Override
	public SegmentsExperimentRel findByPrimaryKey(long segmentsExperimentRelId)
		throws NoSuchExperimentRelException {

		return findByPrimaryKey((Serializable)segmentsExperimentRelId);
	}

	/**
	 * Returns the segments experiment rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param segmentsExperimentRelId the primary key of the segments experiment rel
	 * @return the segments experiment rel, or <code>null</code> if a segments experiment rel with the primary key could not be found
	 */
	@Override
	public SegmentsExperimentRel fetchByPrimaryKey(
		long segmentsExperimentRelId) {

		return fetchByPrimaryKey((Serializable)segmentsExperimentRelId);
	}

	/**
	 * Returns all the segments experiment rels.
	 *
	 * @return the segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the segments experiment rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiment rels
	 * @param end the upper bound of the range of segments experiment rels (not inclusive)
	 * @return the range of segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the segments experiment rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiment rels
	 * @param end the upper bound of the range of segments experiment rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findAll(
		int start, int end,
		OrderByComparator<SegmentsExperimentRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the segments experiment rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SegmentsExperimentRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of segments experiment rels
	 * @param end the upper bound of the range of segments experiment rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of segments experiment rels
	 */
	@Override
	public List<SegmentsExperimentRel> findAll(
		int start, int end,
		OrderByComparator<SegmentsExperimentRel> orderByComparator,
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

		List<SegmentsExperimentRel> list = null;

		if (retrieveFromCache) {
			list = (List<SegmentsExperimentRel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SEGMENTSEXPERIMENTREL);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SEGMENTSEXPERIMENTREL;

				if (pagination) {
					sql = sql.concat(
						SegmentsExperimentRelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SegmentsExperimentRel>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SegmentsExperimentRel>)QueryUtil.list(
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
	 * Removes all the segments experiment rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SegmentsExperimentRel segmentsExperimentRel : findAll()) {
			remove(segmentsExperimentRel);
		}
	}

	/**
	 * Returns the number of segments experiment rels.
	 *
	 * @return the number of segments experiment rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SEGMENTSEXPERIMENTREL);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "segmentsExperimentRelId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SEGMENTSEXPERIMENTREL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SegmentsExperimentRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the segments experiment rel persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindBySegmentsExperimentId = new FinderPath(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySegmentsExperimentId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBySegmentsExperimentId = new FinderPath(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelModelImpl.FINDER_CACHE_ENABLED,
			SegmentsExperimentRelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySegmentsExperimentId", new String[] {Long.class.getName()},
			SegmentsExperimentRelModelImpl.SEGMENTSEXPERIMENTID_COLUMN_BITMASK);

		_finderPathCountBySegmentsExperimentId = new FinderPath(
			SegmentsExperimentRelModelImpl.ENTITY_CACHE_ENABLED,
			SegmentsExperimentRelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySegmentsExperimentId", new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(SegmentsExperimentRelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SEGMENTSEXPERIMENTREL =
		"SELECT segmentsExperimentRel FROM SegmentsExperimentRel segmentsExperimentRel";

	private static final String _SQL_SELECT_SEGMENTSEXPERIMENTREL_WHERE =
		"SELECT segmentsExperimentRel FROM SegmentsExperimentRel segmentsExperimentRel WHERE ";

	private static final String _SQL_COUNT_SEGMENTSEXPERIMENTREL =
		"SELECT COUNT(segmentsExperimentRel) FROM SegmentsExperimentRel segmentsExperimentRel";

	private static final String _SQL_COUNT_SEGMENTSEXPERIMENTREL_WHERE =
		"SELECT COUNT(segmentsExperimentRel) FROM SegmentsExperimentRel segmentsExperimentRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"segmentsExperimentRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SegmentsExperimentRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SegmentsExperimentRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsExperimentRelPersistenceImpl.class);

}