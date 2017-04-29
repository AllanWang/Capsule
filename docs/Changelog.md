# Changelog

## v4.4
* Add method to set Drawable for FAB icon
* Removed fade when checking a CheckBoxItem
* Added helper methods to CheckBoxItem
* Fixed BaseItemAnimator isRecyclable decremented below 0 issue
* Added numColumn hook to CapsuleSRVFragment
* Add dialog as parameter for NeutralButtonClick
* Added click events for toolbar 

## v4.3
* Make switchFragment public
* Create addFragment methods for backstacks
* Switch to Java 8
* Updated sample and readme
* Use CPage for ViewPager
* Add titleId to ViewPagerFragment
* Add page limit abstract in ViewPagerFragment
* Add TabClickEvent posting
* Add Appbar collapse listener
* Fully port SRV into Capsule

## v4.2
* Code cleanup
* Integrate SwipeRecyclerView
* Create abstract SRV fragment
* Update Changelog Dialog
* Added ShortCDrawerItem creations
* Upgraded dependencies

## v4.1
* Add more parcelables
* Update build tools
* Update dependencies

## v4.0
* Complete refractoring and renaming
* Proper namespacing
* Consumer proguard for capsule
* Use ValueAnimator for ripples
* Self generated javadocs

## v3.3
* Minor animation changes
* Code cleanup
* More javadocs
* Dependency updates

## v3.2
* Made AnimUtils compatible to Kitkat
* Created SwipeRecyclerFragment
* Added RecyclerView animations
* Added CollapsingToolbar support
* Do not switch drawerItems if selection is the same
* Added savedInstanceState to material drawer
* Added option to select drawer item by title id
* Created ParcelUtils
* Create parcelable map wrapper
* Added ViewPager modifier in ViewPagerFragment
* Better bulleted Changelog alignment

## v3.1
* Added Capsule reload option with drawer position saved
* Cleaned up AnimUtils
* Updated dependencies

## v3.0
* First fully stable release
* Events for Fab and Snackbar
* RippleCanvas for backgrounds with circular animations
* Null fixes
* More features for CFabEvent
* Some extra Javadocs

## v2.2
* Integrated EventBus - Fragments are no longer forced to extend CapsuleFragment
* Split up onCreate to allow for more flexibility
* Simplified fab creation

## v2.1
* Finished up viewpager
* Everything is working now
* Preparation for EventBus

## v2.0
* Added Activity Frame for easier activity creation
* Added viewpager support
* Modified Fab to be more customisable
* Lots of interfaces and separations

## v1.1
* Added coordinator layout customization
* Added snackbars for activities

## v1.0
* Separated Activity extensions for simplified reading
* Added some javadocs
* Fixed permission crash for null array

## v0.1:
* Initial
