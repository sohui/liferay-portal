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

package com.liferay.site.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.security.permission.RolePermissions;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.persistence.GroupFinder;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.ResourcePermissionTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserGroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.comparator.GroupNameComparator;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alberto Chaparro
 * @author László Csontos
 */
@RunWith(Arquillian.class)
public class GroupFinderTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), TransactionalTestRule.INSTANCE);

	@Test
	public void testFindByActiveGroupIds() throws Exception {
		List<Long> groups = _groupFinder.findByActiveGroupIds(
			TestPropsValues.getUserId());

		Assert.assertFalse(groups.toString(), groups.isEmpty());
	}

	@Test
	public void testFindByC_C_N_DJoinByRoleResourcePermissions()
		throws Exception {

		_group = GroupTestUtil.addGroup();

		List<ResourceAction> resourceActions =
			_resourceActionLocalService.getResourceActions(0, 1);

		_arbitraryResourceAction = resourceActions.get(0);

		_resourcePermission = ResourcePermissionTestUtil.addResourcePermission(
			_arbitraryResourceAction.getBitwiseValue(),
			_arbitraryResourceAction.getName(),
			String.valueOf(_group.getGroupId()), ResourceConstants.SCOPE_GROUP);

		LinkedHashMap<String, Object> groupParams = new LinkedHashMap<>();

		groupParams.put(
			"rolePermissions",
			new RolePermissions(
				_resourcePermission.getName(), ResourceConstants.SCOPE_GROUP,
				_arbitraryResourceAction.getActionId(),
				_resourcePermission.getRoleId()));

		List<Group> groups = _groupFinder.findByC_C_PG_N_D(
			TestPropsValues.getCompanyId(),
			new long[] {_portal.getClassNameId(Group.class)},
			GroupConstants.ANY_PARENT_GROUP_ID, new String[] {null},
			new String[] {null}, groupParams, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		Assert.assertTrue(
			"The method findByC_C_N_D should have returned the group " +
				_group.getGroupId(),
			groups.removeIf(
				group -> group.getGroupId() == _group.getGroupId()));
	}

	@Test
	public void testFindByC_C_PG_N_D() throws Exception {
		_userGroup = UserGroupTestUtil.addUserGroup();
		_user = UserTestUtil.addUser();

		_userGroupLocalService.addUserUserGroup(_user.getUserId(), _userGroup);

		_organization = OrganizationTestUtil.addOrganization(true);

		Group group = _organization.getGroup();

		_groupLocalService.addUserGroupGroup(
			_userGroup.getUserGroupId(), group.getGroupId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("inherit", true);
		params.put("usersGroups", _user.getUserId());

		List<Group> groups = _groupFinder.findByC_C_PG_N_D(
			_organization.getCompanyId(), null,
			GroupConstants.DEFAULT_PARENT_GROUP_ID, null, null, params, true,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertTrue(groups.toString(), groups.contains(group));
	}

	@Test
	public void testFindByCompanyId() throws Exception {
		LinkedHashMap<String, Object> groupParams = new LinkedHashMap<>();

		groupParams.put("inherit", Boolean.TRUE);
		groupParams.put("site", Boolean.TRUE);
		groupParams.put("usersGroups", TestPropsValues.getUserId());

		List<Group> groups = _groupFinder.findByCompanyId(
			TestPropsValues.getCompanyId(), groupParams, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new GroupNameComparator(true));

		Assert.assertFalse(groups.toString(), groups.isEmpty());
	}

	@Test
	public void testFindByCompanyIdByUserGroupGroup() throws Exception {
		_userGroup = UserGroupTestUtil.addUserGroup();
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser();

		_groupLocalService.addUserGroupGroup(
			_userGroup.getUserGroupId(), _group.getGroupId());

		_userGroupLocalService.addUserUserGroup(_user.getUserId(), _userGroup);

		LinkedHashMap<String, Object> groupParams = new LinkedHashMap<>();

		groupParams.put("inherit", Boolean.TRUE);
		groupParams.put("site", Boolean.TRUE);
		groupParams.put("usersGroups", _user.getUserId());

		List<Group> groups = _groupFinder.findByCompanyId(
			TestPropsValues.getCompanyId(), groupParams, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new GroupNameComparator(true));

		boolean exists = false;

		for (Group group : groups) {
			if (group.getGroupId() == _group.getGroupId()) {
				exists = true;

				break;
			}
		}

		Assert.assertTrue(
			"The method findByCompanyId should have returned the group " +
				_group.getGroupId(),
			exists);
	}

	@Test
	public void testFindByLayouts1() throws Exception {
		List<Group> groups = _findByLayouts(
			GroupConstants.DEFAULT_PARENT_GROUP_ID);

		int initialGroupCount = groups.size();

		Group parentGroup = GroupTestUtil.addGroup();

		_groups.addFirst(parentGroup);

		LayoutTestUtil.addLayout(parentGroup, false);

		Group childGroup1 = GroupTestUtil.addGroup(parentGroup.getGroupId());

		_groups.addFirst(childGroup1);

		LayoutTestUtil.addLayout(childGroup1, false);

		Group childGroup2 = GroupTestUtil.addGroup(parentGroup.getGroupId());

		_groups.addFirst(childGroup2);

		LayoutTestUtil.addLayout(childGroup2, true);

		groups = _findByLayouts(GroupConstants.DEFAULT_PARENT_GROUP_ID);

		Assert.assertEquals(
			groups.toString(), initialGroupCount + 1, groups.size());

		groups = _findByLayouts(parentGroup.getGroupId());

		Assert.assertEquals(groups.toString(), 2, groups.size());

		groups = _findByLayouts(childGroup1.getGroupId());

		Assert.assertTrue(groups.toString(), groups.isEmpty());
	}

	@Test
	public void testFindByLayouts2() throws Exception {
		int initialGroupCount = _groupFinder.countByLayouts(
			TestPropsValues.getCompanyId(),
			GroupConstants.DEFAULT_PARENT_GROUP_ID, true, true);

		Group parentGroup = GroupTestUtil.addGroup();

		_groups.addFirst(parentGroup);

		LayoutTestUtil.addLayout(parentGroup, false);

		Group childGroup1 = GroupTestUtil.addGroup(parentGroup.getGroupId());

		_groups.addFirst(childGroup1);

		LayoutTestUtil.addLayout(childGroup1, false);

		Group childGroup2 = GroupTestUtil.addGroup(parentGroup.getGroupId());

		_groups.addFirst(childGroup2);

		LayoutTestUtil.addLayout(childGroup2, true);

		_groupLocalService.updateGroup(
			parentGroup.getGroupId(), parentGroup.getParentGroupId(),
			parentGroup.getNameMap(), parentGroup.getDescriptionMap(),
			parentGroup.getType(), parentGroup.isManualMembership(),
			parentGroup.getMembershipRestriction(),
			parentGroup.getFriendlyURL(), parentGroup.isInheritContent(), false,
			ServiceContextTestUtil.getServiceContext());

		List<Group> groups = _groupFinder.findByLayouts(
			TestPropsValues.getCompanyId(),
			GroupConstants.DEFAULT_PARENT_GROUP_ID, true, true,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new GroupNameComparator(true));

		Assert.assertEquals(
			groups.toString(), initialGroupCount, groups.size());

		groups = _groupFinder.findByLayouts(
			TestPropsValues.getCompanyId(), parentGroup.getGroupId(), true,
			true, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new GroupNameComparator(true));

		Assert.assertEquals(groups.toString(), 2, groups.size());
	}

	private List<Group> _findByLayouts(long parentGroupId) throws Exception {
		return _groupFinder.findByLayouts(
			TestPropsValues.getCompanyId(), parentGroupId, true,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new GroupNameComparator(true));
	}

	private ResourceAction _arbitraryResourceAction;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private GroupFinder _groupFinder;

	@Inject
	private GroupLocalService _groupLocalService;

	@DeleteAfterTestRun
	private final LinkedList<Group> _groups = new LinkedList<>();

	@DeleteAfterTestRun
	private Organization _organization;

	@Inject
	private Portal _portal;

	@Inject
	private ResourceActionLocalService _resourceActionLocalService;

	@DeleteAfterTestRun
	private ResourcePermission _resourcePermission;

	@DeleteAfterTestRun
	private User _user;

	@DeleteAfterTestRun
	private UserGroup _userGroup;

	@Inject
	private UserGroupLocalService _userGroupLocalService;

}