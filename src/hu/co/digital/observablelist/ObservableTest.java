package hu.co.digital.observablelist;

import hu.co.digital.observablelist.ObservableList.ChangeListener;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ObservableTest {
	
	public class SimpleChangeListener implements ObservableList.ChangeListener {

		@Override
		public void onObservableListChanged() {}

	}
	
	@Mock ObservableList<Integer> mockedList;
	@Mock ChangeListener mockedListener;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		mockedList = mock(ObservableList.class);
		mockedListener = mock(SimpleChangeListener.class);
	}

	@Test
	public void mockAdd() {
		mockedList.clear();
		mockedList.add(5);
		when(mockedList.get(0)).thenReturn(5);
		assertEquals(5, (int)mockedList.get(0));
		verify(mockedList, times(1)).add(5);
	}
	
	@Test
	public void mockClear() {
		mockedList.clear();
		mockedList.addAll(Arrays.asList(2, 3, 5, 15));
		mockedList.clear();
		assertEquals(null, mockedList.get(0));
	}

	@Test
	public void mockEvent() {
		ObservableList<String> observableList = new ObservableList<String>(new ArrayList<String>());
		observableList.addListener(mockedListener);
		observableList.add("one");
		observableList.add(1, "two");
		observableList.addAll(Arrays.asList("four", "seven"));
		observableList.addAll(4, Arrays.asList("three", "five"));
		observableList.remove(0);
		observableList.removeAll(Arrays.asList("seven", "four"));
		observableList.clear();
		verify(mockedListener, times(8)).onObservableListChanged();
	}
}