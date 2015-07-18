var gulp = require('gulp');
var aglio = require('gulp-aglio');

var dest = '../../../target/generated-web-sources/jsfrontend-web-sources'

gulp.task('docs', function () {
  gulp.src('apidocs/*.md')
    .pipe(aglio({ template: 'default', themeFullWidth: true, includePath : process.cwd() + '/apidocs/' }))
    .pipe(gulp.dest(dest + '/apidocs'));
});

gulp.task('default', ['docs']);