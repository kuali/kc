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
const mockRateClassTypes = [
	{ code : "O", description : "F & A" },
	{ code : "E", description : "Fringe Benefits" },
	{ code : "I", description : "Inflation" }
];

const mockRateClasses = [
	{ code : "1", description : "MTDC", rateClassTypeCode : "O" },
	{ code : "2", description : "TDC", rateClassTypeCode : "O" },
	{ code : "3", description : "S&W", rateClassTypeCode : "O" },
	{ code : "5", description : "Employee Benefits", rateClassTypeCode : "E" },
	{ code : "7", description : "Inflation", rateClassTypeCode : "I" }
];

const mockRateTypes = [
	{ rateTypeCode : "1", description : "MTDC", rateClassCode : "1" },
	{ rateTypeCode : "2", description : "TDC", rateClassCode : "2" },
	{ rateTypeCode : "3", description : "S&W", rateClassCode : "3" },
	{ rateTypeCode : "10", description : "Salaries", rateClassCode : "5" },
	{ rateTypeCode : "4", description : "Salaries - Non-Classified: SalNC", rateClassCode : "5" },
	{ rateTypeCode : "5", description : "Salaries - Classified: SalClass", rateClassCode : "5" },
	{ rateTypeCode : "6", description : "Salaries - Graduate Assistants: SalGA", rateClassCode : "5" },
	{ rateTypeCode : "10", description : "Hourly - Employee/Non-Student: Wages", rateClassCode : "7" },
	{ rateTypeCode : "11", description : "Hourly - Employee/Non-Student: Wages", rateClassCode : "7" },
	{ rateTypeCode : "12", description : "Hourly - Employee/Non-Student: Wages", rateClassCode : "7" },
	{ rateTypeCode : "3", description : "Hourly - Employee/Non-Student: Wages", rateClassCode : "7" },
	{ rateTypeCode : "4", description : "Hourly - Employee/Non-Student: Wages", rateClassCode : "7" }
];

const mockActivityTypes = [
	{ code : "1", description : "Reserach" },
	{ code : "2", description : "Instruction" },
	{ code : "3", description : "Public Service" },
	{ code : "5", description : "Other" },
	{ code : "6", description : "Fellowship - Pre-Doctoral"}
];

const mockUnits = [
	{ unitNumber : '000001', parentUnitNumber : undefined, unitName : 'University' },
	{ unitNumber : 'AFT1', parentUnitNumber : '000001', unitName : 'AFT-PUI College' },
	{ unitNumber : 'VPA', parentUnitNUmber : '000001', unitName : 'Vice President of Academic Affairs' },
	{ unitNumber : 'A&S', parentUnitNUmber : 'VPA', unitName : 'Dean Arts and Sciences' },
	{ unitNumber : 'BIO', parentUnitNumber : 'A&S', unitName : 'Dept of Biology'}
];

const mockRates = [ ];

function buildMockRates(startYear, endYear, rateClassCode, rateTypeCode, activityTypeCode, instituteRateStartRange, instituteRateEndRange, unitNumber) {
	for (let i = startYear; i <= endYear; i++) {
		mockRates.push({ 
			fiscalYear : i, 
			onOffCampusFlag : true, 
			rateClassCode : rateClassCode, 
			rateTypeCode : rateTypeCode, 
			startDate : new Date(), 
			instituteRate : (Math.random() * (instituteRateEndRange-instituteRateStartRange) + instituteRateStartRange).toFixed(2), 
			unitNumber : unitNumber, 
			activityTypeCode : activityTypeCode});
		mockRates.push({ 
			fiscalYear : i, 
			onOffCampusFlag : false, 
			rateClassCode : rateClassCode, 
			rateTypeCode : rateTypeCode, 
			startDate : new Date(), 
			instituteRate : (Math.random() * (instituteRateEndRange-instituteRateStartRange) + instituteRateStartRange).toFixed(2), 
			unitNumber : unitNumber, 
			activityTypeCode : activityTypeCode});
	}
}

buildMockRates(2012, 2025, "1", "1", "1", 5, 20, "000001");
buildMockRates(2012, 2025, "1", "1", "2", 5, 20, "000001");
buildMockRates(2012, 2025, "1", "1", "3", 5, 20, "000001");
buildMockRates(2012, 2025, "1", "1", "6", 5, 20, "000001");
buildMockRates(2012, 2025, "1", "1", "5", 5, 20, "000001");

buildMockRates(2012, 2025, "2", "2", "1", 5, 20, "000001");
buildMockRates(2012, 2025, "2", "2", "2", 5, 20, "000001");
buildMockRates(2012, 2025, "2", "2", "3", 5, 20, "000001");
buildMockRates(2012, 2025, "2", "2", "6", 5, 20, "000001");
buildMockRates(2012, 2025, "2", "2", "5", 5, 20, "000001");

buildMockRates(2012, 2025, "3", "3", "1", 5, 20, "000001");
buildMockRates(2012, 2025, "3", "3", "2", 5, 20, "000001");
buildMockRates(2012, 2025, "3", "3", "3", 5, 20, "000001");
buildMockRates(2012, 2025, "3", "3", "6", 5, 20, "000001");
buildMockRates(2012, 2025, "3", "3", "5", 5, 20, "000001");

buildMockRates(2012, 2028, "5", "4", "1", 5, 20, "000001");
buildMockRates(2012, 2028, "5", "4", "2", 5, 20, "000001");
buildMockRates(2012, 2028, "5", "4", "3", 5, 20, "000001");
buildMockRates(2012, 2028, "5", "4", "6", 5, 20, "000001");
buildMockRates(2012, 2028, "5", "4", "5", 5, 20, "000001");

buildMockRates(2012, 2025, "5", "5", "1", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "5", "2", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "5", "3", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "5", "6", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "5", "5", 5, 20, "000001");

buildMockRates(2012, 2025, "5", "6", "1", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "6", "2", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "6", "3", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "6", "6", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "6", "5", 5, 20, "000001");

buildMockRates(2012, 2025, "5", "10", "1", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "10", "2", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "10", "3", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "10", "6", 5, 20, "000001");
buildMockRates(2012, 2025, "5", "10", "5", 5, 20, "000001");


class MockFetcher {
	constructor(data) {
		this.data = data;
	}
	fetch() {
		let data = this.data;
		return new Promise(function (resolve, reject) {
			setTimeout(function() {
				resolve(data);
			}, 250);
		});
	}
}

module.exports = {
	rateClassType : new MockFetcher(mockRateClassTypes), 
	rateClass : new MockFetcher(mockRateClasses),
	rateType : new MockFetcher(mockRateTypes),
	activityType : new MockFetcher(mockActivityTypes),
	unit : new MockFetcher(mockUnits),
	rate : new MockFetcher(mockRates),
};