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
	'liferay-widget-zindex',
	function(A) {
		var STR_HOST = 'host';

		var WidgetZIndex = A.Component.create({
			EXTENDS: A.Plugin.Base,

			NAME: 'widgetzindex',

			NS: 'zindex',

			prototype: {
				initializer: function() {
					var instance = this;

					var host = instance.get(STR_HOST);

					if (!host.get('rendered') && host.get('visible')) {
						instance._setHostZIndex();
					}

					instance.onHostEvent('visibleChange', function(event) {
						if (event.newVal) {
							instance._setHostZIndex();
						}
					});
				},

				_setHostZIndex: function() {
					var instance = this;

					instance
						.get(STR_HOST)
						.set('zIndex', ++Liferay.zIndex.WINDOW);
				}
			}
		});

		Liferay.WidgetZIndex = WidgetZIndex;
	},
	'',
	{
		requires: ['aui-modal', 'plugin']
	}
);
