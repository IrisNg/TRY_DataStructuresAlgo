package DataStructures;

import java.util.*;
import java.util.Arrays;

public class DataStructures {

	public static String reverseString(String originalStr) {
		char[] charArr = originalStr.toCharArray();
		String reversed = "";

		StackArrayList<String> stack = new StackArrayList<String>();
		for (int i = 0; i < charArr.length; i++) {
			stack.push(String.valueOf(charArr[i]));
		}

		while (stack.size() > 0) {
			reversed += stack.pop();
		}

		return reversed;
	}

	public static void sortStack(StackArrayList<Integer> originalStack) {
		StackArrayList<Integer> sortedStack = new StackArrayList<Integer>();

		while (!originalStack.isEmpty()) {
			Integer value = originalStack.pop();

			if (sortedStack.isEmpty() || value >= sortedStack.peek()) {
				sortedStack.push(value);
			} else {
				while (!sortedStack.isEmpty() && value < sortedStack.peek()) {
					originalStack.push(sortedStack.pop());
				}
				sortedStack.push(value);
			}
		}
		while (!sortedStack.isEmpty()) {
			originalStack.push(sortedStack.pop());
		}
	}

	public static boolean isBalancedParentheses(String str) {
		char[] charArr = str.toCharArray();
		StackArrayList<String> stack1 = new StackArrayList<>();
		StackArrayList<String> stack2 = new StackArrayList<>();
		for (int i = 0; i < charArr.length; i++) {
			String current = String.valueOf(charArr[i]);

			if (current.equals("(")) {
				stack1.push(current);
			} else if (current.equals(")")) {
				stack2.push(current);
				;
			}
		}

		if (stack1.size() == stack2.size()) {
			return true;
		}
		return false;
	}

	public static boolean itemInCommon(int[] array1, int[] array2) {
		HashMap<String, Integer> hm = new HashMap<>();
		for (int el : array1) {
			hm.put(Integer.toString(el), el);
		}

		for (int el : array2) {
			if (hm.get(Integer.toString(el)) != null) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Integer> findDuplicates(int[] nums) {
		HashMap<String, Integer> hm = new HashMap<>();

		ArrayList<Integer> duplicates = new ArrayList<>();
		for (Integer num : nums) {
			String key = Integer.toString(num);
			if (hm.get(key) == null) {
				hm.put(key, num);
			} else if (!duplicates.contains(num)) {
				duplicates.add(num);
			}
		}
		return duplicates;
	}

	public static Character firstNonRepeatingChar(String str) {
		char[] charArray = str.toCharArray();
		HashMap<String, Integer> hm = new HashMap<>();

		for (char c : charArray) {
			String charStr = String.valueOf(c);

			if (hm.get(charStr) == null) {
				hm.put(charStr, 1);
			} else {
				hm.put(charStr, hm.get(charStr) + 1);
			}
		}

		for (char c : charArray) {
			String charStr = String.valueOf(c);

			if (hm.get(charStr) == 1) {
				return Character.valueOf(c);
			}
		}
		return null;
	}

	public static List<List<String>> groupAnagrams(String[] strings) {
		HashMap<Integer, List<String>> hm = new HashMap<>();

		for (String str : strings) {
			char[] charArray = str.toCharArray();
			Integer hash = 0;
			for (char c : charArray) {
				hash += (c * 23);
			}

			if (hm.get(hash) == null) {
				hm.put(hash, new ArrayList<String>());
			}
			List<String> reference = hm.get(hash);
			reference.add(str);
		}

		List<List<String>> anagramList = new ArrayList<>();
		hm.forEach((key, value) -> {
			anagramList.add(value);
		});

		return anagramList;
	}

	
	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		for(Integer index = 0; index < nums.length; index++) {
			Integer num = nums[index];
			
			Integer remaining = target - num;
			Integer indexOfRemaining = hm.get(remaining);
			
			if(indexOfRemaining != null) {
				return new int[] {indexOfRemaining, index};
			} else {
				hm.put(num, index);
			}
		}
		
		return new int[0];
	}

	public static int[] subarraySum(int[] nums, int target) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		// Prefix sum technique
		Integer cumulativeSum = 0;
		for(Integer index = 0; index< nums.length; index++) {
			Integer num = nums[index];
			
			System.out.println("index " + index);
			cumulativeSum += num;
			System.out.println("cumulative " + cumulativeSum);
			hm.put(index, cumulativeSum);
		}
		
		for(Integer outerEndIndex = -1; outerEndIndex< nums.length; outerEndIndex++) {
			Integer outerCumulativeSum = hm.get(outerEndIndex) == null? 0 : hm.get(outerEndIndex);
			
			for(Integer innerEndIndex = outerEndIndex + 1; innerEndIndex <  nums.length; innerEndIndex++) {
				Integer innerCumulativeSum = hm.get(innerEndIndex);
				
				System.out.println("outer index " + outerEndIndex);
				System.out.println("inner index " + innerEndIndex);
				System.out.println("outer sum " + outerCumulativeSum);
				System.out.println("inner sum " + innerCumulativeSum);
				System.out.println("expression " + (innerCumulativeSum - outerCumulativeSum));
				
				if ((innerCumulativeSum - outerCumulativeSum) == target) {
					return new int[] {outerEndIndex + 1, innerEndIndex};
				}
				
			}
		}
		

		return new int[0];
				
		// Brute force method
//		HashMap<Integer, Integer> hm = new HashMap<>();
//		
//		for(Integer index = 0; index < nums.length; index++) {
//			Integer num = nums[index];
//			if (num == target) {
//				return new int[] {index, index};
//			}
//			Integer remainingSum = target - num;
//			
//			for(Integer startingIndex = 0; startingIndex < index; startingIndex++) {
//				Integer existingSum = hm.get(startingIndex);
//				
//				if (existingSum == remainingSum) {
//					return new int[] {startingIndex, index};
//				} else {
//					hm.put(startingIndex, existingSum + num);
//				}
//			}
//			
//			hm.put(index, num);
//		}
//		return new int[0];
		
	}

