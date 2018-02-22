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
				
		//��ܱƧ�
		//selectionSort(nums);
		
		//��w�Ƨ�
		//bubbleSort(nums);
		
		//���J�Ƨ�
		//insertionSort(nums);

		//�¦ձƧ�
		shellSort(nums);

		//�ֳt�Ƨ�
		//quickSort(nums);
		
		//�C�X����
		for (Integer num : nums)
			System.out.println(num.intValue());
		
		//�G���j�M
		/*
		for (int i = 0; i < nums.size(); i++)
			System.out.println(nums.get(i).toString() + "�����ޡG" + binarySearch(nums, nums.get(i)));
		*/
	}	
	
	private static void selectionSort(List<Integer> nums) {
		int index = 0;
		for (int i = 0; i < nums.size() - 1; i++) { 
			index = findMin(nums, i, nums.size()); //�q���ƧǤ�������X�̤p�Ȫ�����
			if (index != i) //�Y�̤p�������b���ƧǤ��������Ĥ@�ӡA�~�i�洫�A�_�h�ۤv�洫�ۤv�A�ȷ|��0
				exchange(nums, i, index); //�N�ӳ̤p�ȥ洫�쥼�ƧǤ��������Ĥ@�Ӧ�m
		}
	}
	
	private static void bubbleSort(List<Integer> nums) {
		for (int i = 0; i < nums.size() - 1; i++) { //�C�^�X�����@�ӳ̤j�ȶ]��̫᭱�A�ӳѾl�@�Ӥ��α�(�䬰�̤p)�A�ҥH������Ƭ�size - 1
			for (int j = 0; j < nums.size() - 1; j++) {
				if (nums.get(j).compareTo(nums.get(j + 1)) > 0) //�Y�e����᭱���@�Ӥj�A�N����᭱�h
					exchange(nums, j, j + 1);
			}
		}
	}

	private static void insertionSort(List<Integer> nums) {
		int index = 0;
		for (int i = 0; i < nums.size() - 1; i++) {
			index = findMin(nums, i, nums.size()); //�q���ƧǤ�������X�̤p�Ȫ�����
			for (int j = 0; j <= i; j++) { //�q�w�ƧǤ������v�@���A�N�Q���ʤ������J�b�Ĥ@�ӹJ��񥦤j���������e�F�Y���S���A�h��m�b�̫�@�Ӥw�ƧǤ����᭱
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
					if (nums.get(i).compareTo(nums.get(i + gap)) > 0) //���Y���j�������j�p�A�Y�e���j��᭱�h�洫
						exchange(nums, i, i + gap);
				}
			}
			gap /= 2; //�Y�u���j
		}		
		insertionSort(nums); //�X�G�ƧǦn�F�A�̫�A�����J�Ƨ�
	}
	
	private static void quickSort(List<Integer> nums) {
		if (nums.size() < 2) //����������ӡA�h�O����ˡA�����Ƨ�
			return;
		
		Integer midValue = nums.get(0); //�w�Ĥ@�Ӥ������ǭ�
		List<Integer> lstLeft = new ArrayList<>(), lstRight = new ArrayList<>();
		for (int i = 1; i < nums.size(); i++) {
			if (nums.get(i).compareTo(midValue) < 0)
				lstLeft.add(nums.get(i)); //���ǭȤp����b���M��
			else
				lstRight.add(nums.get(i)); //���ǭȤp�ά۵�����b�k�M��
		}
		
		//���k�M��A����i��ֳt�Ƨ�
		quickSort(lstLeft);
		quickSort(lstRight);
		
		//��������
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
		 	���]first�Bmid, last���̫�T�ӯ���(5�B6�B7)�A�������
			�hfirst = mid = 6�A�omid = (6 + 7) / 2�A�˥h�p���I����6�A�y���L���M��
			�]��last���w�]�������ӼơA����mid = (6 + 8) / 2 = 7�A�i���̫�@�Ӥ���
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