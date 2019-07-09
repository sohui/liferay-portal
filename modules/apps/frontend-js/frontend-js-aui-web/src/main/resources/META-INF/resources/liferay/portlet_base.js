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
	'liferay-portlet-base',
	function(A) {
		var PortletBase = function(config) {
			var instance = this;

			var namespace;

			if ('namespace' in config) {
				namespace = config.namespace;
			} else {
				namespace = A.guid();
			}

			instance.NS = namespace;
			instance.ID = namespace.replace(/^_(.*)_$/, '$1');

			if (config.rootNode) {
				instance._setRootNode(config.rootNode);
			}
		};

		PortletBase.ATTRS = {
			namespace: {
				getter: '_getNS',
				writeOnce: true
			},
			rootNode: {
				getter: '_getRootNode',
				setter: '_setRootNode',
				valueFn: function() {
					var instance = this;

					return A.one('#p_p_id' + instance.NS);
				}
			}
		};

		PortletBase.prototype = {
			all: function(selector, root) {
				var instance = this;

				root = A.one(root) || instance.rootNode || A;

				return root.all(
					instance._formatSelectorNS(instance.NS, selector)
				);
			},

			byId: function(id) {
				var instance = this;

				return A.one('#' + A.Lang.String.prefix(instance.NS, id));
			},

			ns: function(str) {
				var instance = this;

				return Liferay.Util.ns(instance.NS, str);
			},

			one: function(selector, root) {
				var instance = this;

				root = A.one(root) || instance.rootNode || A;

				return root.one(
					instance._formatSelectorNS(instance.NS, selector)
				);
			},

			_formatSelectorNS: function(ns, selector) {
				return selector.replace(
					A.DOM._getRegExp('(#|\\[id=(\\"|\\\'))(?!' + ns + ')', 'g'),
					'$1' + ns
				);
			},

			_getNS: function(value) {
				var instance = this;

				return instance.NS;
			},

			_getRootNode: function(value) {
				var instance = this;

				return instance.rootNode;
			},

			_setRootNode: function(value) {
				var instance = this;

				var rootNode = A.one(value);

				instance.rootNode = rootNode;

				return rootNode;
			}
		};

		Liferay.PortletBase = PortletBase;
	},
	'',
	{
		requires: ['aui-base']
	}
);
