package com.thompson234.sort;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class SimpleSorts {
	
	private static final Logger LOG = Logger.getLogger(SimpleSorts.class.getName());
	
	private static final int ELEMENT_COUNT = 10000;
	private static final int RUN_COUNT = 100;
		
	@SuppressWarnings("unchecked")
	private static final List<Integer> clone(List<Integer> toClone) {
		return (List<Integer>) ((ArrayList<Integer>) toClone).clone();
	}

	private List<ISimpleSorter<Integer>> _sorters;
	private Map<String, List<Integer>> _data;
	
	
	public SimpleSorts() {
		
	}
	
	private void tearDown() {
		_sorters = null;
		_data = null;
	}
	
	private void init(int elementCount) {
		tearDown();
	
		_sorters = new ArrayList<ISimpleSorter<Integer>>();
		_sorters.add(new BubbleSort<Integer>());
		_sorters.add(new MergeSort<Integer>());
		_sorters.add(new QuickSort<Integer>());
		
		_data = new HashMap<String, List<Integer>>();
		List<Integer> ordered = new ArrayList<Integer>();
		List<Integer> reverseOrdered = new ArrayList<Integer>();
		List<Integer> random = new ArrayList<Integer>();
		_data.put("Ordered", ordered);
		_data.put("Reverse Ordered", reverseOrdered);
		_data.put("Random", random);
		
		Random generator = new Random(System.currentTimeMillis());
		for (int x = 0; x < elementCount; ++x) {
			ordered.add(x);
			reverseOrdered.add(elementCount - x - 1);
			random.add(generator.nextInt(elementCount));
		}
	}
	
	public void testSorter(ISimpleSorter<Integer> sorter, int runCount) {
		
		for (Map.Entry<String, List<Integer>> entry : _data.entrySet()) {
			List<Long> runTimes = new ArrayList<Long>();

			for (int x = 0; x < runCount; ++x) {				
				List<Integer> data = clone(entry.getValue());
				LOG.finer("Sorting: " + data);
				long start = System.currentTimeMillis();
				sorter.sortInPlace(data);
				runTimes.add(System.currentTimeMillis() - start);
				LOG.finer("Result : " + data);
				LOG.fine(x + ". " + sorter.getName() + "(" + entry.getKey() + "): " + runTimes.get(runTimes.size() - 1) + " ms");
			}
			
			long sum = 0;
			for (Long millis : runTimes) {
				sum += millis;
			}

			LOG.info("Average " + sorter.getName() + " on " + entry.getKey() + 
					 " List with " + entry.getValue().size() + " items: " + 
					 (sum / runTimes.size()) + " ms.");
		}
	}
	
	public void run(int elementCount, int runCount) {
		init(elementCount);

		for (ISimpleSorter<Integer> sorter : _sorters) {
			testSorter(sorter, runCount);
		}
		
		tearDown();
	}
	
	public static void main(String[] args) throws Exception {
				
		new SimpleSorts().run(ELEMENT_COUNT, RUN_COUNT);
	}
}
