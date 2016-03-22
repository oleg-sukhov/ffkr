var gulp = require('gulp'),
    react = require('gulp-react'),
    bower = require('gulp-bower'),
    babel = require('gulp-babel'),
    run = require('gulp-run'),
    concat = require('gulp-concat'),
    del = require('del'),
    source = require('vinyl-source-stream'),
    package = require('./package.json');

gulp.task('bower', function () {
    return bower('./bower_components')
        .pipe(gulp.dest(package.dest.lib))
});

gulp.task('clean', function (cb) {
    del(['dist/**'], cb);
});

gulp.task('js', function () {
    return gulp.src(package.paths.jsx)
        .pipe(react({ harmony: true, es6module: true} ))
        .pipe(babel({"presets": ["es2015", "react"]}))
        .pipe(gulp.dest(package.dest.dist))
});

// Just running the two tasks
gulp.task('default', ['bower', 'clean', 'js']);