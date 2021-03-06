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

import ClayButton from 'clay-button';
import Component from 'metal-jsx';
import Notifications from '../../util/Notifications.es';
import {Config} from 'metal-state';

class PreviewButton extends Component {
	preview() {
		const {resolvePreviewURL} = this.props;

		return resolvePreviewURL()
			.then(previewURL => {
				window.open(previewURL, '_blank');

				return previewURL;
			})
			.catch(() => {
				Notifications.showError(
					Liferay.Language.get('your-request-failed-to-complete')
				);
			});
	}

	render() {
		const {spritemap} = this.props;

		return (
			<ClayButton
				elementClasses={'btn-default'}
				events={{
					click: this._handleButtonClicked.bind(this)
				}}
				label={Liferay.Language.get('preview-form')}
				ref={'button'}
				spritemap={spritemap}
				style={'link'}
			/>
		);
	}

	_handleButtonClicked() {
		this.preview();
	}
}

PreviewButton.PROPS = {
	resolvePreviewURL: Config.func().required(),
	spritemap: Config.string().required()
};

export default PreviewButton;
