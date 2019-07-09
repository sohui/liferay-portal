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

package com.liferay.talend.tliferayinput;

import com.liferay.talend.connection.LiferayConnectionResourceBaseProperties;
import com.liferay.talend.resource.LiferayInputResourceProperties;

import java.util.Collections;
import java.util.Set;

import org.talend.components.api.component.PropertyPathConnector;

/**
 * @author Zoltán Takács
 * @author Ivica Cardic
 */
public class TLiferayInputProperties
	extends LiferayConnectionResourceBaseProperties {

	public TLiferayInputProperties(String name) {
		super(name);
	}

	@Override
	public void setupProperties() {
		super.setupProperties();

		resource = new LiferayInputResourceProperties("resource");

		resource.connection = connection;

		resource.setupProperties();
	}

	@Override
	protected Set<PropertyPathConnector> getAllSchemaPropertiesConnectors(
		boolean outputConnectors) {

		if (outputConnectors) {
			return Collections.singleton(mainConnector);
		}

		return Collections.<PropertyPathConnector>emptySet();
	}

	private static final long serialVersionUID = 8010931662185868407L;

}