package uz.ataboyev.debtbook;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
//        System.out.println(searchInsert(new int[]{0, 2, 3, 5, 8, 9, 99}, 1));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 0, 0, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 0, 0, 8})));
        System.out.println(Arrays.toString(plusOne(new int[]{5, 2, 2, 6, 5, 7, 1, 9, 0, 3, 8, 6, 8, 6, 5, 2, 1, 8, 7, 9, 8, 3, 8, 4, 7, 2, 5, 8, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9, 9, 9})));
    }

    public static int searchInsert(int[] nums, int target) {
        return treeSearch(nums, target, 0, nums.length);
    }

    public static int treeSearch(int[] nums, int target, int left, int right) {

        if (right - left == 1 && nums[(left + right) / 2] != target) {
            if (left == 0 && nums[0] > target) return 0;
            else return (left + right) / 2 + 1;
        }
        ;
        if (nums[(left + right) / 2] > target) {
            return treeSearch(nums, target, left, (left + right) / 2);
        } else if (nums[(left + right) / 2] < target) {
            return treeSearch(nums, target, (left + right) / 2, right);
        } else return (left + right) / 2;
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--) {
            if (digits[i]<9){digits[i]++;
            return digits;}
            else digits[i]=0;
        }
        int a[] = new int[digits.length+1];
        a[0] = 1;
        return a;
    }

}

