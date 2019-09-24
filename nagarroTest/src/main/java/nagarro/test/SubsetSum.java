package nagarro.test;

public class SubsetSum {

	public boolean hasSum(int[] intArr, int sum) {	
		
		return subsetSum(intArr, intArr.length, sum);
	}
	
	private boolean subsetSum(int[] intArr,int arrLen, int sum) {
		
		if(sum == 0)
			return true;
		
		if(arrLen == 0 && sum > 0)
			return false;
		
		if(intArr[arrLen - 1] > sum) {
			return subsetSum(intArr, arrLen-1, sum);
		}
		
		return subsetSum(intArr, arrLen-1, sum) ||  
	            subsetSum(intArr, arrLen-1, sum-intArr[arrLen-1]); 
	}
}
