package nagarro.test;

public class SubsetSum {

	public boolean hasSum(int[] intArr, int sum) {

		return subsetSum(intArr, intArr.length - 1, sum);
	}

	private boolean subsetSum(int[] intArr, int arrLen, int sum) {

		if (sum == 0)
			return true;

		if (arrLen == 0 && (sum > 0 || sum < 0))
			return false;

		if (sum > 0) {
			if (intArr[arrLen] > sum) {
				return subsetSum(intArr, arrLen - 1, sum);
			}
		}

		if (sum < 0) {
			if (intArr[arrLen] < sum) {
				return subsetSum(intArr, arrLen - 1, sum - intArr[arrLen]);
			}
		}
		
		boolean including = subsetSum(intArr, arrLen - 1, sum);  //another way of writing
		boolean excluding = subsetSum(intArr, arrLen - 1, sum - intArr[arrLen]);
		
		//return subsetSum(intArr, arrLen - 1, sum) || subsetSum(intArr, arrLen - 1, sum - intArr[arrLen]);
		return including || excluding;
	}
}
