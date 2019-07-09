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

package com.liferay.talend.connection;

import com.liferay.talend.resource.BaseLiferayResourceProperties;

import org.apache.avro.Schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;
import org.talend.components.common.FixedConnectorsComponentProperties;
import org.talend.daikon.properties.presentation.Form;

/**
 * @author Zoltán Takács
 * @author Ivica Cardic
 */
public abstract class LiferayConnectionResourceBaseProperties
	extends FixedConnectorsComponentProperties
	implements LiferayConnectionPropertiesProvider {

	public LiferayConnectionResourceBaseProperties(String name) {
		super(name);
	}

	/**
	 * This method returns the connection properties from the referenced
	 * connection component if it was specified by the user, otherwise the
	 * actual component's connection properties.
	 *
	 * @return LiferayConnectionProperties
	 */
	public LiferayConnectionProperties
		getEffectiveLiferayConnectionProperties() {

		LiferayConnectionProperties liferayConnectionProperties =
			getLiferayConnectionProperties();

		if (liferayConnectionProperties == null) {
			_log.error("LiferayConnectionProperties is null");
		}

		LiferayConnectionProperties referencedLiferayConnectionProperties =
			liferayConnectionProperties.getReferencedConnectionProperties();

		if (referencedLiferayConnectionProperties != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Using a reference connection properties");
				_log.debug(
					"API spec URL: " +
						referencedLiferayConnectionProperties.apiSpecURL.
							getValue());
				_log.debug(
					"User ID: " +
						referencedLiferayConnectionProperties.getUserId());
			}

			return referencedLiferayConnectionProperties;
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"API spec URL: " +
					liferayConnectionProperties.apiSpecURL.getValue());
			_log.debug("User ID: " + liferayConnectionProperties.getUserId());
		}

		return liferayConnectionProperties;
	}

	@Override
	public LiferayConnectionProperties getLiferayConnectionProperties() {
		return connection;
	}

	public Schema getSchema() {
		return resource.main.schema.getValue();
	}

	@Override
	public void refreshLayout(Form form) {
		super.refreshLayout(form);

		for (Form childForm : connection.getForms()) {
			connection.refreshLayout(childForm);
		}

		for (Form childForm : resource.getForms()) {
			resource.refreshLayout(childForm);
		}
	}

	@Override
	public void setupLayout() {
		super.setupLayout();

		Form mainForm = new Form(this, Form.MAIN);

		mainForm.addRow(connection.getForm(Form.REFERENCE));

		mainForm.addRow(resource.getForm(Form.REFERENCE));

		Form advancedForm = new Form(this, Form.ADVANCED);

		advancedForm.addRow(connection.getForm(Form.ADVANCED));

		refreshLayout(mainForm);
	}

	public LiferayConnectionProperties connection =
		new LiferayConnectionProperties("connection");
	public BaseLiferayResourceProperties resource;

	protected transient PropertyPathConnector mainConnector =
		new PropertyPathConnector(Connector.MAIN_NAME, "resource.main");

	private static final Logger _log = LoggerFactory.getLogger(
		LiferayConnectionResourceBaseProperties.class);

	private static final long serialVersionUID = 4534371813009904L;

}