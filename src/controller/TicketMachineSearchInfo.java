package controller;

public class TicketMachineSearchInfo {

	public TicketMachineSearchInfo(){}
	
	private String location;
	private int district;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) throws Exception {
		if(!location.isEmpty())
			this.location = location;
		else
			throw new Exception();
	}
	public int getDistrict() {
		return district;
	}
	public void setDistrict(int district) throws Exception {
		if(district > 0)
			this.district = district;
		else
			throw new Exception();
	}
	
	
}
