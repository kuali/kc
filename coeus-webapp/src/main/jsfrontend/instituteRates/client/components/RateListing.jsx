import React from 'react/addons';
import ReactRouter from 'react-router';
import {assign} from 'lodash';
import RateStore from '../stores/RateStore';
import RateActions from '../stores/RateActions';

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
				width:'90%',
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
		};

		let campusCols = this.state.showOnCampus && this.state.showOffCampus ? 2 : 1;
		let yearCols = [];
		let yearHead = [];
		let campusHeader = [];
		for (let i = this.state.startYear; i <= this.state.endYear; i++) {
			yearCols.push(i);
			yearHead.push(<th style={assign({}, styles.th, styles.yearHeaders)} colSpan={campusCols}>{i}</th>);
			if (this.state.showOnCampus) {
				campusHeader.push(<th style={styles.th}>ON</th>);
			}
			if (this.state.showOffCampus) {
				campusHeader.push(<th style={styles.th}>OFF</th>);
			}	
		}

		campusHeader.splice(0, 0, (<th style={assign({}, styles.th, styles.rateTypeLabels, {width: 220})}>Rate Class</th>));
		campusHeader.splice(1, 0, (<th style={assign({}, styles.th, styles.rateTypeLabels, {width: 220})}>Rate Type</th>));
		campusHeader.splice(2, 0, (<th style={assign({}, styles.th, styles.rateTypeLabels, {width: 260})}>Activity Types</th>));
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
							<td style={assign({}, styles.td, styles.rateTypeHeaders)}>{!shownRateClass && RateStore.getRateClassFromCode(rateClassCode).description}</td>
							<td style={assign({}, styles.td, styles.rateTypeHeaders)}>{!shownRateType && RateStore.getRateTypeFromCode(rateTypeCode).description}</td>
							<td style={assign({}, styles.td, styles.rateTypeHeaders)}>{!shownActivityType && RateStore.getActivityTypeFromCode(activityTypeCode).description}</td>
							{rateCells}
						</tr>
					);
					shownRateClass = shownRateType = shownActivityType = true;
				}
			}
		}

		let editControls = [];
		if (this.state.editMode) {
			editControls.push(<a href="#" onClick={this.cancelEdit}>Cancel</a>);
			editControls.push(<a href="#" onClick={this.save}>Save</a>);
		} else {
			editControls.push(<a href="#" onClick={this.enterEditMode}>Edit</a>);
		}

		return (
			<div style={assign({}, this.props.style, styles.container)}>
				<div style={styles.header}>
					<div>Rate Class Type: {this.rateStore.getRateClassTypeFromCode(this.state.selectedRateClassType).description}</div>
					<div>Fiscal Years: {this.state.startYear} - {this.state.endYear}</div>
					<div><label><input type="checkbox" defaultChecked={this.state.hideEmptyRows} onChange={this.toggleEmptyRows}>Hide All Empty Rows</input></label></div>
					<span style={styles.edit}>{editControls}</span>
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
	toggleEmptyRows() {
		RateActions.toggleEmptyRows();
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
	enterEditMode() {
		RateActions.enterEditMode();
	}
	cancelEdit() {
		RateActions.cancelEdit();
	}
	save() {
		RateActions.save();
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