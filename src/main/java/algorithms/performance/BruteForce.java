package algorithms.performance;

import java.util.ArrayList;
import java.util.List;

public class BruteForce {
    int maxSum;

    int maxI;
    int maxJ;


    public void MaxSubArray(int[] numberUsersAtFacility){
        maxSum = numberUsersAtFacility[0];
        for(int i = 0; i < numberUsersAtFacility.length; i++)
        {
            int currSum = 0;

            for(int j = i; j < numberUsersAtFacility.length; j++)
            {
                currSum += numberUsersAtFacility[j];
                if(currSum > maxSum)
                {
                    maxSum = currSum;
                    maxI = i;
                    maxJ = j;
                }
            }
        }
    }
    public List<Integer> getResults(){
        List<Integer> results = new ArrayList<>();
        results.add(maxI);
        results.add(maxJ);
        results.add(maxSum);
        return results;
    }
}