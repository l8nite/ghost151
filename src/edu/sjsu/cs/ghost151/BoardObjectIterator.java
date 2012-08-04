package edu.sjsu.cs.ghost151;

import java.util.Iterator;

public class BoardObjectIterator implements Iterator<BoardObject> {
	private BoardObject[] items;
	int index;

	public BoardObjectIterator(BoardObject[] items) {
		this.items = items;
		index = 0;
	}
	
	@Override
	public boolean hasNext() {
		return index < items.length;
	}

	@Override
	public BoardObject next() {
		return items[index++];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
