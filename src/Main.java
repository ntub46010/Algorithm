import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();		
		nums.add(new Integer(5));
		nums.add(new Integer(4));
		nums.add(new Integer(8));
		nums.add(new Integer(7));
		nums.add(new Integer(2));
		nums.add(new Integer(6));
		nums.add(new Integer(3));
		nums.add(new Integer(1));		
				
		//選擇排序
		//selectionSort(nums);
		
		//氣泡排序
		//bubbleSort(nums);
		
		//插入排序
		//insertionSort(nums);

		//謝耳排序
		//shellSort(nums);

		//快速排序
		quickSort(nums);
		
		//列出元素
		for (Integer num : nums)
			System.out.println(num.intValue());
		
		//二元搜尋
		/*
		for (int i = 0; i < nums.size(); i++)
			System.out.println(nums.get(i).toString() + "位於索引：" + binarySearch(nums, nums.get(i)));
		*/

	}
	
	
	private static void selectionSort(List<Integer> nums) {
		int index = 0;
		Integer tmp;
		for (int i = 0; i < nums.size() - 1; i++) {
			index = findMin(nums, i, nums.size());
			exchange(nums, i, index);
		}
	}
	
	private static void bubbleSort(List<Integer> nums) {
		Integer tmp;
		for (int i = 0; i < nums.size(); i++) {
			for (int j = 0; j < nums.size() - 1; j++) {
				if (nums.get(j).compareTo(nums.get(j + 1)) > 0)
					exchange(nums, j, j + 1);
			}
		}
	}

	private static void insertionSort(List<Integer> nums) {
		int index = 0;
		for (int i = 0; i < nums.size() - 1; i++) {
			index = findMin(nums, i, nums.size());
			for (int j = 0; j <= i; j++) {
				if (nums.get(index).compareTo(nums.get(j)) < 0) {
					Collections.swap(nums, index, j);
					break;
				}else if (j == i)
					Collections.swap(nums, index, i);
			}			
		}
	}
	
	private static void shellSort(List<Integer> nums) {
		int gap = nums.size() / 2;
		while (gap > 0) {
			for (int i = 0; i < nums.size(); i++) {
				try {
					if (nums.get(i).compareTo(nums.get(i + gap)) > 0) {
						exchange(nums, i, i + gap);
					}
				}catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
			gap /= 2;
		}
		insertionSort(nums);
	}
	
	private static void quickSort(List<Integer> nums) {
		if (nums.size() < 2)
			return;
		
		Integer midValue = nums.get(0);
		List<Integer> lstLeft = new ArrayList<>(), lstRight = new ArrayList<>();
		for (int i = 1; i < nums.size(); i++) {
			if (nums.get(i).compareTo(midValue) < 0)
				lstLeft.add(nums.get(i));				
			else
				lstRight.add(nums.get(i));
		}
		
		quickSort(lstLeft);
		quickSort(lstRight);
		
		nums.clear();
		nums.addAll(lstLeft);
		nums.add(midValue);
		nums.addAll(lstRight);
	}
	
	
	private static int sequentialSearch(List<Integer> nums, Integer integer) {
		for (int i = 0; i < nums.size(); i++) {
			if (nums.get(i).equals(integer))
				return i;
		}
		return -1;		
	}
	
	
	private static int binarySearch(List<Integer> nums, Integer integer) {
		int first = 0, last = nums.size();
		int mid = (last + first) / 2;
		Integer target; 
		
		while (first < last) {
			target = nums.get(mid);
			if (target.compareTo(integer) == 0)
				return mid;
			else if (target.compareTo(integer) > 0)
				last = mid;
			else
				first = mid;
			
			mid = (last + first) / 2;
		}		
		return -1;
	}
	
		
	private static int findMin(List<Integer> nums, int startIndex, int end) {
		int min = startIndex;
		if (nums.size() >= 2) {
			for (int i = startIndex; i < end; i++) {
				if (nums.get(i).compareTo(nums.get(min)) < 0)
					min = i;
			}	
		}		
		return min;
	}	
	
	private static void exchange(List<Integer> nums, int i, int j) {
		Integer tmp = nums.get(i);
		nums.set(i, nums.get(j));
		nums.set(j, tmp);		
	}

}
