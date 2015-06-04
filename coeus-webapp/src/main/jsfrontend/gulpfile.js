var gulp = require('gulp');
var aglio = require('gulp-aglio');
var gutil = require('gulp-util');
var rename = require('gulp-rename');
var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var webpackConfig = require('./webpack.config.js');


var dest = process.env.OVERRIDE_DEST || '../../../target/generated-web-sources/jsfrontend-web-sources';
webpackConfig.output.path = dest + '/client/assets/';

gulp.task('docs', function () {
  gulp.src('apidocs/*.md')
    .pipe(aglio({ template: 'default', themeFullWidth: true, includePath : process.cwd() + '/apidocs/' }))
    .pipe(gulp.dest(dest + '/apidocs'));
});

gulp.task('webpack', function (callback) {
	var localWebpackConfig = Object.create(webpackConfig);
	localWebpackConfig.plugins = localWebpackConfig.plugins || [ ];
	localWebpackConfig.plugins = localWebpackConfig.plugins.concat(
		new webpack.DefinePlugin({
			"process.env" : { "NODE_ENV" : JSON.stringify("production") }
		}),
		new webpack.optimize.DedupePlugin(),
		new webpack.optimize.UglifyJsPlugin()
	);

	webpack(localWebpackConfig, function(err, stats) {
		if (err) throw new gutil.PluginError("webpack", err);
		gutil.log("[webpack]", stats.toString({ colors: true }));
		callback();
	});
});

gulp.task('static-assets', function() {
	gulp.src('client/*').pipe(gulp.dest(dest + '/client/'));
});

gulp.task('clients', ['webpack', 'static-assets']);

gulp.task('dev-build', ['static-assets','webpack:build-dev'], function() {
	gulp.watch(['instituteRates/**/*', 'client/**/*'], ['static-assets', 'webpack:build-dev']);
});

gulp.task('webpack:build-dev', function(callback) {
	var myDevConfig = Object.create(webpackConfig);
	myDevConfig.devtool = "sourcemap";
	myDevConfig.debug = true;

	var devWebpackCompiler = webpack(myDevConfig);
	devWebpackCompiler.run(function(err, stats) {
		if (err) throw new gutil.PluginError('webpack:build-dev', err);
		gutil.log('[webpack:build-dev]', stats.toString({ color: true }));
		callback();
	});
});


gulp.task('default', ['docs', 'clients']);