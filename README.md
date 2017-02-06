# Capsule
A UI framework library

<!--Current Release 4.0-->

Mainly for more complex UIs, this library contains plenty of useful tools and shortened methods to make UI building easier.
This library is meant to offer a much simpler and more functional, albeit unconventional way of coding views. Note that a lot of the variables and interfaces start with the letter "c" (ie cFab; to make it easier to remember)

Capsule heavily relies on other powerful libraries to enable its functionality, and strips away a significant portion of code that is common in the standard navigation drawer layouts.
Check out the [sample app code](https://github.com/AllanWang/Capsule/tree/master/sample/src/main/java/ca/allanwang/capsule/sample) to see just how concise the code can be! Capsule also incorporates full implementations of common components such as changelogs and refreshable recycler views, and is built proguard friendly.

------------

Capsule is available on JitPack

[![](https://jitpack.io/v/ca.allanwang/capsule.svg)](https://jitpack.io/#ca.allanwang/capsule)

To apply, add the following to your root build.gradle:

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

And add the following dependencies:

```gradle
dependencies {
        compile 'ca.allanwang:capsule:v4.0' //Or a specific version/commit/-SNAPSHOT
}

```

[JitPack Javadocs v4.0](https://allanwang.github.io/Capsule/)
