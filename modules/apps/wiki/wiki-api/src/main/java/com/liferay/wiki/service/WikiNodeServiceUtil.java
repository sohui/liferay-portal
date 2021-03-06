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

package com.liferay.wiki.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for WikiNode. This utility wraps
 * <code>com.liferay.wiki.service.impl.WikiNodeServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see WikiNodeService
 * @generated
 */
@ProviderType
public class WikiNodeServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.wiki.service.impl.WikiNodeServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.wiki.model.WikiNode addNode(
			String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addNode(name, description, serviceContext);
	}

	public static void deleteNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteNode(nodeId);
	}

	public static com.liferay.wiki.model.WikiNode getNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getNode(nodeId);
	}

	public static com.liferay.wiki.model.WikiNode getNode(
			long groupId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getNode(groupId, name);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getNodes(groupId);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
			long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getNodes(groupId, status);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
		long groupId, int start, int end) {

		return getService().getNodes(groupId, start, end);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
		long groupId, int status, int start, int end) {

		return getService().getNodes(groupId, status, start, end);
	}

	public static java.util.List<com.liferay.wiki.model.WikiNode> getNodes(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.wiki.model.WikiNode> obc) {

		return getService().getNodes(groupId, status, start, end, obc);
	}

	public static int getNodesCount(long groupId) {
		return getService().getNodesCount(groupId);
	}

	public static int getNodesCount(long groupId, int status) {
		return getService().getNodesCount(groupId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void importPages(
			long nodeId, String importer, java.io.InputStream[] inputStreams,
			java.util.Map<String, String[]> options)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().importPages(nodeId, importer, inputStreams, options);
	}

	public static com.liferay.wiki.model.WikiNode moveNodeToTrash(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().moveNodeToTrash(nodeId);
	}

	public static void restoreNodeFromTrash(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().restoreNodeFromTrash(nodeId);
	}

	public static void subscribeNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().subscribeNode(nodeId);
	}

	public static void unsubscribeNode(long nodeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().unsubscribeNode(nodeId);
	}

	public static com.liferay.wiki.model.WikiNode updateNode(
			long nodeId, String name, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateNode(
			nodeId, name, description, serviceContext);
	}

	public static WikiNodeService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WikiNodeService, WikiNodeService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WikiNodeService.class);

		ServiceTracker<WikiNodeService, WikiNodeService> serviceTracker =
			new ServiceTracker<WikiNodeService, WikiNodeService>(
				bundle.getBundleContext(), WikiNodeService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}