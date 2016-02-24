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
import RateStore from '../stores/RateStore';
import RateActions from '../stores/RateActions';
import {EditControls} from './EditControls';

export class RateListing extends React.Component {
	constructor(props) {
		super(props);
		this.rateStore = RateStore;
		this.state = this.rateStore.getState();
		this.onChange = this.onChange.bind(this);
	}

	componentDidMount() { 
		this.rateStore.listen(this.onChange);
	}
	componentWillUnmount() { 
		this.rateStore.unlisten(this.onChange);
	}
	
	render() {
		if (!this.state.selectedRateClassType) { return (<span/>); }
		let styles = {
			container : {
				height: '100%',
				position: 'relative',
			},
			innerDiv : {
				overflowX:'auto',
				overflowY:'auto',
				width:'100%',
			},
			table : {
				width: '100%',
				tableLayout: 'fixed',
				borderCollapse: 'collapse',
				fontSize: '.9em',
			},
			thead : {
				
			},
			th : {
				background : 'rgb(230, 230, 230)',
				padding: 5,
				textAlign: 'center',
				fontWeight: 'normal',
				border: '2px solid rgb(200, 200, 200)',
			},
			yearHeaders : {
				background : 'rgb(66, 117, 136)',
				width: "12em",
				color: 'white',
			},
			rateTypeLabels : {
				textAlign: 'left',
			},
			rateTypeHeaders : {
				textAlign: 'left'
			},
			edit: {
				float: 'right',
			},
			headings: {
				fontSize: '1.2em',
				paddingBottom: 5,
			},
			headingLabels: {
				textTransform: 'uppercase',
			},
			headingText: {
				fontWeight: 'bold',
			}
		};

		const yearHeaderStyle = assign({}, styles.th, styles.yearHeaders);
		const campusHeaderStyle = assign({}, styles.th, styles.rateTypeLabels, {width: 220});
		const rateHeaderStyles = assign({}, styles.td, styles.rateTypeHeaders);


		let campusCols = this.state.showOnCampus && this.state.showOffCampus ? 2 : 1;
		let yearCols = [];
		let yearHead = [];
		let campusHeader = [];
		for (let i = this.state.startYear; i <= this.state.endYear; i++) {
			yearCols.push(i);
			yearHead.push(<th style={yearHeaderStyle} colSpan={campusCols}>{i}</th>);
			if (this.state.showOnCampus) {
				campusHeader.push(<th style={styles.th}>ON</th>);
			}
			if (this.state.showOffCampus) {
				campusHeader.push(<th style={styles.th}>OFF</th>);
			}	
		}

		campusHeader.splice(0, 0, (<th style={campusHeaderStyle}>Rate Class</th>));
		campusHeader.splice(1, 0, (<th style={campusHeaderStyle}>Rate Type</th>));
		campusHeader.splice(2, 0, (<th style={assign({}, campusHeaderStyle, {width: 260})}>Activity Types</th>));
		yearHead.splice(0, 0, (<th colSpan="3" style={{width: 700}}></th>));

		let dataRows = [];
		for (let rateClassCode in this.state.applicableRatesMap) {
			let shownRateClass = false;
			for (let rateTypeCode in this.state.applicableRatesMap[rateClassCode]) {
				let shownRateType = false;
				for (let activityTypeCode in this.state.applicableRatesMap[rateClassCode][rateTypeCode]) {
					let shownActivityType = false;
					let rateMap = this.state.applicableRatesMap[rateClassCode][rateTypeCode][activityTypeCode];
					let rateCells = [ ];
					for (let i = this.state.startYear; i <= this.state.endYear; i++) {
						if (this.state.showOnCampus) {
							let rateCell;
							if (rateMap[i].onCampus) {
								rateCell = this.buildRateCell(rateMap[i].onCampus);
							} else {
								rateCell = this.buildNewRateCell(rateClassCode,
									rateTypeCode,
									activityTypeCode,
									i, true);
							}
							rateCells.push(rateCell);
						}
						if (this.state.showOffCampus) {
							let rateCell;
							if (rateMap[i].offCampus) {
								rateCell = this.buildRateCell(rateMap[i].offCampus);
							} else {
								rateCell = this.buildNewRateCell(rateClassCode,
									rateTypeCode,
									activityTypeCode,
									i, false);
							}
							rateCells.push(rateCell);
						}	
					}
					let borderColor = !shownRateClass ? 150 : 200;
					dataRows.push(
						<tr style={{borderTop: '2px solid rgb(' + borderColor + ',' + borderColor + ',' + borderColor + ')'}}>
							<td style={rateHeaderStyles}>{!shownRateClass && RateStore.getRateClassFromCode(rateClassCode).description}</td>
							<td style={rateHeaderStyles}>{!shownRateType && RateStore.getRateTypeFromCode(rateClassCode, rateTypeCode).description}</td>
							<td style={rateHeaderStyles}>{!shownActivityType && RateStore.getActivityTypeFromCode(activityTypeCode).description}</td>
							{rateCells}
						</tr>
					);
					shownRateClass = shownRateType = shownActivityType = true;
				}
			}
		}

		return (
			<div style={assign({}, this.props.style, styles.container)}>
				<div style={styles.header}>
					<div style={{width:'50%', display: 'inline-block'}}>
					<div style={styles.headings}><span style={styles.headingLabels}>Rate Class Type:</span> <span style={styles.headingText}>{this.rateStore.getRateClassTypeFromCode(this.state.selectedRateClassType).description}</span></div>
					<div style={styles.headings}><span style={styles.headingLabels}>Fiscal Years:</span> <span style={styles.headingText}>{this.state.startYear} - {this.state.endYear}</span></div>
					{!this.state.editMode && <div><label><input type="checkbox" defaultChecked={this.state.hideEmptyRows} onChange={RateActions.toggleEmptyRows}>Hide All Empty Rows</input></label></div>}
					</div>
					<EditControls style={{width:'49%', display: 'inline-block'}} editMode={this.state.editMode} changeStartDates={this.state.changeStartDates}/>
				</div>
				<div style={styles.innerDiv}>
					<table style={styles.table}>
						<thead style={styles.thead}>
							<tr>{yearHead}</tr>
							<tr>{campusHeader}</tr>
						</thead>
						<tbody>
							{dataRows}
						</tbody>
					</table>
				</div>
			</div>
		);
	}
	buildRateCell(rate) {
		let tdStyles = {
			padding: '2px 10px 2px 10px',
			textAlign: 'center',
			border: '2px solid rgb(200, 200, 200)',
			width: "6em",
		};
		if (this.state.editMode) {
			return (<td style={tdStyles}><input type="text" style={{width: "5em"}} name={this.buildRateId(rate)} value={rate.instituteRate} onChange={this.changeRate(rate)}/></td>);
		} else {
			return (<td style={tdStyles}>{rate.instituteRate}</td>);
		}		
	}
	buildNewRateCell(rateClassCode, rateTypeCode, activityTypeCode, fiscalYear, onCampus) {
		let tdStyles = {
			padding: '2px 10px 2px 10px',
			textAlign: 'center',
			border: '2px solid rgb(200, 200, 200)',
			width: "6em",
		};
		if (this.state.editMode) {
			return (<td style={tdStyles}><input type="text" style={{width: "5em"}} name={this.buildNewRateId(rateClassCode, rateTypeCode, activityTypeCode, fiscalYear, onCampus)} value="" onChange={this.addRate(rateClassCode, rateTypeCode, activityTypeCode, fiscalYear, onCampus)}/></td>);
		} else {
			return (<td style={tdStyles}>&nbsp;</td>);
		}		

	}
	changeRate(rate) {
		return function(event) {
			RateActions.setRateValue(rate, event.target.value);
		}
	}
	addRate(rateClassCode, rateTypeCode, activityTypeCode, fiscalYear, onCampus) {
		return function(event) {
			RateActions.addRate(rateClassCode, rateTypeCode, activityTypeCode, fiscalYear, onCampus, event.target.value);
		}
	}
	buildRateId(rate) {
		return rate.rateClassCode + "-" + rate.rateTypeCode + "-" + rate.activityTypeCode + "-" + rate.fiscalYear + "-" + rate.onOffCampus;
	}
	buildNewRateId(rateClassCode, rateTypeCode, activityTypeCode, fiscalYear, onCampus) {
		return rateClassCode + "-" + rateTypeCode + "-" + activityTypeCode + "-" + fiscalYear + "-" + onCampus;	
	}
	onChange() {
		this.setState(this.rateStore.getState());
	}
};