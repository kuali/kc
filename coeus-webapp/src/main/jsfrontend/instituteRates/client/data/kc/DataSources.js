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
import axios from 'axios';
import cookies from 'cookies-js';
import {assign} from 'lodash';

class RestFetcher {
	constructor(endpoint) {
		this.endpoint = endpoint;
	}
	fetch(query) {
		return axios.get(this.endpoint, {
			params: assign({}, this.sharedQuery),
			headers: this.getAuthHeader(),
		}).catch((response) => {
			console.log(this.endpoint);
			console.log(response);
			throw response;
		}).then((response) => { return response.data; });
	}
	put(data) {
		console.log(data);
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

const apiRoot = '../research-common/api/v1/';

module.exports = {
	rateClassType : new RestFetcher(apiRoot + 'rate-class-types/'), 
	rateClass : new RestFetcher(apiRoot + 'rate-classes/'),
	rateType : new RestFetcher(apiRoot + 'rate-types/'),
	activityType : new RestFetcher(apiRoot + 'activity-types/'),
	unit : new RestFetcher(apiRoot + 'units/'),
	topUnit : new RestFetcher(apiRoot + 'units/top-unit'),
	rate : new RestFetcher(apiRoot + 'institute-rates/'),
};