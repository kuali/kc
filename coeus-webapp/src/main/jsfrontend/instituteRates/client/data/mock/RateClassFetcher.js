/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
const mockRateClasses = [
	{ code : "O", description : "F & A" },
	{ code : "E", description : "Fringe Benefits" },
	{ code : "I", description : "Inflation" }
];

class RateClassFetcher {
	fetch() {
		return new Promise(function (resolve, reject) {
			setTimeout(function() {
				resolve(mockRateClassTypes);
			}, 250);
		});
	}
}

let rateClassFetcher = new RateClassFetcher();
module.exports = rateClassFetcher;