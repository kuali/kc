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
import axios from 'axios';
import cookies from 'cookies-js';
import {assign} from 'lodash';

class RestFetcher {
	constructor(endpoint, query) {
		this.endpoint = endpoint;
		this.sharedQuery = query;
	}
	fetch(query) {
		return axios.get(this.endpoint, {
			params: assign({}, this.sharedQuery, query),
			headers: this.getAuthHeader(),
		}).catch(function(response) {
			console.log(response);
			throw response;
		}).then(function(response) { return response.data });
	}
	put(data) {
		return axios.put(this.endpoint, data, {
			headers: this.getAuthHeader(),
		}).catch(function(response) {
			console.log(response);
			throw response;
		}).then(function(response) { return response.data });
	}
	getAuthHeader() {
		return {'Authorization' : 'Bearer ' + cookies.get('authToken')};
	}
}

const apiRoot = '../research-common/api/';

module.exports = {
	rateClassType : new RestFetcher(apiRoot + 'rateClassTypes'), 
	rateClass : new RestFetcher(apiRoot + 'rateClasses'),
	rateType : new RestFetcher(apiRoot + 'rateTypes'),
	activityType : new RestFetcher(apiRoot + 'activityTypes'),
	unit : new RestFetcher(apiRoot + 'units'),
	topUnit : new RestFetcher(apiRoot + 'units', {topUnit : ''}),
	rate : new RestFetcher(apiRoot + 'instituteRates'),
};