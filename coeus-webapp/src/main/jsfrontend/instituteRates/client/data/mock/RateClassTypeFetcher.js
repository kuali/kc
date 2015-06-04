


class RateClassFetcher {
	fetch() {
		return new Promise(function (resolve, reject) {
			setTimeout(function() {
				resolve(mockRateClasses);
			}, 250);
		});
	}
}

let rateClassFetcher = new RateClassFetcher();
module.exports = rateClassFetcher;