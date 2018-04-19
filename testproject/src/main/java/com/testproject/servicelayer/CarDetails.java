package com.testproject.servicelayer;

public class CarDetails {

	private String regNumber;

	private String make;

	private String DoR;

	private String YoM;

	private String engine;

	// private String co2;

	private String fuel;

	private String exportMarker;

	private String status;

	private String color;

	private String approval;

	private String wheelplan;

	private String rweight;

	public CarDetails(String regNum, String make, String DoR, String YoM, String engine

			, String fuel, String exportMarker,

			String status, String color, String approval, String wheelplan, String rweight)

	{

		this.regNumber = regNum;

		this.make = make;

		this.DoR = DoR;

		this.YoM = YoM;

		this.engine = engine;

		this.fuel = fuel;

		this.exportMarker = exportMarker;

		this.status = status;

		this.color = color;

		this.approval = approval;

		this.wheelplan = wheelplan;

		this.rweight = rweight;

	}

	public String getCarRegNumber() {
		return regNumber;
	}

	@Override

	public boolean equals(Object obj) {

		if (obj == null) {

			return false;

		}

		if (!(obj instanceof CarDetails))

			return false;

		CarDetails inCarDetails = (CarDetails) obj;

		String incarregNumber = inCarDetails.regNumber.replaceAll("\\s", "");
		String thiscarregNumber = this.regNumber.replaceAll("\\s", "");

		boolean ret = (incarregNumber.equalsIgnoreCase(thiscarregNumber)
				&& inCarDetails.make.equalsIgnoreCase(this.make) &&

				inCarDetails.DoR.equalsIgnoreCase(this.DoR) &&

				inCarDetails.YoM.equalsIgnoreCase(this.YoM) &&

				inCarDetails.engine.equalsIgnoreCase(this.engine) &&

				inCarDetails.fuel.equalsIgnoreCase(this.fuel) &&

				inCarDetails.exportMarker.equalsIgnoreCase(this.exportMarker) &&

				inCarDetails.status.equalsIgnoreCase(this.status) &&

				inCarDetails.color.equalsIgnoreCase(this.color) &&

				inCarDetails.approval.equalsIgnoreCase(this.approval) &&

				inCarDetails.rweight.equalsIgnoreCase(this.rweight) &&

				inCarDetails.wheelplan.equalsIgnoreCase(this.wheelplan));

		if (!ret)

		{

			System.out.println(this.toString());

			System.out.println(inCarDetails.toString());

			System.out.flush();

		}

		return ret;

	}

	public String toString() {

		CarDetails car = this;

		return " Reg Number : " + car.regNumber + ", make : " + car.make + ", Date of Reg : " + car.DoR
				+ ", Year of Manufactor : " + car.YoM + ", Engine : " + car.engine + ", Fuel : " + car.fuel

				+ ", ExportMaker : " + car.exportMarker + ", Status : " + car.status + ", Approval : " + car.approval
				+ ", Color : " + car.color + ", weight : " + car.rweight

				+ ", wheelplan : " + car.wheelplan;

	}

}
