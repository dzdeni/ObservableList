ObservableList
==============
An observable list is a list data structure which notifies it's listeners when the list's content changes. It will not notify its listeners when an object reference by the list changes just when a new element or collection of elements is added or removed.

It uses Google's Guava library.

An example and a unit test included which uses Mockito (because it's delicious).

How to use
==========

Just like java.util.List

This is a an example change listener implementation: 
`public class ListChangeListener implements ObservableList.ChangeListener { 
	@Override 
		public void onObservableListChanged() { 
		// the ObservableList has been changed 
		// notify ListView's data adapter about the change 
		if (adapter != null) { 
			adapter.notifyDataSetChanged(); 
		}
	}
}`
This is how you can declare an observable list:
`ObservableList<DeviceStatus> observableList = new ObservableList<DeviceStatus>(new ArrayList<DeviceStatus>());`

And this is how you can create, register and unregister the listeners:
`observableListChangeListener = new ListChangeListener();`
`observableList.addListener(observableListChangeListener);`
`observableList.removeListener(observableListChangeListener);`

Build
=====
Compile: `javac -d ./bin -cp ./lib/* ./src/hu/co/digital/observablelist/*.java`
Run (Test): `java -cp ./lib/*;./bin org.junit.runner.JUnitCore hu.co.digital.observablelist.ObservableTest` (it's not an exhaustive test suite, just some example)