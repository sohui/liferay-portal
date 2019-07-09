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

/**
 * Shows a dialog and handles the selected item.
 */
class ItemSelectorDialog extends Component {
	/**
	 * Close the dialog.
	 *
	 * @review
	 */
	close() {
		Liferay.Util.getWindow(this.eventName).hide();
	}

	/**
	 * Open the dialog.
	 *
	 * @review
	 */
	open() {
		this._currentItem = null;
		this._selectedItem = null;

		const eventName = this.eventName;
		const zIndex = this.zIndex;

		Liferay.Util.selectEntity(
			{
				dialog: {
					cssClass: this.dialogClasses,
					constrain: true,
					destroyOnHide: true,
					modal: true,
					on: {
						visibleChange: event => {
							if (!event.newVal) {
								this.selectedItem = this._selectedItem;

								this.emit('selectedItemChange', {
									selectedItem: this.selectedItem
								});
							}
						}
					},
					'toolbars.footer': [
						{
							cssClass: 'btn-link close-modal',
							id: 'cancelButton',
							label: this.buttonCancelLabel,
							on: {
								click: () => {
									this.close();
								}
							}
						},
						{
							cssClass: 'btn-primary',
							disabled: true,
							id: 'addButton',
							label: this.buttonAddLabel,
							on: {
								click: () => {
									this._selectedItem = this._currentItem;
									this.close();
								}
							}
						}
					],
					zIndex
				},
				eventName,
				id: eventName,
				stack: !zIndex,
				title: this.title,
				uri: this.url
			},
			this._onItemSelected.bind(this)
		);
	}

	/**
	 * Saves the current item that has been selected in the dialog,
	 * and disables the Add button is is empty.
	 *
	 * @param {EventFacade} event
	 *
	 * @private
	 * @review
	 */
	_onItemSelected(event) {
		const currentItem = event.data;

		const dialog = Liferay.Util.getWindow(this.eventName);

		const addButton = dialog
			.getToolbar('footer')
			.get('boundingBox')
			.one('#addButton');

		Liferay.Util.toggleDisabled(addButton, !currentItem);

		this._currentItem = currentItem;
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */

ItemSelectorDialog.STATE = {
	/**
	 * Label for the Add button
	 *
	 * @instance
	 * @review
	 * @type {String}
	 */
	buttonAddLabel: Config.string().value(Liferay.Language.get('add')),

	/**
	 * Label for the Cancel button
	 *
	 * @instance
	 * @review
	 * @type {String}
	 */
	buttonCancelLabel: Config.string().value(Liferay.Language.get('cancel')),

	/**
	 * Css classes to pass to the dialog.
	 *
	 * @instance
	 * @review
	 * @type {String}
	 */
	dialogClasses: Config.string(),

	/**
	 * Event name
	 *
	 * @instance
	 * @review
	 * @type {String}
	 */
	eventName: Config.string().required(),

	/**
	 * The selected item(s) in the dialog.
	 *
	 * @instance
	 * @review
	 * @type {Object|Object[]}
	 */
	selectedItem: Config.oneOfType([
		Config.object(),
		Config.arrayOf(Config.object())
	]),

	/**
	 * Dialog's title
	 *
	 * @instance
	 * @review
	 * @type {String}
	 */
	title: Config.string().value(Liferay.Language.get('select-file')),

	/**
	 * Dialog's zIndex
	 *
	 * @instance
	 * @review
	 * @type {Number}
	 */
	zIndex: Config.number(),

	/**
	 * Url that will open the dialog.
	 *
	 * @instance
	 * @review
	 * @type {String}
	 */
	url: Config.string().required()
};

export default ItemSelectorDialog;
