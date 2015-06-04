import { alt } from '../alt';
import actions from './RateActions';
import { RestDataSources } from '../data/RestDataSources';
import {find, sortByAll, first, omit}  from 'lodash';

class RateStore {
	constructor() {
		this.rateClassTypes = [];
		this.rateClasses = [];
		this.rateTypes = [];
		this.activityTypes = [];
		this.units = [];
		this.rates = [];
		this.topUnit = { };

		this.applicableRateClasses = [];
		this.applicableRateTypes = [];
		this.applicableRates = [];
		this.applicableRatesMap = { };
		this.selectedUnit = { };
		
		this.selectedRateClassType;
		this.selectedRateClasses = [];
		this.selectedRateTypes = [];
		this.selectedActivityTypes = [];
		this.showOnCampus = true;
		this.showOffCampus = true;
		this.startYear = 2010;
		this.endYear = 2015;

		this.validStartYear = 1990;
		this.validEndYear = 2200;

		this.hideEmptyRows = false;
		this.editMode = false;
		this.pendingEdits = [];
		this.saving = false;

		this.errorMessages = [];

		this.bindActions({
			selectRateClassType : actions.SELECT_RATE_CLASS_TYPE,
			selectStartYear : actions.SELECT_START_YEAR,
			selectEndYear : actions.SELECT_END_YEAR,
			selectRateClass : actions.SELECT_RATE_CLASS,
			removeSelectedRateClass : actions.REMOVE_SELECTED_RATE_CLASS,
			selectRateType : actions.SELECT_RATE_TYPE,
			removeSelectedRateType : actions.REMOVE_SELECTED_RATE_TYPE,
			selectActivityType : actions.SELECT_ACTIVITY_TYPE,
			removeSelectedActivityType : actions.REMOVE_SELECTED_ACTIVITY_TYPE,
			toggleOnCampusFlag : actions.TOGGLE_ON_CAMPUS_FLAG,
			toggleOffCampusFlag : actions.TOGGLE_OFF_CAMPUS_FLAG,
			handleFetchSupportData : actions.FETCH_SUPPORT_DATA,
			handleFetchRates : actions.FETCH_RATES,
			handleUpdateRateClassTypes : actions.UPDATE_RATE_CLASS_TYPES,
			handleUpdateRateClasses : actions.UPDATE_RATE_CLASSES,
			handleUpdateRateTypes : actions.UPDATE_RATE_TYPES,
			handleUpdateActivityTypes : actions.UPDATE_ACTIVITY_TYPES,
			handleUpdateUnits : actions.UPDATE_UNITS,
			handleUpdateRates : actions.UPDATE_RATES,
			handleUpdateTopUnit : actions.UPDATE_TOP_UNIT,
			handleDataFailed : actions.FETCH_DATA_FAILED,
			enterEditMode : actions.ENTER_EDIT_MODE,
			setRateValue : actions.SET_RATE_VALUE,
			cancelEdit: actions.CANCEL_EDIT,
			startSave: actions.SAVE,
			finishSave: actions.FINISH_SAVE,
			addRate : actions.ADD_RATE,
			toggleEmptyRows : actions.TOGGLE_EMPTY_ROWS,
		});
		this.exportPublicMethods({
			getRateClassTypeFromCode : this.getRateClassTypeFromCode.bind(this),
			getRateClassFromCode : this.getRateClassFromCode.bind(this),
			getRateTypeFromCode : this.getRateTypeFromCode.bind(this),
			getActivityTypeFromCode : this.getActivityTypeFromCode.bind(this),
		});
	}
	toggleEmptyRows() {
		this.hideEmptyRows = !this.hideEmptyRows;
		this.makeRateMap();
	}
	getEffectiveRateClasses() {
		let effectiveRateClasses = 
			this.selectedRateClasses.length == 0 ? 
				this.applicableRateClasses.map((rateClass) => {return rateClass.code;}) 
				: this.selectedRateClasses;
		return effectiveRateClasses;
	}
	getEffectiveRateTypes(classCode) {
		let effectiveTypesForClass = this.applicableRateTypes
			.filter((type) => {
				return type.rateClassCode == classCode &&
					(this.selectedRateTypes.length == 0 || this.selectedRateTypes.indexOf(type.rateTypeCode) >= 0)})
			.map((type) => {return type.rateTypeCode;});
		return effectiveTypesForClass;
	}
	getEffectiveActivityTypes() {
		let effectiveActivityTypes = 
			this.selectedActivityTypes.length == 0 ? 
				this.activityTypes.map((activity) => {return activity.code;}) 
				: this.selectedActivityTypes;
		return effectiveActivityTypes;
	}
	selectRateClassType(rateCode) {
		if (this.selectedRateClassType != rateCode) {
			this.selectedRateClassType = rateCode;
		}
	}
	selectRateClass(rateClassCode) {
		if (this.selectedRateClasses.indexOf(rateClassCode) == -1) {
			this.selectedRateClasses.push(rateClassCode);
		}
		this.resetRateTypes();
	}
	removeSelectedRateClass(rateClassCode) {
		this.selectedRateClasses = this.selectedRateClasses.filter((selectedCode) => { return selectedCode != rateClassCode; });
		this.resetRateTypes();
	}
	resetRateClasses() {
		let rateClassTypeCode = this.selectedRateClassType;
		this.applicableRateClasses = this.rateClasses.filter((rateClass) => { return rateClass.rateClassTypeCode == rateClassTypeCode });
		this.selectedRateClasses = this.selectedRateClasses.filter((rateClass) => {
			return this.applicableRateClasses.map((rateClass) => { 
				return rateClass.code; 
			}).indexOf(rateClass.code) != -1;
		});
		this.resetRateTypes();
	}
	selectRateType(rateTypeCode) {
		if (this.selectedRateTypes.indexOf(rateTypeCode) == -1) {
			this.selectedRateTypes.push(rateTypeCode);
		}
		this.filterRates();
	}
	removeSelectedRateType(rateTypeCode) {
		this.selectedRateTypes = this.selectedRateTypes.filter((selectedCode) => { return selectedCode != rateTypeCode; });
		this.filterRates();
	}
	resetRateTypes() {
		this.applicableRateTypes = this.rateTypes.filter(this.buildApplicableRateTypeFilter());
		this.selectedRateTypes = this.selectedRateTypes.filter(function(rateTypeCode) {
				return find(this.applicableRateTypes, "rateTypeCode", rateTypeCode) != undefined;
		}.bind(this));
		this.filterRates();
	}
	buildApplicableRateTypeFilter() {
		let rateClassCodes = [];
		if (this.selectedRateClasses.length != 0) {
			rateClassCodes = this.selectedRateClasses;
		} else {
			rateClassCodes = this.applicableRateClasses.map((rateClass) => { return rateClass.code; });
		}
		return (rateType) => {
			return rateClassCodes.indexOf(rateType.rateClassCode) != -1;
		};
	}
	selectActivityType(activityTypeCode) {
		if (this.selectedActivityTypes.indexOf(activityTypeCode) == -1) {
			this.selectedActivityTypes.push(activityTypeCode);
		}
		this.filterRates();
	}
	removeSelectedActivityType(activityTypeCode) {
		this.selectedActivityTypes = this.selectedActivityTypes.filter((selectedCode) => { return selectedCode != activityTypeCode });
		this.filterRates();
	}
	selectStartYear(year) {
		this.startYear = parseInt(year);
		this.filterRates();
	}
	selectEndYear(year) {
		this.endYear = parseInt(year);
		this.filterRates();
	}
	toggleOnCampusFlag(enabled) {
		this.showOnCampus = !this.showOnCampus;
		if (!this.showOnCampus && !this.showOffCampus) {
			this.showOffCampus = true;
		}
		this.filterRates();
	}
	toggleOffCampusFlag(enabled) {
		this.showOffCampus = !this.showOffCampus;
		if (!this.showOnCampus && !this.showOffCampus) {
			this.showOnCampus = true;
		}
		this.filterRates();
	}
	filterRates() {
		this.applicableRates = {};
		this.applicableRates = this.rates.filter(this.rateMatchesCriteria.bind(this));
		sortByAll(this.applicableRates, ['rateClassCode', 'rateTypeCode', 'activityTypeCode', 'fiscalYear', 'onOffCampusFlag'], [true, true, true, true, false]);
		this.makeRateMap();		
	}
	makeRateMap() {
		let applicableRatesMap = { };
		this.getEffectiveRateClasses().forEach((classCode) => {
			applicableRatesMap[classCode] = applicableRatesMap[classCode] ||  { };
			let applicableRateByClass = applicableRatesMap[classCode];
			this.getEffectiveRateTypes(classCode).forEach((typeCode) => {
				applicableRateByClass[typeCode] = applicableRateByClass[typeCode] || { };
				let applicableRatesByType = applicableRateByClass[typeCode]
				this.getEffectiveActivityTypes().forEach((activityTypeCode) => {
					applicableRatesByType[activityTypeCode] = this.makeMapOfFiscalYears(this.applicableRates.filter((rate) => {
						return rate.rateClassCode == classCode
							&& rate.rateTypeCode == typeCode
							&& rate.activityTypeCode == activityTypeCode;
					}));
				});
			});
		});
		console.log(applicableRatesMap);
		if (this.hideEmptyRows) {
			this.clearEmptyRows(applicableRatesMap);
		}
		this.applicableRatesMap = applicableRatesMap;
		return applicableRatesMap;
	}
	clearEmptyRows(applicableRatesMap) {
		for (let rateClassCode in applicableRatesMap) {
			let rateTypeMap = applicableRatesMap[rateClassCode];
			for (let rateTypeCode in rateTypeMap) {
				let activityTypeMap = rateTypeMap[rateTypeCode];
				for (let activityTypeCode in activityTypeMap) {
					let fiscalYearMap = activityTypeMap[activityTypeCode];
					let activityTypeEmpty = true;
					for (let fiscalYear in fiscalYearMap) {
						let onCampusMap = fiscalYearMap[fiscalYear];
						if (onCampusMap.onCampus || onCampusMap.offCampus) {
							activityTypeEmpty = false;
						}
					}
					if (activityTypeEmpty) {
						delete activityTypeMap[activityTypeCode];
					}
				}
				if (Object.keys(rateTypeMap[rateTypeCode]).length == 0) {
					delete rateTypeMap[rateTypeCode];
				}
			}
			if (Object.keys(applicableRatesMap[rateClassCode]).length == 0) {
				delete applicableRatesMap[rateClassCode];
			}
		}
	}
	makeMapOfFiscalYears(rates) {
		let result = { };
		for (let i = this.startYear; i <= this.endYear; i++) {
			result[i] = {onCampus : undefined, offCampus : undefined };
			result[i].onCampus = first(rates.filter((rate) => { return rate.fiscalYear == i && rate.onOffCampusFlag; }));
			result[i].offCampus = first(rates.filter((rate) => { return rate.fiscalYear == i && !rate.onOffCampusFlag; }));
		}
		return result;
	}
	rateMatchesCriteria(rate) {
		return this.rateMatchesSelectedUnit(rate) 
			&& this.rateMatchesRateClasses(rate)
			&& this.rateMatchesRateTypes(rate)
			&& this.rateMatchesActivityTypes(rate)
			&& this.rateMatchesFiscalYears(rate)
			&& this.rateMatchesOnOffCampus(rate);
	}
	rateMatchesRateClasses(rate) {
		if (this.selectedRateClasses.length > 0) {
			return this.selectedRateClasses.indexOf(rate.rateClassCode) != -1;
		} else {
			return find(this.applicableRateClasses, {code : rate.rateClassCode}) != undefined;
		}
	}
	rateMatchesRateTypes(rate) {
		if (this.selectedRateTypes.length > 0) {
			return this.selectedRateTypes.indexOf(rate.rateTypeCode) != -1;
		} else {
			return find(this.applicableRateTypes, {rateTypeCode : rate.rateTypeCode}) != undefined;
		}
	}
	rateMatchesActivityTypes(rate) {
		if (this.selectedActivityTypes.length > 0) {
			return this.selectedActivityTypes.indexOf(rate.activityTypeCode) != -1;
		} else {
			return true;
		}
	}
	rateMatchesFiscalYears(rate) {
		let result = true;
		if (this.startYear) result &= rate.fiscalYear >= this.startYear;
		if (this.endYear) result &= rate.fiscalYear <= this.endYear;
		return result;
	}
	rateMatchesOnOffCampus(rate) {
		return ((this.showOnCampus && rate.onOffCampusFlag) 
			|| (this.showOffCampus && !rate.onOffCampusFlag));
	}
	rateMatchesSelectedUnit(rate) {
		return !this.selectedUnit || rate.unitNumber == this.selectedUnit.unitNumber;
	}
	updateValidStartAndEndYears() {
		let startYear;
		let endYear;
		this.applicableRates.forEach((rate) => {
			if (!startYear || rate.fiscalYear < startYear ) { startYear = rate.fiscalYear; }
			if (!endYear || rate.fiscalYear > endYear) { endYear = rate.fiscalYear; }
		});
		this.validStartYear = startYear;
		this.validEndYear = endYear;
		if (!this.validStartYear
			|| !this.startYear 
			|| this.startYear < this.validStartYear) {
			this.startYear = this.validStartYear;
		}
		if (!this.validEndYear
			|| !this.endYear 
			|| this.endYear > this.validEndYear) {
			this.endYear = this.validEndYear;
		}
	}
	handleUpdateRateClassTypes(rateClassTypes) {
		this.rateClassTypes = rateClassTypes;
	}
	handleUpdateRateClasses(rateClasses) {
		this.rateClasses = rateClasses;
	}
	handleUpdateRateTypes(rateTypes) {
		this.rateTypes = rateTypes;
	}
	handleUpdateActivityTypes(activityTypes) {
		this.activityTypes = activityTypes;
	}
	handleUpdateUnits(units) {
		this.units = units;
	}
	handleUpdateRates(rates) {
		this.rates = rates;
		this.resetRateClasses();
	}
	handleUpdateTopUnit(unit) {
		this.topUnit = unit;
		this.selectedUnit = unit;
		this.filterRates();
	}
	handleDataFailed(error) {
		console.log(error);
		this.errorMessages.push(error);
	}
	handleFetchSupportData() { }
	handleFetchRates() { }
	enterEditMode() {
		this.rates.forEach(rate => { rate.previousRate = rate.instituteRate; });
		this.editMode = true;
	}
	setRateValue(changes) {
		changes.rate.instituteRate = changes.value;
		console.log('updating rate for ' + JSON.stringify(changes.rate));
		if (this.pendingEdits.indexOf(changes.rate) < 0) {
			this.pendingEdits.push(changes.rate);
		}
	}
	addRate(newRate) {
		newRate.unitNumber = this.selectedUnit.unitNumber;

		//if this rate has been added already and is already being modified, see if it exists in pending
		let currentRate = this.pendingEdits.filter(rate => {
			return rate.rateClassCode == newRate.rateClassCode
				&& rate.rateTypeCode == newRate.rateTypeCode
				&& rate.activityTypeCode == newRate.activityTypeCode
				&& rate.fiscalYear == newRate.fiscalYear
				&& rate.onOffCampusFlag == newRate.onOffCampusFlag;
		});
		if (!currentRate) {
			this.pendingEdits.push(newRate);
			this.rates.push(newRate);
			this.applicableRates.push(newRate);
			this.applicableRatesMap[newRate.rateClassCode][newRate.rateTypeCode][newRate.activityTypeCode][newRate.fiscalYear][newRate.onOffCampusFlag ? "onCampus" : "offCampus"] = newRate;
		}
	}
	cancelEdit() {
		this.editMode = false;
		this.rates.forEach(rate => { rate.instituteRate = rate.previousRate; });
		this.rates = this.rates.filter(rate => { return !rate.isNew; });
	}
	startSave() {
		this.saving = true;
		console.log("saving, " + JSON.stringify(this.pendingEdits));
		RestDataSources('rate').put(this.pendingEdits.map(rate => {
			return omit(rate, 'previousRate', 'isNew');
		})).then((data) => {
			actions.finishSave(data);
		}).catch((errorMessage) => {
			this.handleDataFailed(errorMessage);
		}.bind(this));
	}
	finishSave() {
		this.editMode = false;
		this.rates.forEach(rate => { delete rate["previousRate"]; });
		this.pendingEdits = [];
	}
	getRateClassTypeFromCode(rateClassType) {
		return find(this.rateClassTypes, {code : rateClassType});
	}
	getRateClassFromCode(rateClassCode) {
		return find(this.rateClasses, {code : rateClassCode });
	}
	getRateTypeFromCode(rateTypeCode) {
		return find(this.rateTypes, {rateTypeCode : rateTypeCode});
	}
	getActivityTypeFromCode(activityTypeCode) {
		return find(this.activityTypes, {code : activityTypeCode});
	}

}

export default alt.createStore(RateStore, 'RateStore');



