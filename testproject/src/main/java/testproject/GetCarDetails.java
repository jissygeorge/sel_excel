package testproject;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetCarDetails {

    public List<CarDetails> getCarDetails(List<FileProperty> lfp){
    	
    	List<CarDetails> retls = new LinkedList<CarDetails>();
    	
    	
    	for(FileProperty fp : lfp) {
    		if(fp.fileMimeType.contains("application/vnd.ms-excel")) {
    			List<CarDetails> ls = new LinkedList<CarDetails>();
    			ls = readExcel (fp);
    			
    			for(CarDetails car : ls)
    				retls.add(car);
    			
    		}
    	}
		return retls;
    	
    }

    public List<CarDetails> readExcel(FileProperty fp) {

    	List<CarDetails> retls = new LinkedList<CarDetails>();
    	File file=null;
    	FileInputStream inputStream=null;
    //Create an object of File class to open xlsx file
    try {
    file =    new File(fp.filepath+"\\"+fp.filename);

    //Create an object of FileInputStream class to read excel file

    inputStream = new FileInputStream(file);

    Workbook cardetailswb = null;

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fp.filename.substring(fp.filename.indexOf("."));

    //Check condition if the file is xlsx file

    /*
    if(fileExtensionName.equals(".xlsx")){

    //If it is xlsx file then create object of XSSFWorkbook class

    guru99Workbook = new XSSFWorkbook(inputStream);

    }
    */

    //Check condition if the file is xls file

    if(fileExtensionName.equals(".xls")){

        //If it is xls file then create object of XSSFWorkbook class

    	cardetailswb = new HSSFWorkbook(inputStream);

    }

    //Read first sheet inside the workbook by its name

    Sheet cardetailsws = cardetailswb.getSheetAt(0);

    //Find number of rows in excel file

    int rowCount = cardetailsws.getLastRowNum()-cardetailsws.getFirstRowNum();

    //Create a loop over all the rows of excel file to read it

    for (int i = 1; i < rowCount+1; i++) { //ignore header row

        Row row = cardetailsws.getRow(i);
        
        if(!row.getCell(0).toString().isEmpty()) {
        
        	CarDetails newCar = new CarDetails(row.getCell(0).toString().trim(),row.getCell(1).toString().trim()
        		,row.getCell(2).toString().trim(),row.getCell(3).toString().trim(),row.getCell(4).toString().trim(),
        		row.getCell(5).toString().trim(),row.getCell(6).toString().trim(),row.getCell(7).toString().trim()
        		,row.getCell(8).toString().trim(),row.getCell(9).toString().trim(),row.getCell(10).toString().trim()
        		,row.getCell(11).toString().trim());

        	//System.out.println(newCar.toString());
        
        	retls.add(newCar);
        }
    	if(inputStream!=null)
    		inputStream.close();
    }
    }catch(IOException io) {
    	System.out.println(io.getMessage());
    }
    catch(Exception ex) {
    	System.out.println(ex.getMessage());
    }

    
    return retls;

    }

    


}