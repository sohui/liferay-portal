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

/* eslint no-unused-vars: "warn" */

import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';
import {Modal, openToast} from 'frontend-js-web';
import templates from './ContentsAffected.soy';

/**
 * Handles the Change Lists Contents Affected dialog.
 */
class ContentsAffected extends Component {
	created() {
		this._fetchAffectedContents();
	}

	_fetchAffectedContents(page) {
		const headers = new Headers();
		headers.append('Content-Type', 'application/json');
		headers.append('X-CSRF-Token', Liferay.authToken);

		const init = {
			credentials: 'include',
			headers,
			method: 'GET'
		};

		var keywords = '';

		const input = document.querySelector('#filterKeywords');

		if (input) {
			keywords = input.value;
		}

		if (!page) {
			page = 1;
		}

		const queryParam =
			'?pageSize=' +
			this.pageSize +
			(keywords.length > 0 ? '&keywords=' + keywords : '') +
			(page > 1 ? '&page=' + page : '');

		const url = this.urlAffectedContents + queryParam;

		fetch(url, init)
			.then(r => r.json())
			.then(response => this._populateAffectedContents(response))
			.catch(error => {
				const message =
					typeof error === 'string'
						? error
						: Liferay.Util.sub(
								Liferay.Language.get(
									'an-error-occured-while-getting-data-from-x'
								),
								url
						  );

				openToast({
					message,
					title: Liferay.Language.get('error'),
					type: 'danger'
				});
			});
	}

	static _getKeywords() {
		let keywords = '';

		const input = document.querySelector('#filterKeywords');

		if (input) {
			keywords = input.value;
		}

		return keywords;
	}

	_handleClickNextPage(event) {
		if (this.page + 1 <= this.lastPage) {
			this._fetchAffectedContents(this.page + 1);
		}
	}

	_handleClickPageNumber(event) {
		const page = event.target.getAttribute('data-page');

		if (page >= 1 && page <= this.lastPage) {
			this._fetchAffectedContents(page);
		}
	}

	_handleClickPreviousPage(event) {
		if (this.page - 1 >= 1) {
			this._fetchAffectedContents(this.page - 1);
		}
	}

	_handleClickSearch(event) {
		this._fetchAffectedContents();
	}

	_handleCloseDialogClick(event) {
		this.refs.modal.visible = false;
	}

	_handleSearchFormKeyUp(event) {
		this._fetchAffectedContents();
	}

	_populateAffectedContents(affectedContentsResult) {
		this.affectedContents = [];

		this.page = affectedContentsResult.page;
		this.lastPage = affectedContentsResult.lastPage;
		this.totalCount = affectedContentsResult.totalCount;

		this.startPosition = (this.page - 1) * this.pageSize + 1;
		this.endPosition = this.page * this.pageSize;

		if (affectedContentsResult.items) {
			affectedContentsResult.items.forEach(affectedContent => {
				const entityNameTranslation = this.entityNameTranslations.find(
					entityNameTranslation =>
						entityNameTranslation.key ===
						affectedContent.contentType
				);

				this.affectedContents.push({
					contentType: entityNameTranslation.translation,
					title: affectedContent.title
				});
			});
		}
	}
}

/**
 * State definition.
 *
 * @ignore
 * @static
 * @type {!Object}
 */
ContentsAffected.STATE = {
	/**
	 * Path to the images.
	 *
	 * @instance
	 * @memberOf ContentsAffected
	 * @type {String}
	 */
	spritemap: Config.string().required(),

	/**
	 * Affected Contents for the currently selected change tracking entry.
	 *
	 * @default undefined
	 * @instance
	 * @memberOf ContentsAffected
	 * @type {object}
	 */
	affectedContents: Config.arrayOf(
		Config.shapeOf({
			contentType: Config.string(),
			title: Config.string()
		})
	),

	endPosition: Config.number().value(10),

	/**
	 * JSON array of translation properties.
	 *
	 * @default
	 * @instance
	 * @memberOf Overview
	 * @type {object}
	 */
	entityNameTranslations: Config.arrayOf(
		Config.shapeOf({
			key: Config.string(),
			translation: Config.string()
		})
	),

	lastPage: Config.number().value(1),

	page: Config.number().value(1),

	pageSize: Config.number().value(10),

	startPosition: Config.number().value(1),

	totalCount: Config.number().value(0),

	urlAffectedContents: Config.string().required()
};

// Register component

Soy.register(ContentsAffected, templates);

export {ContentsAffected};
export default ContentsAffected;