	public static List<Integer> removeDuplicates(List<Integer> myList) {
		HashSet<Integer> hs = new HashSet<Integer>(myList);
		List<Integer> al = new ArrayList<Integer>(hs);
		return al;
	}

	public static boolean hasUniqueChars(String string) {
		if (string.length() == 0) {
			return true;
		}
		String[] strArr = string.split("");

		HashSet<String> hs = new HashSet<String>(Arrays.asList(strArr));
		
		if (hs.size() == string.length()) {
			return true;
		}
		return false;
	}

	public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
		Set<Integer> hs1 = new HashSet<>();
		List<int[]> pairs = new ArrayList<>();
		
		for(Integer el: arr1) {
			hs1.add(el);
		}
		
		for(Integer el: arr2) {
			Integer remaining = target - el;
			if (hs1.contains(remaining)) {
				pairs.add(new int[] {remaining, el});
			}
		}
		
		return pairs;
	}

	public static int longestConsecutiveSequence(int[] nums) {
		Set<Integer> set = new HashSet<>();
		
		for(Integer num: nums) {
			set.add(num);
		}
		
		Integer maxCounter = 0;
		
		for(Integer num: nums) {
			if (!set.contains(num - 1)) {
				Integer current = num;
				Integer counter = 1;
				
				while(set.contains(current + 1)) {
					current = current + 1;
					counter += 1;
				}
				if (counter > maxCounter) {
					maxCounter = counter;
				}
			}
		}
		return maxCounter;
	}
	

	  
	public static void main(String[] args) {
		
	int yo = Integer.parseInt("9999999991");
	System.out.println(yo);
	BigInteger.parseInt
	
//		int[] nums1 = {1, 2, 3, 4, 5};
//        int target1 = 9;
//        int[] result1 = subarraySum(nums1, target1);
//        System.out.println("[" + result1[0] + ", " + result1[1] + "]");

//        int[] nums2 = {-1, 2, 3, -4, 5};
//        int target2 = 0;
//        int[] result2 = subarraySum(nums2, target2);
//        System.out.println("[" + result2[0] + ", " + result2[1] + "]");

//        int[] nums3 = {2, 3, 4, 5, 6};
//        int target3 = 3;
//        int[] result3 = subarraySum(nums3, target3);
//        System.out.println("[" + result3[0] + ", " + result3[1] + "]");
//
//        int[] nums4 = {};
//        int target4 = 0;
//        int[] result4 = subarraySum(nums4, target4);
//        System.out.println("[]");
        
//		boolean result = hasUniqueChars("");
//		LinkedList linkedList = new LinkedList();
//		linkedList.append(9);
//		linkedList.append(10);
//		linkedList.append(11);
//		linkedList.append(12);
//		linkedList.prepend(5);
//		linkedList.prepend(3);
//		linkedList.prepend(1);
//		linkedList.reverseBetween(0, 1);

//		linkedList.removeFirst();
//		linkedList.removeLast();
//		linkedList.append(13);
//		linkedList.insert(1, 5);
//		linkedList.remove(1);
//		linkedList.append(15);
//		linkedList.append(20);
//		linkedList.reverse();
//		linkedList.set(1, 18);
//		linkedList.print();
//		linkedList.printLength();

//		boolean result = linkedList.hasLoop();
//		System.out.println(result);
//		linkedList.getHead().next.next.next = linkedList.getHead().next;
//		result = linkedList.hasLoop();
//		System.out.println(result);

//		Stack stack = new Stack();
//		stack.push(1);
//		stack.push(2);
//		stack.push(3);
//		stack.pop();
//		stack.print();
//		stack.printLength();

//		System.out.println(reverseString("paint"));

//		DoublyLinkedList dll = new DoublyLinkedList(1);
//		dll.append(2);
//		dll.append(3);
//		dll.append(4);
//		dll.append(5);
//		dll.swapPairs();
//		dll.print();

//		StackArrayList<Integer> sal = new StackArrayList<>();
//		sal.push(4);
//		sal.push(2);
//		sal.push(8);
//		sal.push(1);
//		sortStack(sal);
//		sal.print();
	}
}
