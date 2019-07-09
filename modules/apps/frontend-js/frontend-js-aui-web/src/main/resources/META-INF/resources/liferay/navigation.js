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
	'liferay-navigation',
	function(A) {
		var ANode = A.Node;
		var Lang = A.Lang;

		var STR_EMPTY = '';

		var TPL_LINK = '<a href="{url}">{pageTitle}</a>';

		var TPL_TAB_LINK =
			'<a href="{url}">' + '<span>{pageTitle}</span>' + '</a>';

		/**
		 * OPTIONS
		 *
		 * Required
		 * navBlock {string|object}: A selector or DOM element of the navigation.
		 */

		var Navigation = A.Component.create({
			ATTRS: {
				navBlock: {
					lazyAdd: false,
					setter: function(value) {
						var instance = this;

						value = A.one(value);

						if (!value) {
							value = A.Attribute.INVALID_VALUE;
						}

						return value;
					},
					value: null
				}
			},

			EXTENDS: A.Base,

			NAME: 'navigation',

			prototype: {
				initializer: function() {
					var instance = this;

					var navBlock = instance.get('navBlock');

					if (navBlock) {
						var navListSelector =
							Liferay.Data.NAV_LIST_SELECTOR || '> ul';

						var navItemSelector =
							Liferay.Data.NAV_ITEM_SELECTOR ||
							navListSelector + '> li';

						var navItemChildToggleSelector =
							Liferay.Data.NAV_ITEM_CHILD_TOGGLE_SELECTOR ||
							'> span';

						var navList = navBlock.one(navListSelector);

						instance._navItemChildToggleSelector = navItemChildToggleSelector;
						instance._navItemSelector = navItemSelector;
						instance._navListSelector = navListSelector;

						instance._navList = navList;

						instance._tempTab = instance._createTempTab(
							TPL_TAB_LINK
						);
						instance._tempChildTab = instance._createTempTab(
							TPL_LINK
						);
					}
				},

				_createTempTab: function(tpl) {
					var instance = this;

					var tempLink = Lang.sub(tpl, {
						pageTitle: STR_EMPTY,
						url: '#'
					});

					var tempTab = ANode.create('<li>');

					tempTab.append(tempLink);

					return tempTab;
				}
			}
		});

		Liferay.Navigation = Navigation;
	},
	'',
	{
		requires: ['aui-component', 'event-mouseenter']
	}
);
