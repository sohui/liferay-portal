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
	'liferay-search-sort-configuration',
	function(A) {
		var SortConfiguration = function(form) {
			var instance = this;

			instance.form = form;

			instance.form.on('submit', A.bind(instance._onSubmit, instance));
		};

		A.mix(SortConfiguration.prototype, {
			_onSubmit: function(event) {
				var instance = this;

				event.preventDefault();

				var fields = [];

				var fieldFormRows = A.all('.field-form-row').filter(function(
					item
				) {
					return !item.get('hidden');
				});

				fieldFormRows.each(function(item) {
					var label = item.one('.label-input').val();

					var field = item.one('.sort-field-input').val();

					fields.push({
						label: label,
						field: field
					});
				});

				instance.form.one('.fields-input').val(JSON.stringify(fields));

				submitForm(instance.form);
			}
		});

		Liferay.namespace('Search').SortConfiguration = SortConfiguration;
	},
	'',
	{
		requires: ['aui-node']
	}
);
