/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import React from 'react/addons';
import {assign} from 'lodash';
import RateActions from '../stores/RateActions';
import Styles from './shared/Styles';

export class EditControls extends React.Component {
	constructor(props) {
		super(props);
		this.state = { setAllValues : '' };
		this.updateSetAllValue = this.updateSetAllValue.bind(this);
		this.setAllVisibleRates = this.setAllVisibleRates.bind(this);
	}

	render() {
		const styles = {
			container: {
				textAlign: 'right',
				marginBottom: 10,
			},
			lineContainer: {
				display: 'flex',
				flexDirection: 'row-reverse',
				width: '100%',
				padding: '10px 0',
			},
			editModeItem : {
				width: '15em',
				padding: '0 20px',
				textAlign: 'right',
			},
			actions : {
				textTransform: 'uppercase',
				color: Styles.secondaryColor,
			},
			icons: {
				color: Styles.secondaryColor,
				height: '1.6em',
				verticalAlign: 'baseline',
				padding: '0 10px',
			},
			labels : {
				color: Styles.secondaryColor,
				paddingRight: 10,
			},
			setAllTextField: {
				borderRadius: '8px 0 0 8px',
				border: '1px solid lightgray',
				height: '2em',
			},
			setAllButton: {
				border: '1px solid lightgray',
				borderRadius: '0 8px 8px 0',
				height: '2em',
				backgroundColor: Styles.secondaryColor,
				color: 'white',
			},
		}
		if (this.props.editMode) {
			return (
			<div style={assign({}, this.props.style)}>
				<span style={styles.container}>
					<div style={styles.lineContainer}>
						<span style={assign({}, styles.editModeItem, {width:'10em', textAlign:'left'})}>
							<a href="#" style={styles.actions} onClick={RateActions.save}>
								<span className="fa fa-floppy-o" style={styles.icons}/>Save
							</a>
						</span>
						<span style={styles.editModeItem}>
							<a href="#" style={styles.actions} onClick={RateActions.newFiscalYear}>
								<span className="fa fa-plus-circle" style={styles.icons}/>New Fiscal Year
							</a>
						</span>
					</div>
					<div style={styles.lineContainer}>
						<span style={assign({}, styles.editModeItem, {width:'10em', textAlign:'left'})}>
							<a href="#" style={styles.actions} onClick={RateActions.cancelEdit}>
								<span className="fa fa-ban" style={styles.icons}/>Cancel
							</a>
						</span>
						<span style={assign({}, styles.editModeItem, {width:'30em'})}>
							<label style={styles.labels} htmlFor="setAll">Set all visible fields to:</label>
							<span style={{whiteSpace: 'nowrap'}}><input id="setAll" style={styles.setAllTextField} type="text" value={this.state.setAllValue} onChange={this.updateSetAllValue}/>
							<button style={styles.setAllButton} onClick={this.setAllVisibleRates}>Submit</button></span>
						</span>
					</div>
				</span>
			</div>
			);
		} else {
			return (
			<div style={assign({}, this.props.style)}>
				<span style={styles.container}>
					<span style={styles.lineContainer}><span style={styles.editModeItem}>
						<a href="#" style={styles.actions} onClick={RateActions.enterEditMode}>
							<span className="fa fa-pencil" style={styles.icons}/>Edit Mode
						</a>
					</span></span>
				</span>
			</div>
			);
		}
	}
	updateSetAllValue(event) {
		this.setState({setAllValue: event.target.value});
	}
	setAllVisibleRates(event) {
		RateActions.setAllVisibleRates(this.state.setAllValue);
		this.setState({setAllValue: ''});
	}
}