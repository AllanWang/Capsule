# Capsule
A UI framework library

<!--Current Release 4.3-->

Mainly for more complex UIs, this library contains plenty of useful tools and shortened methods to make UI building easier.
This library is meant to offer a much simpler and more functional, albeit unconventional way of coding views. Note that a lot of the variables and interfaces start with the letter "c" (ie cFab; to make it easier to remember)

Capsule heavily relies on other powerful libraries to enable its functionality, and strips away a significant portion of code that is common in the standard navigation drawer layouts.
Check out the [sample app code](https://github.com/AllanWang/Capsule/tree/master/sample/src/main/java/ca/allanwang/capsule/sample) to see just how concise the code can be! Capsule also incorporates full implementations of common components such as changelogs and refreshable recycler views, and is built proguard friendly.

------------

## Demo

<img src="https://github.com/AllanWang/Storage-Hub/blob/master/capsule_showcase.gif" width="35%">

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
        compile 'ca.allanwang:capsule:v4.3'
}

```

[Capsule Javadocs v4.3](https://jitpack.io/ca/allanwang/capsule/v4.3/javadoc/)

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

SwipeRecyclerView is a custom view combining the RecyclerView, SwipeRefreshLayout, FastAdapter, as well as adapter animations. It simplifies one of the most commonly used view combination into one class, and contains many helper methods.
Capsule also further integrates SRV with its own [CapsuleSRVFragment](https://github.com/AllanWang/Capsule/blob/master/capsule/src/main/java/ca/allanwang/capsule/library/fragments/CapsuleSRVFragment.java). This fragment will automatically extend our base fragment, provide the layout of a SRV, along with callbacks for easy refreshes and manipulations.

## Event Driven Structure

Many components in Capsule are event based, meaning you don't need to know or hold a context for what you are changing to change it. For exampl, the FAB is entirely managed by CapsuleActivity, but can be modified anywhere by sending a [CFabEvent](https://github.com/AllanWang/Capsule/blob/master/capsule/src/main/java/ca/allanwang/capsule/library/event/CFabEvent.java).
In CapsuleFragments, disabling the FAB is as easy as `postEvent(new CFabEvent(false))`. You can also show the FAB, change its icon and click listener, and much more.

CapsuleSRVFragment also uses [RefreshEvent](https://github.com/AllanWang/Capsule/blob/master/capsule/src/main/java/ca/allanwang/capsule/library/event/RefreshEvent.java) to trigger SRV refreshes. They can be called anywhere with `EventBus.getDefault.post(new RefreshEvent(...))`

## Utilities

Capsule comes with a LOT of utilities, all available [here](https://github.com/AllanWang/Capsule/tree/master/capsule/src/main/java/ca/allanwang/capsule/library/utils). Utils range from colour transformations to animations to email builders.

## RippleView

Ripples are one of the defining changes for Material Design. However, it is often hard to implement properly. RippleView gives you a background capable of forming (many) ripples, which is also a great way to transition into a new background colour. An example would be a RippleView behind a toolbar, and sending a ripple based on the contents, much like that in Google Calendar.
