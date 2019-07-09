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

import CustomObject from './CustomObject.es';
import CustomObjectsList from './CustomObjectsList.es';
import React from 'react';
import {Route, HashRouter as Router, Switch} from 'react-router-dom';

export default function App() {
	return (
		<div className='container-fluid container-fluid-max-xl main-content-body'>
			<Router>
				<Switch>
					<Route exact path='/' component={CustomObjectsList} />

					<Route
						path='/custom-object/:dataDefinitionId'
						component={CustomObject}
					/>
				</Switch>
			</Router>
		</div>
	);
}
