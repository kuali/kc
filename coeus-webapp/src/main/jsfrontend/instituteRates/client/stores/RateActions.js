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
import { alt } from '../alt';
import { RestDataSources } from '../data/RestDataSources';

class RateActions {
	rateClassTypesFailed(errorMessage) {
		this.dispatch(errorMessage);
	}
	selectRateClassType(rateCode) {
		this.dispatch(rateCode);
		this.actions.fetchData(this.actions.updateRates, 'rate', 'Rates', this.actions.fetchRates, {rateClassTypeCode : rateCode});
	}
	selectRateClass(rateClassCode) {
		this.dispatch(rateClassCode);
	}
	removeSelectedRateClass(rateClassCode) {
		this.dispatch(rateClassCode);
	}
	selectRateType(rateTypeCode) {
		this.dispatch(rateTypeCode);
	}
	removeSelectedRateType(rateTypeCode) {
		this.dispatch(rateTypeCode);
	}
	selectActivityType(activityTypeCode) {
		this.dispatch(activityTypeCode);
	}
	removeSelectedActivityType(activityTypeCode) {
		this.dispatch(activityTypeCode);
	}
	selectStartYear(year) {
		this.dispatch(year);
	}
	selectEndYear(year) {
		this.dispatch(year);
	}
	toggleOnCampusFlag() {
		this.dispatch();
	}
	toggleOffCampusFlag() {
		this.dispatch();
	}
	enterEditMode() {
		this.dispatch();
	}
	fetchSupportData() {
		this.dispatch();
		this.actions.fetchData(this.actions.updateRateClassTypes, 'rateClassType', 'Rate Class Types', this.actions.fetchSupportData);
		this.actions.fetchData(this.actions.updateRateClasses, 'rateClass', 'Rate Classes', this.actions.fetchSupportData);
		this.actions.fetchData(this.actions.updateRateTypes, 'rateType', 'Rate Types', this.actions.fetchSupportData);
		this.actions.fetchData(this.actions.updateActivityTypes, 'activityType', 'Activity Types', this.actions.fetchSupportData);
		this.actions.fetchData(this.actions.updateUnits, 'unit', 'Units', this.actions.fetchSupportData);
		this.actions.fetchData(this.actions.updateTopUnit, 'topUnit', 'Top Unit', this.actions.fetchSupportData);
	}
	fetchRates() {
		this.dispatch();
	}
	fetchData(methodOnSuccess, dataSource, dataSourceName, methodToRetry, query) {
		RestDataSources(dataSource).fetch(query).then((data) => {
			methodOnSuccess(data);
		}).catch((errorMessage) => {
			this.actions.fetchDataFailed(dataSource, dataSourceName, methodToRetry, errorMessage);
		});		
	}
	updateRateClassTypes(rateClassTypes) {
		this.dispatch(rateClassTypes);
	}
	updateRateClasses(rateClasses) {
		this.dispatch(rateClasses);
	}
	updateRateTypes(rateTypes) {
		this.dispatch(rateTypes);
	}
	updateActivityTypes(activityTypes) {
		this.dispatch(activityTypes);
	}
	updateUnits(units) {
		this.dispatch(units);
	}
	updateRates(rates) {
		this.dispatch(rates);
	}
	updateTopUnit(unit) {
		this.dispatch(unit);
	}
	setRateValue(rate, value) {
		this.dispatch({rate: rate, value : value});
	}
	addRate(rateClassCode, rateTypeCode, activityTypeCode, fiscalYear, onCampus, instituteRate) {
		this.dispatch({
			rateClassCode : rateClassCode,
			rateTypeCode : rateTypeCode,
			activityTypeCode : activityTypeCode,
			fiscalYear : fiscalYear,
			onOffCampusFlag : onCampus,
			instituteRate : instituteRate,
			isNew : true,
		});
	}
	cancelEdit() {
		this.dispatch();
	}
	save() {
		this.dispatch();
	}
	finishSave(data) {
		this.dispatch();
	}
	toggleEmptyRows() {
		this.dispatch();
	}
	toggleChangeStartDates() {
		this.dispatch();
	}
	newFiscalYear() {
		this.dispatch();
	}
	setAllVisibleRates(value) {
		this.dispatch(value);
	}
	fetchDataFailed(dataSource, dataSourceName, methodToRetry, errorMessage) {
		this.dispatch({ 
			dataSource : dataSource, 
			dataSourceName : dataSourceName, 
			methodToRetry : methodToRetry, 
			errorMessage : errorMessage 
		});
	}

}
export default alt.createActions(RateActions);
