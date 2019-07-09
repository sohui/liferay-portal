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

package com.liferay.document.library.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DLFileVersion service. Represents a row in the &quot;DLFileVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portlet.documentlibrary.model.impl.DLFileVersionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portlet.documentlibrary.model.impl.DLFileVersionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileVersion
 * @generated
 */
@ProviderType
public interface DLFileVersionModel
	extends BaseModel<DLFileVersion>, ShardedModel, StagedGroupedModel,
			WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a document library file version model instance should use the {@link DLFileVersion} interface instead.
	 */

	/**
	 * Returns the primary key of this document library file version.
	 *
	 * @return the primary key of this document library file version
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this document library file version.
	 *
	 * @param primaryKey the primary key of this document library file version
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this document library file version.
	 *
	 * @return the uuid of this document library file version
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this document library file version.
	 *
	 * @param uuid the uuid of this document library file version
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the file version ID of this document library file version.
	 *
	 * @return the file version ID of this document library file version
	 */
	public long getFileVersionId();

	/**
	 * Sets the file version ID of this document library file version.
	 *
	 * @param fileVersionId the file version ID of this document library file version
	 */
	public void setFileVersionId(long fileVersionId);

	/**
	 * Returns the group ID of this document library file version.
	 *
	 * @return the group ID of this document library file version
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this document library file version.
	 *
	 * @param groupId the group ID of this document library file version
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this document library file version.
	 *
	 * @return the company ID of this document library file version
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this document library file version.
	 *
	 * @param companyId the company ID of this document library file version
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this document library file version.
	 *
	 * @return the user ID of this document library file version
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this document library file version.
	 *
	 * @param userId the user ID of this document library file version
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this document library file version.
	 *
	 * @return the user uuid of this document library file version
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this document library file version.
	 *
	 * @param userUuid the user uuid of this document library file version
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this document library file version.
	 *
	 * @return the user name of this document library file version
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this document library file version.
	 *
	 * @param userName the user name of this document library file version
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this document library file version.
	 *
	 * @return the create date of this document library file version
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this document library file version.
	 *
	 * @param createDate the create date of this document library file version
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this document library file version.
	 *
	 * @return the modified date of this document library file version
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this document library file version.
	 *
	 * @param modifiedDate the modified date of this document library file version
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the repository ID of this document library file version.
	 *
	 * @return the repository ID of this document library file version
	 */
	public long getRepositoryId();

	/**
	 * Sets the repository ID of this document library file version.
	 *
	 * @param repositoryId the repository ID of this document library file version
	 */
	public void setRepositoryId(long repositoryId);

	/**
	 * Returns the folder ID of this document library file version.
	 *
	 * @return the folder ID of this document library file version
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this document library file version.
	 *
	 * @param folderId the folder ID of this document library file version
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the file entry ID of this document library file version.
	 *
	 * @return the file entry ID of this document library file version
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this document library file version.
	 *
	 * @param fileEntryId the file entry ID of this document library file version
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the tree path of this document library file version.
	 *
	 * @return the tree path of this document library file version
	 */
	@AutoEscape
	public String getTreePath();

	/**
	 * Sets the tree path of this document library file version.
	 *
	 * @param treePath the tree path of this document library file version
	 */
	public void setTreePath(String treePath);

	/**
	 * Returns the file name of this document library file version.
	 *
	 * @return the file name of this document library file version
	 */
	@AutoEscape
	public String getFileName();

	/**
	 * Sets the file name of this document library file version.
	 *
	 * @param fileName the file name of this document library file version
	 */
	public void setFileName(String fileName);

	/**
	 * Returns the extension of this document library file version.
	 *
	 * @return the extension of this document library file version
	 */
	@AutoEscape
	public String getExtension();

	/**
	 * Sets the extension of this document library file version.
	 *
	 * @param extension the extension of this document library file version
	 */
	public void setExtension(String extension);

	/**
	 * Returns the mime type of this document library file version.
	 *
	 * @return the mime type of this document library file version
	 */
	@AutoEscape
	public String getMimeType();

	/**
	 * Sets the mime type of this document library file version.
	 *
	 * @param mimeType the mime type of this document library file version
	 */
	public void setMimeType(String mimeType);

	/**
	 * Returns the title of this document library file version.
	 *
	 * @return the title of this document library file version
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this document library file version.
	 *
	 * @param title the title of this document library file version
	 */
	public void setTitle(String title);

	/**
	 * Returns the description of this document library file version.
	 *
	 * @return the description of this document library file version
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this document library file version.
	 *
	 * @param description the description of this document library file version
	 */
	public void setDescription(String description);

	/**
	 * Returns the change log of this document library file version.
	 *
	 * @return the change log of this document library file version
	 */
	@AutoEscape
	public String getChangeLog();

	/**
	 * Sets the change log of this document library file version.
	 *
	 * @param changeLog the change log of this document library file version
	 */
	public void setChangeLog(String changeLog);

	/**
	 * Returns the extra settings of this document library file version.
	 *
	 * @return the extra settings of this document library file version
	 */
	@AutoEscape
	public String getExtraSettings();

	/**
	 * Sets the extra settings of this document library file version.
	 *
	 * @param extraSettings the extra settings of this document library file version
	 */
	public void setExtraSettings(String extraSettings);

	/**
	 * Returns the file entry type ID of this document library file version.
	 *
	 * @return the file entry type ID of this document library file version
	 */
	public long getFileEntryTypeId();

	/**
	 * Sets the file entry type ID of this document library file version.
	 *
	 * @param fileEntryTypeId the file entry type ID of this document library file version
	 */
	public void setFileEntryTypeId(long fileEntryTypeId);

	/**
	 * Returns the version of this document library file version.
	 *
	 * @return the version of this document library file version
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this document library file version.
	 *
	 * @param version the version of this document library file version
	 */
	public void setVersion(String version);

	/**
	 * Returns the size of this document library file version.
	 *
	 * @return the size of this document library file version
	 */
	public long getSize();

	/**
	 * Sets the size of this document library file version.
	 *
	 * @param size the size of this document library file version
	 */
	public void setSize(long size);

	/**
	 * Returns the checksum of this document library file version.
	 *
	 * @return the checksum of this document library file version
	 */
	@AutoEscape
	public String getChecksum();

	/**
	 * Sets the checksum of this document library file version.
	 *
	 * @param checksum the checksum of this document library file version
	 */
	public void setChecksum(String checksum);

	/**
	 * Returns the last publish date of this document library file version.
	 *
	 * @return the last publish date of this document library file version
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this document library file version.
	 *
	 * @param lastPublishDate the last publish date of this document library file version
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this document library file version.
	 *
	 * @return the status of this document library file version
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this document library file version.
	 *
	 * @param status the status of this document library file version
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this document library file version.
	 *
	 * @return the status by user ID of this document library file version
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this document library file version.
	 *
	 * @param statusByUserId the status by user ID of this document library file version
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this document library file version.
	 *
	 * @return the status by user uuid of this document library file version
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this document library file version.
	 *
	 * @param statusByUserUuid the status by user uuid of this document library file version
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this document library file version.
	 *
	 * @return the status by user name of this document library file version
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this document library file version.
	 *
	 * @param statusByUserName the status by user name of this document library file version
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this document library file version.
	 *
	 * @return the status date of this document library file version
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this document library file version.
	 *
	 * @param statusDate the status date of this document library file version
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this document library file version is approved.
	 *
	 * @return <code>true</code> if this document library file version is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this document library file version is denied.
	 *
	 * @return <code>true</code> if this document library file version is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this document library file version is a draft.
	 *
	 * @return <code>true</code> if this document library file version is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this document library file version is expired.
	 *
	 * @return <code>true</code> if this document library file version is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this document library file version is inactive.
	 *
	 * @return <code>true</code> if this document library file version is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this document library file version is incomplete.
	 *
	 * @return <code>true</code> if this document library file version is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this document library file version is pending.
	 *
	 * @return <code>true</code> if this document library file version is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this document library file version is scheduled.
	 *
	 * @return <code>true</code> if this document library file version is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

}