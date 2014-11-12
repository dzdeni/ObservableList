import ObservableList.ChangeListener;

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

@RunWith(MockitoJUnitRunner.class)
public class ObservableTest {
	
	@Mock ChangeListener mockedListener;

	@Before
	public void setup() {
		mockedListener = mock(ObservableList.ChangeListener.class);
	}

	@Test
	public void add() {
		ObservableList<Integer> observableList = new ObservableList<Integer>(new ArrayList<Integer>());
		observableList.add(2);
		assertEquals(2, (int) observableList.get(0));
	}

	@Test
	public void clear() {
		ObservableList<Integer> observableList = new ObservableList<Integer>(new ArrayList<Integer>());
		observableList.addAll(Arrays.asList(2, 3, 5, 15));
		observableList.clear();
		assertEquals(0, observableList.size());
	}

	@Test
	public void addEvent() {
		ObservableList<String> observableList = new ObservableList<String>(new ArrayList<String>());
		observableList.addListener(mockedListener);
		observableList.add("someThing");
		verify(mockedListener, times(1)).onObservableListChanged();
	}
}