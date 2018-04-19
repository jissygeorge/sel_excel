package com.testprojects.pageObjects;

import com.testprojects.helper.*;

import junit.framework.Assert;
import testproject.CarDetails;
import testproject.FileProperty;
import testproject.FileUtil;
import testproject.GetCarDetails;

import java.util.List;

import org.openqa.selenium.By;



public class CarRegistration extends PageObject {

	By startNowButton=By.xpath("//a[@class='pub-c-button pub-c-button--start']");
	By registrationNumber = By.xpath("//input[@class='form-control form-control-1-4 input-upper']");
	By continueButton = By.xpath("//button[@name='Continue']");
	By vehRegNum = By.xpath("//span[@class='reg-mark']");
	By radiobuttonYes = By.xpath("//input[@name='Correct']");
	By vehContinueButton =By.xpath("//button[@name='Continue']");
	By returnToGovUkButton = By.xpath("//input[@class='button']");
	
	By wvehicleMake =By.xpath("//span[contains(text(),'Vehicle make: ')]/following-sibling::span");
	By wdateOfRegistration =By.xpath("//span[contains(text(),'Date of first registration:')]/following-sibling::span");
	By wyearOfManufacture =By.xpath("//span[contains(text(),'Year of manufacture:')]/following-sibling::span");
	By wcylinderCapacity =By.xpath("//span[contains(text(),'Cylinder capacity (cc):')]/following-sibling::span");
	//By wCOEmissions =By.xpath("//span[contains(text(),'CO2 Emissions:')]/following-sibling::span");
	By wfueltype =By.xpath("//span[contains(text(),'Fuel type:')]/following-sibling::span");
	By wexportMarker =By.xpath("//span[contains(text(),'Export marker:')]/following-sibling::span");
	By wvehicleStatus =By.xpath("//span[contains(text(),'Vehicle status:')]/following-sibling::span");
	By wvehicleColour =By.xpath("//span[contains(text(),'Vehicle colour')]/following-sibling::span");
	By wvehicleTypeApproval =By.xpath("//span[contains(text(),'Vehicle type approval:')]/following-sibling::span");
	By wwheelPlan =By.xpath("//span[contains(text(),'Wheelplan:')]/following-sibling::span");
	By wrevenueWeight =By.xpath("//span[contains(text(),'Revenue weight:')]/following-sibling::span");
	
	
	
	
	
	
	public void clickOnStartNowButton()
	{
		
				element(startNowButton).click();
				
	}
	
	public void enterRegistrationNumber(String regNumber){
		element (registrationNumber).sendKeys(regNumber);
	}

	public void clickContinue() {
		element(continueButton).click();
	}
	
    public void vehicledetails(String regNum){
	
    	String vehRegistrationNumber = element(vehRegNum).getText();
    	System.out.println(" Vehicle Registration Number : " + vehRegistrationNumber);

    	
    	//if (vehRegistrationNumber.equals(regNum) )
    	 //{
    			element(radiobuttonYes).click();
    			element(vehContinueButton).click();
    	 //}
    	
    	
    }
    
    @SuppressWarnings("deprecation")
	public boolean verifyVehicleDetails(CarDetails iCarDetails){

       	System.out.println(" Vehicle make: " + element(wvehicleMake ).getText());
    	System.out.println(" Date of first registration: " + element(wdateOfRegistration).getText());
    	System.out.println("Year of manufacture: " + element(wyearOfManufacture).getText());
    	System.out.println(" Cylinder capacity (cc): " + element(wcylinderCapacity).getText());
    	//System.out.println(" CO Emissions:" + element(wCOEmissions).getText());
    	System.out.println(" Fuel type: " + element(wfueltype).getText());
    	System.out.println(" Export marker: " + element( wexportMarker).getText());
    	System.out.println(" Vehicle status: " + element(wvehicleStatus ).getText());
    	System.out.println(" Vehicle colour: " + element(wvehicleColour).getText());
    	System.out.println(" Vehicle type approval: " + element(wvehicleTypeApproval).getText());
    	System.out.println(" Wheelplan:" + element(wwheelPlan).getText());
    	System.out.println(" Revenue weight: " + element(wrevenueWeight).getText());

   
    	
       	CarDetails wCarDetails = new CarDetails (element(vehRegNum).getText(),element(wvehicleMake).getText(),
       			element(wdateOfRegistration).getText(),
    			element(wyearOfManufacture).getText(),
    			element(wcylinderCapacity).getText(),
    			//element(wCOEmissions).getText(),
    			element(wfueltype).getText(),
    			element(wexportMarker).getText(),
    			element(wvehicleStatus ).getText(),
    			element(wvehicleColour).getText(),
    			element(wvehicleTypeApproval).getText(),
    			element(wwheelPlan).getText(),
    			element(wrevenueWeight).getText()
    			);
  	
       	
     	if (iCarDetails.equals(wCarDetails))
     		return true;
     	else{
     		System.out.println(wCarDetails.toString());
     		return false;
     	}
    	
    }
    
    public void returnToHomePage(){
    	element(returnToGovUkButton).click();
    }
    
    public List<FileProperty> readcardetailsfromfolder(String path){
    	FileUtil fu = new FileUtil();
        List<FileProperty> lfp = fu.getFilesofMimeType(path, ".xls");
        return lfp;
    }
    public List<CarDetails> getCarsDetails(List<FileProperty> lfp)
    {
    	GetCarDetails gcd = new GetCarDetails();
    	List<CarDetails> carlist = gcd.getCarDetails(lfp);
    	return carlist;
    }
}
