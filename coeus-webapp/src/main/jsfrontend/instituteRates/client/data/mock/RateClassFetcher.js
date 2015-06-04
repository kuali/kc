
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