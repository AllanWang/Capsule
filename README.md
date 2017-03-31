# Capsule
A UI framework library

<!--Current Release 4.2-->

Mainly for more complex UIs, this library contains plenty of useful tools and shortened methods to make UI building easier.
This library is meant to offer a much simpler and more functional, albeit unconventional way of coding views. Note that a lot of the variables and interfaces start with the letter "c" (ie cFab; to make it easier to remember)

Capsule heavily relies on other powerful libraries to enable its functionality, and strips away a significant portion of code that is common in the standard navigation drawer layouts.
Check out the [sample app code](https://github.com/AllanWang/Capsule/tree/master/sample/src/main/java/ca/allanwang/capsule/sample) to see just how concise the code can be! Capsule also incorporates full implementations of common components such as changelogs and refreshable recycler views, and is built proguard friendly.

------------

<img src="https://github.com/AllanWang/Storage-Hub/blob/master/capsule_showcase.gif" height="75%">

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

And add the following dependencies (You can use a specific version, commit, or -SNAPSHOT):

```gradle
dependencies {
        compile 'ca.allanwang:capsule:v4.2'
}

```

[Capsule Javadocs v4.2](https://jitpack.io/ca/allanwang/capsule/v4.2/javadoc/)

[Full Changelog](https://github.com/AllanWang/Capsule/blob/master/docs/Changelog.md)

# Features

## Easy Activity &amp; Fragment Handling

One of Capsule's primary goals is to simplify the creation of Activities and Fragment frames. Most apps come with the standard design of a drawer, toolbar, etc. Capsule bundles all that into [CapsuleActivityFrame](https://github.com/AllanWang/Capsule/blob/master/capsule/src/main/java/ca/allanwang/capsule/library/activities/CapsuleActivityFrame.java), along with CapsuleFragment, which can easily modify the FAB, expand/hide the appbar, add a viewpager with tabs, show snackbars, and much more.

## Seamless Changelog Dialog

Want a changelog dialog? Simply show one through

```java
ChangelogDialog.show(activity, xmlRes);
```

The xml is easy to create, and contains the following format:

```xml
<version title="version here"/>
<item text="item here"/>
<item text="more items"/>
```

The items will be automatically bulleted, and empty tags will be ignored. There is also an optional neutral callback button in the dialog that can be set up.

## SwipeRecyclerView

Capsule integrates with SwipeRecyclerView, and supports its own [CapsuleSRVFragment](https://github.com/AllanWang/Capsule/blob/master/capsule/src/main/java/ca/allanwang/capsule/library/fragments/CapsuleSRVFragment.java). This fragment will automatically extend our base fragment, provide the layout of a SRV, along with callbacks for easy refreshes and manipulations.

## Utilities

Capsule comes with a LOT of utilities, all available [here](https://github.com/AllanWang/Capsule/tree/master/capsule/src/main/java/ca/allanwang/capsule/library/utils). Utils range from colour transformations to animations to email builders.
