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
		shellSort(nums);

		//快速排序
		//quickSort(nums);
		
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
		for (int i = 0; i < nums.size() - 1; i++) { 
			index = findMin(nums, i, nums.size()); //從未排序元素中找出最小值的索引
			if (index != i) //若最小元素不在未排序元素中的第一個，才可交換，否則自己交換自己，值會變0
				exchange(nums, i, index); //將該最小值交換到未排序元素中的第一個位置
		}
	}
	
	private static void bubbleSort(List<Integer> nums) {
		for (int i = 0; i < nums.size() - 1; i++) { //每回合都有一個最大值跑到最後面，而剩餘一個不用排(其為最小)，所以比較次數為size - 1
			for (int j = 0; j < nums.size() - 1; j++) {
				if (nums.get(j).compareTo(nums.get(j + 1)) > 0) //若前面比後面那一個大，就換到後面去
					exchange(nums, j, j + 1);
			}
		}
	}

	private static void insertionSort(List<Integer> nums) {
		int index = 0;
		for (int i = 0; i < nums.size() - 1; i++) {
			index = findMin(nums, i, nums.size()); //從未排序元素中找出最小值的索引
			for (int j = 0; j <= i; j++) { //從已排序元素中逐一比對，將被移動元素插入在第一個遇到比它大的元素之前；若都沒有，則放置在最後一個已排序元素後面
				if (nums.get(j).compareTo(nums.get(index)) > 0) {
					Collections.swap(nums, index, j);
					break;
				}
			}			
		}
	}
	
	private static void shellSort(List<Integer> nums) {
		int gap = nums.size() / 2;
		while (gap > 0) {
			for (int i = 0; i < nums.size(); i++) {
				if (i + gap < nums.size()) {
					if (nums.get(i).compareTo(nums.get(i + gap)) > 0) //比對某間隔的元素大小，若前面大於後面則交換
						exchange(nums, i, i + gap);
				}
			}
			gap /= 2; //縮短間隔
		}		
		insertionSort(nums); //幾乎排序好了，最後再做插入排序
	}
	
	private static void quickSort(List<Integer> nums) {
		if (nums.size() < 2) //元素不足兩個，則保持原樣，不必排序
			return;
		
		Integer midValue = nums.get(0); //已第一個元素當基準值
		List<Integer> lstLeft = new ArrayList<>(), lstRight = new ArrayList<>();
		for (int i = 1; i < nums.size(); i++) {
			if (nums.get(i).compareTo(midValue) < 0)
				lstLeft.add(nums.get(i)); //比基準值小的放在左清單
			else
				lstRight.add(nums.get(i)); //比基準值小或相等的放在右清單
		}
		
		//左右清單再持續進行快速排序
		quickSort(lstLeft);
		quickSort(lstRight);
		
		//拼接元素
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
		
	private static int binarySearch(List<Integer> nums, Integer target) {
		int first = 0, last = nums.size();
		/*
		 	假設first、mid, last為最後三個索引(5、6、7)，但未找到
			則first = mid = 6，得mid = (6 + 7) / 2，捨去小數點仍為6，造成無限尋找
			因此last應預設為元素個數，屆時mid = (6 + 8) / 2 = 7，可找到最後一個元素
		*/
		
		int mid = (last + first) / 2;
		Integer integer; 
		
		while (first < last) {
			integer = nums.get(mid);
			if (integer.compareTo(target) == 0)
				return mid;
			else if (integer.compareTo(target) > 0)
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
		nums.set(i, nums.get(i) ^ nums.get(j)); // i = i XOR j
		nums.set(j, nums.get(i) ^ nums.get(j)); // j = i XOR j = (i XOR j) XOR j = i XOR 0 = i		
		nums.set(i, nums.get(i) ^ nums.get(j)); // i = i XOR j = (i XOR j) XOR i = j		
	}
}