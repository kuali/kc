const prefix = 'kc';
let DataSources = require('./' + prefix + '/DataSources');


function getDataSource(sourceName) {
	return DataSources[sourceName];
}

export { getDataSource as RestDataSources };