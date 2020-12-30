package tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
public class Retry implements IRetryAnalyzer {
 
    private int count = 1;  //By the time it comes here it is already 1.
    private static int maxTry = 3;
 
    @Override
    public boolean retry(ITestResult iTestResult) {
    	System.out.println("Result of last execution:"+iTestResult.isSuccess());
        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
            if (count < maxTry) { 
            	System.out.println("Count:"+count);//Check if maxtry count is reached
                count++;                                     //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                return true;                                 //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
 
}