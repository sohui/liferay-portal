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

package com.liferay.dynamic.data.mapping.service.base;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateVersionPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ImagePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the base implementation for the ddm template local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMTemplateLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class DDMTemplateLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements DDMTemplateLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DDMTemplateLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil</code>.
	 */

	/**
	 * Adds the ddm template to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplate the ddm template
	 * @return the ddm template that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMTemplate addDDMTemplate(DDMTemplate ddmTemplate) {
		ddmTemplate.setNew(true);

		return ddmTemplatePersistence.update(ddmTemplate);
	}

	/**
	 * Creates a new ddm template with the primary key. Does not add the ddm template to the database.
	 *
	 * @param templateId the primary key for the new ddm template
	 * @return the new ddm template
	 */
	@Override
	@Transactional(enabled = false)
	public DDMTemplate createDDMTemplate(long templateId) {
		return ddmTemplatePersistence.create(templateId);
	}

	/**
	 * Deletes the ddm template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param templateId the primary key of the ddm template
	 * @return the ddm template that was removed
	 * @throws PortalException if a ddm template with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMTemplate deleteDDMTemplate(long templateId)
		throws PortalException {

		return ddmTemplatePersistence.remove(templateId);
	}

	/**
	 * Deletes the ddm template from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplate the ddm template
	 * @return the ddm template that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMTemplate deleteDDMTemplate(DDMTemplate ddmTemplate) {
		return ddmTemplatePersistence.remove(ddmTemplate);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			DDMTemplate.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return ddmTemplatePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return ddmTemplatePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return ddmTemplatePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return ddmTemplatePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return ddmTemplatePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DDMTemplate fetchDDMTemplate(long templateId) {
		return ddmTemplatePersistence.fetchByPrimaryKey(templateId);
	}

	/**
	 * Returns the ddm template matching the UUID and group.
	 *
	 * @param uuid the ddm template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddm template, or <code>null</code> if a matching ddm template could not be found
	 */
	@Override
	public DDMTemplate fetchDDMTemplateByUuidAndGroupId(
		String uuid, long groupId) {

		return ddmTemplatePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the ddm template with the primary key.
	 *
	 * @param templateId the primary key of the ddm template
	 * @return the ddm template
	 * @throws PortalException if a ddm template with the primary key could not be found
	 */
	@Override
	public DDMTemplate getDDMTemplate(long templateId) throws PortalException {
		return ddmTemplatePersistence.findByPrimaryKey(templateId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(ddmTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("templateId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			ddmTemplateLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DDMTemplate.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("templateId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(ddmTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("templateId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DDMTemplate>() {

				@Override
				public void performAction(DDMTemplate ddmTemplate)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, ddmTemplate);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(DDMTemplate.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return ddmTemplateLocalService.deleteDDMTemplate(
			(DDMTemplate)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return ddmTemplatePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the ddm templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddm templates
	 * @param companyId the primary key of the company
	 * @return the matching ddm templates, or an empty list if no matches were found
	 */
	@Override
	public List<DDMTemplate> getDDMTemplatesByUuidAndCompanyId(
		String uuid, long companyId) {

		return ddmTemplatePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of ddm templates matching the UUID and company.
	 *
	 * @param uuid the UUID of the ddm templates
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching ddm templates, or an empty list if no matches were found
	 */
	@Override
	public List<DDMTemplate> getDDMTemplatesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator) {

		return ddmTemplatePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the ddm template matching the UUID and group.
	 *
	 * @param uuid the ddm template's UUID
	 * @param groupId the primary key of the group
	 * @return the matching ddm template
	 * @throws PortalException if a matching ddm template could not be found
	 */
	@Override
	public DDMTemplate getDDMTemplateByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return ddmTemplatePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the ddm templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm templates
	 * @param end the upper bound of the range of ddm templates (not inclusive)
	 * @return the range of ddm templates
	 */
	@Override
	public List<DDMTemplate> getDDMTemplates(int start, int end) {
		return ddmTemplatePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of ddm templates.
	 *
	 * @return the number of ddm templates
	 */
	@Override
	public int getDDMTemplatesCount() {
		return ddmTemplatePersistence.countAll();
	}

	/**
	 * Updates the ddm template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmTemplate the ddm template
	 * @return the ddm template that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMTemplate updateDDMTemplate(DDMTemplate ddmTemplate) {
		return ddmTemplatePersistence.update(ddmTemplate);
	}

	/**
	 * Returns the ddm template local service.
	 *
	 * @return the ddm template local service
	 */
	public DDMTemplateLocalService getDDMTemplateLocalService() {
		return ddmTemplateLocalService;
	}

	/**
	 * Sets the ddm template local service.
	 *
	 * @param ddmTemplateLocalService the ddm template local service
	 */
	public void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {

		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	/**
	 * Returns the ddm template persistence.
	 *
	 * @return the ddm template persistence
	 */
	public DDMTemplatePersistence getDDMTemplatePersistence() {
		return ddmTemplatePersistence;
	}

	/**
	 * Sets the ddm template persistence.
	 *
	 * @param ddmTemplatePersistence the ddm template persistence
	 */
	public void setDDMTemplatePersistence(
		DDMTemplatePersistence ddmTemplatePersistence) {

		this.ddmTemplatePersistence = ddmTemplatePersistence;
	}

	/**
	 * Returns the ddm template finder.
	 *
	 * @return the ddm template finder
	 */
	public DDMTemplateFinder getDDMTemplateFinder() {
		return ddmTemplateFinder;
	}

	/**
	 * Sets the ddm template finder.
	 *
	 * @param ddmTemplateFinder the ddm template finder
	 */
	public void setDDMTemplateFinder(DDMTemplateFinder ddmTemplateFinder) {
		this.ddmTemplateFinder = ddmTemplateFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public com.liferay.portal.kernel.service.ImageLocalService
		getImageLocalService() {

		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(
		com.liferay.portal.kernel.service.ImageLocalService imageLocalService) {

		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the ddm template link local service.
	 *
	 * @return the ddm template link local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService
		getDDMTemplateLinkLocalService() {

		return ddmTemplateLinkLocalService;
	}

	/**
	 * Sets the ddm template link local service.
	 *
	 * @param ddmTemplateLinkLocalService the ddm template link local service
	 */
	public void setDDMTemplateLinkLocalService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService
			ddmTemplateLinkLocalService) {

		this.ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	/**
	 * Returns the ddm template link persistence.
	 *
	 * @return the ddm template link persistence
	 */
	public DDMTemplateLinkPersistence getDDMTemplateLinkPersistence() {
		return ddmTemplateLinkPersistence;
	}

	/**
	 * Sets the ddm template link persistence.
	 *
	 * @param ddmTemplateLinkPersistence the ddm template link persistence
	 */
	public void setDDMTemplateLinkPersistence(
		DDMTemplateLinkPersistence ddmTemplateLinkPersistence) {

		this.ddmTemplateLinkPersistence = ddmTemplateLinkPersistence;
	}

	/**
	 * Returns the ddm template version local service.
	 *
	 * @return the ddm template version local service
	 */
	public
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService
			getDDMTemplateVersionLocalService() {

		return ddmTemplateVersionLocalService;
	}

	/**
	 * Sets the ddm template version local service.
	 *
	 * @param ddmTemplateVersionLocalService the ddm template version local service
	 */
	public void setDDMTemplateVersionLocalService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService
			ddmTemplateVersionLocalService) {

		this.ddmTemplateVersionLocalService = ddmTemplateVersionLocalService;
	}

	/**
	 * Returns the ddm template version persistence.
	 *
	 * @return the ddm template version persistence
	 */
	public DDMTemplateVersionPersistence getDDMTemplateVersionPersistence() {
		return ddmTemplateVersionPersistence;
	}

	/**
	 * Sets the ddm template version persistence.
	 *
	 * @param ddmTemplateVersionPersistence the ddm template version persistence
	 */
	public void setDDMTemplateVersionPersistence(
		DDMTemplateVersionPersistence ddmTemplateVersionPersistence) {

		this.ddmTemplateVersionPersistence = ddmTemplateVersionPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.dynamic.data.mapping.model.DDMTemplate",
			ddmTemplateLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.dynamic.data.mapping.model.DDMTemplate");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMTemplateLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMTemplate.class;
	}

	protected String getModelClassName() {
		return DDMTemplate.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmTemplatePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = DDMTemplateLocalService.class)
	protected DDMTemplateLocalService ddmTemplateLocalService;

	@BeanReference(type = DDMTemplatePersistence.class)
	protected DDMTemplatePersistence ddmTemplatePersistence;

	@BeanReference(type = DDMTemplateFinder.class)
	protected DDMTemplateFinder ddmTemplateFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ImageLocalService.class
	)
	protected com.liferay.portal.kernel.service.ImageLocalService
		imageLocalService;

	@ServiceReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService
			ddmTemplateLinkLocalService;

	@BeanReference(type = DDMTemplateLinkPersistence.class)
	protected DDMTemplateLinkPersistence ddmTemplateLinkPersistence;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService
			ddmTemplateVersionLocalService;

	@BeanReference(type = DDMTemplateVersionPersistence.class)
	protected DDMTemplateVersionPersistence ddmTemplateVersionPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}