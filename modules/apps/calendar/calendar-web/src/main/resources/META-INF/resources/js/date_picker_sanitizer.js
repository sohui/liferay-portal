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
	'liferay-calendar-date-picker-sanitizer',
	function(A) {
		var AArray = A.Array;

		var DateMath = A.DataType.DateMath;

		var DatePickerSanitizer = A.Component.create({
			ATTRS: {
				datePickers: {},

				defaultDate: {},

				maximumDate: {},

				minimumDate: {}
			},

			EXTENDS: A.Base,

			NAME: 'date-picker-sanitizer',

			prototype: {
				initializer: function(config) {
					var instance = this;

					instance.eventHandlers = [];

					instance.bindUI();
				},

				bindUI: function() {
					var instance = this;

					var datePickers = instance.get('datePickers');

					instance.eventHandlers = A.map(datePickers, function(item) {
						return item.on(
							'selectionChange',
							A.bind(
								instance._onDatePickerSelectionChange,
								instance
							)
						);
					});
				},

				destructor: function() {
					var instance = this;

					instance.unlink();

					instance.eventHandlers = null;
				},

				unlink: function() {
					var instance = this;

					AArray.invoke(instance.eventHandlers, 'detach');
				},

				_onDatePickerSelectionChange: function _onDatePickerSelectionChange(
					event
				) {
					var instance = this;

					var date = event.newSelection[0];

					var datePicker = event.currentTarget;

					var defaultDate = instance.get('defaultDate');

					var maximumDate = instance.get('maximumDate');

					var minimumDate = instance.get('minimumDate');

					if (
						date &&
						!DateMath.between(date, minimumDate, maximumDate)
					) {
						event.halt();
						event.newSelection.pop();

						datePicker.deselectDates();
						datePicker.selectDates([defaultDate]);
					}
				}
			}
		});

		Liferay.DatePickerSanitizer = DatePickerSanitizer;
	},
	'',
	{
		requires: ['aui-base', 'aui-datatype']
	}
);
