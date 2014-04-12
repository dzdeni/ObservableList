ObservableList
==============
An observable list is a list data structure which notifies it's listeners when the list's content changes.

It uses Google's Guava library.

The included unit test uses Mockito (because it's delicious).

How to:
Compile:

javac -cp ./lib/* ./src/hu/co/digital/observablelist/*.java

Test:

java -cp ./lib/*;./src org.junit.runner.JUnitCore hu.co.digital.observablelist.SimpleTest