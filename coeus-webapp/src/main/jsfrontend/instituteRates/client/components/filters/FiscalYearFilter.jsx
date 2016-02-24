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
import ReactRouter from 'react-router';
import {assign} from 'lodash';
import RateActions from '../../stores/RateActions';
import {StyledSelect} from '../shared/StyledSelect';
import SharedStyles from '../shared/Styles';

export class FiscalYearFilter extends React.Component {
	constructor(props) {
		super(props);
	}
	render() {
		let styles = {
			fieldSet : {
				border: 0,
				padding: 0,
				margin: 0,
				textAlign: 'left',
			},
			label : {
				textTransform: 'uppercase',
			},
		}
		let yearOptions = [];
		for ( let i = this.props.validStartYear; i <= this.props.validEndYear; i++) {
			yearOptions.push(<option value={i} key={i}>{i}</option>);
		}
		return (
			<fieldset style={assign({}, styles.fieldSet, this.props.style)}>
				<legend style={SharedStyles.filterLabels}>Fiscal Year Span:</legend>
				<label htmlFor="startYear" style={{display: "none"}}>Start Year</label>
				<StyledSelect id="startYear" name="startYear" value={this.props.startYear} onChange={this.onChangeStart}>{yearOptions}</StyledSelect>
				<span style={{margin : 5}}>TO</span>
				<label htmlFor="endYear" style={{display: "none"}}>End Year</label>
				<StyledSelect id="endYear" name="endYear" value={this.props.endYear} onChange={this.onChangeEnd}>{yearOptions}</StyledSelect>
			</fieldset>
		);
	}
	onChangeStart(event) {
		RateActions.selectStartYear(event.target.value);
	}
	onChangeEnd(event) {
		RateActions.selectEndYear(event.target.value);
	}
};	