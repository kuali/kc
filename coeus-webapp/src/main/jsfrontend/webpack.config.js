module.exports = {
	devtool : 'eval',
	entry: {
		instituteRatesClient: './instituteRates/client/index.jsx',
	},
	output: {
		path: __dirname + '../../../target/generated-web-sources/jsfrontend-web-sources/client/assets/',
		publicPath: '/client/assets/',
		filename: '[name].js',
		sourceMapFilename: '[file].map'
	},
	resolve: {
		extensions: ['', '.jsx', '.css', '.js']
	},
	module: {
		loaders: [
			{test: /\.js/, loader: 'babel-loader'},
			{test: /\.jsx/, loader: 'babel-loader'},
			{test: /\.css/, loader: 'style!css'}
		]
	}
}; 