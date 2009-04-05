package com.thompson234.sort;
import java.util.List;

public interface ISimpleSorter<T extends Comparable<? super T>> {

	public String getName();
	public void sortInPlace(List<T> toSort);
}
