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