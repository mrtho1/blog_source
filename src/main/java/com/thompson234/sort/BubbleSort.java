package com.thompson234.sort;
import java.util.Collections;
import java.util.List;


public class BubbleSort<T extends Comparable<? super T>> implements ISimpleSorter<T> {

	public String getName() {
		return "Bubble Sort";
	}

	public void sortInPlace(List<T> toSort) {
		boolean keepSorting = true;

		while (keepSorting) {

			keepSorting = false;

			for (int x = 0; x < toSort.size() - 1; ++x) {
				T lhs = toSort.get(x);
				T rhs = toSort.get(x + 1);
				
				if (rhs.compareTo(lhs) < 0) {
					Collections.swap(toSort, x, x + 1);
					keepSorting = true;
				}
			}
		}
	}
}
