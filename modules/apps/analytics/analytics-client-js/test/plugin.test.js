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

import AnalyticsClient from '../src/analytics';
import {assert} from 'chai';
import fetchMock from 'fetch-mock';

let Analytics;

describe('Analytics Plugin Integration', () => {
	afterEach(() => {
		Analytics.reset();
		Analytics.dispose();
	});

	beforeEach(() => {
		fetchMock.mock('*', () => 200);
		Analytics = AnalyticsClient.create();
	});

	describe('.registerPlugin', () => {
		it('should be exposed as an Analytics static method', () => {
			Analytics.registerPlugin.should.be.a('function');
		});

		it('should process the given plugin and execute its initialisation logic', function() {
			const plugin = analytics => {
				analytics.should.be.equal(Analytics);
			};

			const spy = sinon.spy(plugin);

			Analytics.registerPlugin(spy);
			assert.isTrue(spy.calledOnce);
		});
	});
});
