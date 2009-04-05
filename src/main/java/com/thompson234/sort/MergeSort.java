package com.thompson234.sort;
import java.util.List;


public class MergeSort<T extends Comparable<? super T>> implements ISimpleSorter<T> {

	public String getName() {
		return "Merge Sort";
	}

	public void sortInPlace(List<T> toSort) {
		mergeSort(toSort, 0, toSort.size());		
	}

	private void mergeSort(List<T> toSort, int start, int end) {

		//return if our sublist size < 2
		if (end - start < 2) {
			return;
		}
		
		int split = start + (end - start) / 2;
		
		mergeSort(toSort, start, split);
		mergeSort(toSort, split, end);
		merge(toSort, start, split, split, end);
	}

	private void merge(List<T> toSort, int lStart, int lEnd, int rStart, int rEnd) {
		
		int lIndex = lStart;
		int rIndex = rStart;
		
		while (lIndex < lEnd && rIndex < rEnd) {
		
			if (toSort.get(rIndex).compareTo(toSort.get(lIndex)) < 0) {
				
				T rValue = toSort.get(rIndex);

				for (int x = rIndex; x > lIndex; --x) {
					toSort.set(x, toSort.get(x - 1));
				}
					
				toSort.set(lIndex, rValue);
				
				++lEnd;
				++rIndex;			
			}
			
			++lIndex;
		}
	}
}
