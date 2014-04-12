package hu.co.digital.observablelist;

import java.util.*;

import com.google.common.collect.ForwardingList;

class ObservableList<E> extends ForwardingList<E> {
	
	public interface ChangeListener { 
		void onObservableListChanged();
	}
	
	private List<ChangeListener> listeners = new ArrayList<ChangeListener>();
	final List<E> delegate; // backing list
  
	public ObservableList(List<E> delegate) {
		this.delegate = delegate;
	}
  
	@Override
	protected List<E> delegate() {
		return delegate;
	}

	@Override
	public void add(int index, E element) {		
		super.add(index, element);
		listChanged();
	}
	
	@Override
	public boolean add(E elem) {
		return standardAdd(elem);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return standardAddAll(c);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (standardAddAll(index, c)) {
			listChanged();
			return true;
		}
		return false;
	}

	@Override
	public E remove(int index) {
		E tmp = super.remove(index); 
		listChanged();
		return tmp;
	}
	
	@Override
	public boolean remove(Object elem) {
		if (standardRemove(elem)) {
			listChanged();
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) { 
		if (standardRemoveAll(c)) {
			listChanged();
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		super.clear();
		listChanged();
	}

	public void addListener(ChangeListener listener) {
		listeners.add(listener);
	}
  
	public void removeListener(ChangeListener listener) {
		listeners.remove(listener);
	}

	private void listChanged() {
		for (ChangeListener changeListener : listeners)
            changeListener.onObservableListChanged();
	}

}