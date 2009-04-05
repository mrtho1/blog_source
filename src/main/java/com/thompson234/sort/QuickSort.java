package com.thompson234.sort;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class QuickSort<T extends Comparable<? super T>> implements ISimpleSorter<T> {

	public String getName() {
		return "Quick Sort";
	}

	private void quickSort(List<T> toSort, int start, int end) {
	
		if (end - start < 2) {
			return;
		}
		
		Random random = new Random(System.currentTimeMillis());
		//Random pivot value
		int pIndex = start + random.nextInt(end - start);
		T pValue = toSort.get(pIndex);
		
		//Move pivot to end
		Collections.swap(toSort, pIndex, end - 1);
		int lIndex = start;
		
		for (int x = start; x < end - 1; ++x) {
			
			T current = toSort.get(x);
			
			if (current.compareTo(pValue) <= 0) {
				Collections.swap(toSort, x, lIndex);
				++lIndex;
			}
		}
		
		Collections.swap(toSort, lIndex, end - 1);
		
		//pivot index is now ltIndex
		//Note: if pivot index is 0, this method call will
		//immediately return because of the length check up top
		quickSort(toSort, start, lIndex);
		quickSort(toSort, lIndex + 1, end);		
	}
	
	public void sortInPlace(List<T> toSort) {
		quickSort(toSort, 0, toSort.size());
	}

}
