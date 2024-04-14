package api.utilities;
/*
 *@className -DataProviders
 * @Objective- The class have the data providers methods responsible for passing data to another test methods .
 */

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    //returning all data of the users
    @DataProvider(name ="Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx" ;
        XLUtility xl = new XLUtility(path);

        //get row and column count from excel sheet
        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1",1);

        //create 2D array for excel data
        String apidata [][] = new String[rownum][colcount];
        //start from 1 index becz of excel sheet row count starts from 1.
        for(int i=1; i<=rownum ;i++){
            for(int j=0;j<colcount;j++){
                apidata[i-1][j] = xl.getCellData("Sheet1",i,j);
            }
        }
        return apidata;
    }

    //returning all names of the users
    @DataProvider(name="UserNames")
    public Object[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        String apidata[] = new String[rownum];

        for(int i=1;i<=rownum;i++){
            apidata[i-1]= xl.getCellData("Sheet1",i,1);
        }
        return apidata;
    }
}
