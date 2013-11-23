# Introduction

This project is forked from [Davis Ford's android-chromium](https://github.com/davisford/android-chromium) it is very important to read the original documentation from his repository.

## Goal of this Project
The goal of this project is to make the Cordova API to run inside the Chromium WebView.

## Project Structure
This project contains three sub-project added to the [android-chromium](https://github.com/davisford/android-chromium) code.

* **cordova** - The version 2.9 of Cordova framework, I've made some changes to it so that it works with the Chromium Web View.
* **cordova-chromium** The bridge between Chromium classes and the Cordova classes. It contains the bare minimum code to make the cordova framework compile and replaces the stock Android Web View interfaces with Chromium ones.
* **cordova-chromium-sample** The APK project that uses both the cordova( which in turn depends on cordova-chromium).

### Build / Install

Make a copy of `local.properties.sample` and edit it to point to the folder where your SDK lives

```sh
$ cp local.properties.sample local.properties
$ vi local.properties
```

Use the [gradle wrapper](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html). Run `gradlew build` (Mac / Linux) or `gradlew.bat build` (Windows) -- either command will build *everything* in the whole project space.  Alternatively, you can [install gradle yourself, if you'd prefer to not use the wrapper](http://www.gradle.org/installation) (it is painless).

```sh
$ cd cordova-chromium-sample && gradle installDebug
```

## Known Issues
* Since I've taken code from `Content Shell` project to make Cordova work, The `file:///android_asset` and `file:///android_res` url schemes does not work I guess that is because the native .so library file that comes with `Content Shell` does not support these kind of schemes so a temporary solution is to copy a www.zip file to the assets folder of the project and unzip the file to a location on the data directory of the application and load the files from there. 
There are plans in the future to make it work.