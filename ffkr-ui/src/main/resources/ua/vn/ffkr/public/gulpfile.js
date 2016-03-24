var gulp = require('gulp'),
    bower = require('gulp-bower'),
    browserify = require('browserify'),
    babelify = require('babelify'),
    source = require('vinyl-source-stream'),
    del = require('del'),
    package = require('./package.json');

gulp.task('bower', function () {
     return bower('./bower_components')
         .pipe(gulp.dest(package.dest.lib))
});

gulp.task('clean', function (cb) {
     del(['dist/app/**'], cb);
});

gulp.task('build', function () {
    browserify({ entries: package.paths.jsx, extensions: ['.jsx'], debug: true })
        .transform('babelify', {presets: ['es2015', 'react'], compact: true})
        .bundle()
        .pipe(source('ffkr.js'))
        .pipe(gulp.dest(package.dest.dist));
});

gulp.task('default', ['clean', 'bower' , 'build']);