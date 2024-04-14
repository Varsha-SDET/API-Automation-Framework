package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *@className -ExtentReportManager
 * @Objective- The class have the methods responsible for generating reports for the test suites automatically on execution.
 */
public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    //on test starting execution
    public void onStart(ITestContext testContext){
        //time stamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp+ ".html";
        //specify location of report
        sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);
        //title of the report
        sparkReporter.config().setDocumentTitle("RestAssuredAPIAutomationProject");
        sparkReporter.config().setReportName("Pet - Store - Users API Test Result");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","Pest Store Users API");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment","Testing");
        extent.setSystemInfo("Tester","Varsha Rane");
    }

    //test pass
    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test Passed");

    }

    //test failure
    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,"Test Failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }

    //test skipped
    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,"Test Skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }

    //test finish
    public void onFinish(ITestContext testContext){
        extent.flush();
    }
}
