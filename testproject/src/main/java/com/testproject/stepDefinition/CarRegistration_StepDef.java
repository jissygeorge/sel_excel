package com.testproject.stepDefinition;

import java.io.File;
import java.util.List;

import com.testproject.helper.Helper;
import com.testproject.pageObjects.CarRegistration;

import cucumber.api.Scenario;
import cucumber.api.java.en.*;
import junit.framework.Assert;
import com.testproject.servicelayer.CarDetails;
import com.testproject.servicelayer.FileProperty;
import com.testproject.servicelayer.FileUtil;

public class CarRegistration_StepDef {
	private CarRegistration carRegistration;
	private Scenario scenario;

	public CarRegistration_StepDef() {
		carRegistration = new CarRegistration();
		return;
	}

	public CarRegistration_StepDef(CarRegistration carRegistration) {
		this.carRegistration = carRegistration;

	}

	@Given("^I navigate to DVLA website$")
	public void i_navigate_to_DVLA_website() {
		Helper.openBrowser();
	}

	@Given("^I click on 'Start Now' button$")
	public void i_click_on_Start_Now_button() throws Throwable {
		carRegistration.clickOnStartNowButton();

	}

	@When("^I enter '(.*?)' as the Registration number$")
	public void i_enter_LC_VFN_as_the_Registartion_number(String regNumber) {
		carRegistration.enterRegistrationNumber(regNumber);
	}

	@When("^I click 'Continue'$")
	public void i_click_Continue() {
		carRegistration.clickContinue();
	}

	@Then("^I should see '(.*?)' in the Vehicle details page$")
	public void i_should_see_LC_VFN_BMW_Black_in_the_Vehicle_details_page(String regNum) {
		carRegistration.vehicledetails(regNum);
	}

	@Then("^I should see the details '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)', '(.*?)' on the screen$")
	public void should_see_the_details_on_the_screen(String regnum, String make, String DoR, String YoM, String engine,
			String fuel, String exportMarker, String status, String color, String approval, String wheelplan,
			String rweight) {

		CarDetails inputnewcar = new CarDetails(regnum, make, DoR, YoM, engine, fuel, exportMarker, status, color,
				approval, wheelplan, rweight);

		carRegistration.verifyVehicleDetails(inputnewcar);

	}

	@Then("^I click on Home button$")
	public void i_click_on_Home_button() {
		carRegistration.returnToHomePage();
	}

	@Then("^I close the browser$")
	public void i_close_the_browser() {
		Helper.getWebDriver().close();
		Helper.getWebDriver().quit();
	}

	@Given("^I read files from folder '(.*?)' and verify vehicle details at DVLA$")
	public void i_read_files_from_folder(String path) {

		File file = new File(path);
		
		System.out.println("File absolute path : "+file.getAbsolutePath());

		List<FileProperty> lfp = carRegistration.readcardetailsfromfolder(file.getAbsolutePath());

		for (FileProperty fp : lfp) {
			System.out.println(fp.toString());
		}

		List<CarDetails> carlist = carRegistration.getCarsDetails(lfp);

		for (CarDetails car : carlist) {
			System.out.println("*********  CAR DETAILS BEGINS ***********");
			System.out.println(car.toString());
			System.out.println("*********  CAR DETAILS ENDS ***********");
			System.out.flush();
		}

		boolean verify = true;
		String errorList = "";

		Helper.openBrowser();
		
		for (CarDetails car : carlist) {
			
			Helper.NavgiateToURL("https://www.gov.uk/get-vehicle-information-from-dvla");
			carRegistration.clickOnStartNowButton();
			carRegistration.enterRegistrationNumber(car.getCarRegNumber());
			carRegistration.clickContinue();
			carRegistration.vehicledetails(car.getCarRegNumber());
			verify = carRegistration.verifyVehicleDetails(car);

			if (!verify)
				errorList += car.toString();
		}

		Assert.assertTrue(errorList, errorList.isEmpty());

		Helper.closeBrowser();
	}

	// Then verify vehicle details at DVLA

}
