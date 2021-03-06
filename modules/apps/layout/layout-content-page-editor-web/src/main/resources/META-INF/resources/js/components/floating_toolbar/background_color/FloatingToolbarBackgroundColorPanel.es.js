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

import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import '../common/FloatingToolbarColorPicker.es';
import './FloatingToolbarBackgroundColorPanelDelegateTemplate.soy';
import {CONFIG_KEYS} from '../../../utils/rowConstants';
import {
	disableSavingChangesStatusAction,
	enableSavingChangesStatusAction,
	updateLastSaveDateAction
} from '../../../actions/saveChanges.es';
import getConnectedComponent from '../../../store/ConnectedComponent.es';
import templates from './FloatingToolbarBackgroundColorPanel.soy';
import {UPDATE_ROW_CONFIG} from '../../../actions/actions.es';

/**
 * FloatingToolbarBackgroundColorPanel
 */
class FloatingToolbarBackgroundColorPanel extends Component {
	/**
	 * Handle Clear button click
	 * @private
	 * @review
	 */
	_handleClearButtonClick() {
		this._updateRowConfig({
			[CONFIG_KEYS.backgroundColorCssClass]: ''
		});
	}

	/**
	 * Handle BackgroundColor button click
	 * @param {Event} event
	 * @private
	 * @review
	 */
	_handleBackgroundColorButtonClick(event) {
		this._updateRowConfig({
			[CONFIG_KEYS.backgroundColorCssClass]: event.color
		});
	}

	/**
	 * Updates row configuration
	 * @param {object} config Row configuration
	 * @private
	 * @review
	 */
	_updateRowConfig(config) {
		this.store
			.dispatch(enableSavingChangesStatusAction())
			.dispatch({
				config,
				rowId: this.itemId,
				type: UPDATE_ROW_CONFIG
			})
			.dispatch(updateLastSaveDateAction())
			.dispatch(disableSavingChangesStatusAction());
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FloatingToolbarBackgroundColorPanel.STATE = {
	/**
	 * @default undefined
	 * @memberof FloatingToolbarBackgroundColorPanel
	 * @review
	 * @type {!string}
	 */
	itemId: Config.string().required()
};

const ConnectedFloatingToolbarBackgroundColorPanel = getConnectedComponent(
	FloatingToolbarBackgroundColorPanel,
	['themeColorsCssClasses']
);

Soy.register(ConnectedFloatingToolbarBackgroundColorPanel, templates);

export {
	ConnectedFloatingToolbarBackgroundColorPanel,
	FloatingToolbarBackgroundColorPanel
};
export default ConnectedFloatingToolbarBackgroundColorPanel;
