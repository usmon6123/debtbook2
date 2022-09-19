package uz.ataboyev.debtbook;

public class Test {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{ 0, 2, 3, 5, 8, 9, 99}, 1));
    }

    public static int searchInsert(int[] nums, int target) {
        return treeSearch(nums, target, 0, nums.length);
    }

    public static int treeSearch(int[] nums, int target, int left, int right) {

        if (right - left == 1 && nums[(left + right) / 2] != target){
            if (left == 0 && nums[0]>target)return 0;
            else return (left + right) / 2 + 1;
        };
        if (nums[(left + right) / 2] > target) {
            return treeSearch(nums, target, left, (left + right) / 2);
        } else if (nums[(left + right) / 2] < target) {
            return treeSearch(nums, target, (left + right) / 2, right);
        } else return (left + right) / 2;
    }


}

