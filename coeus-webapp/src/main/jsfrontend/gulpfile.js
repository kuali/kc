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
		new webpack.optimize.DedupePlugin()
	);

	webpack(localWebpackConfig, function(err, stats) {
		if (err) throw new gutil.PluginError("webpack", err);
		gutil.log("[webpack]", stats.toString({ colors: true }));
		callback();
	});
});

gulp.task('static-assets', function() {
	gulp.src('client/**/*.html').pipe(gulp.dest(dest + '/WEB-INF/client/'));
	gulp.src('client/**/*').pipe(gulp.dest(dest + '/client/'));
});

gulp.task('clients', ['webpack', 'static-assets']);

gulp.task('dev-build', ['static-assets','webpack:build-dev'], function() {
	gulp.watch(['instituteRates/**/*', 'client/**/*'], ['static-assets', 'webpack:build-dev']);
});

gulp.task('default', ['docs', 'clients']);
