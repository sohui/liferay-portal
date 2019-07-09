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

AUI.add(
	'liferay-calendar-session-listener',
	function(A) {
		var CalendarSessionListener = A.Component.create({
			ATTRS: {
				calendars: {
					value: []
				},

				scheduler: {
					value: null
				}
			},

			NAME: 'calendar-session-listener',

			prototype: {
				initializer: function() {
					var instance = this;

					Liferay.on(
						'sessionExpired',
						A.bind(instance._onSessionExpired, instance)
					);
				},

				_disableCalendars: function() {
					var instance = this;

					var calendars = instance.get('calendars');

					A.Object.each(calendars, function(calendar) {
						var permissions = calendar.get('permissions');

						permissions.DELETE = false;
						permissions.MANAGE_BOOKINGS = false;
						permissions.UPDATE = false;
						permissions.PERMISSIONS = false;
					});
				},

				_disableEvents: function() {
					var instance = this;

					var scheduler = instance.get('scheduler');

					scheduler.getEvents().forEach(function(event) {
						event.set('disabled', true);
					});
				},

				_disableScheduler: function() {
					var instance = this;

					var addEventButtons = A.all('.calendar-add-event-btn');

					var scheduler = instance.get('scheduler');

					addEventButtons.set('disabled', true);

					scheduler.set('eventRecorder', null);
				},

				_onSessionExpired: function() {
					var instance = this;

					instance._disableCalendars();

					instance._disableScheduler();

					instance._disableEvents();
				}
			}
		});

		Liferay.CalendarSessionListener = CalendarSessionListener;
	},
	'',
	{
		requires: ['aui-base', 'aui-component']
	}
);
