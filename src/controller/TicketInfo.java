package controller;



public class TicketInfo {
	
	private String category;
	private double new_price;
	
	public TicketInfo(){}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) throws Exception {
		if(category.compareTo("Bus") != 0 && category.compareTo("Boat") != 0 && category.compareTo("Tram") != 0 &&
				category.compareTo("Metro") != 0 && category.compareTo("Suburban railway") != 0 ){
			throw new Exception();
		}
		else{
			this.category = category;
		}
		
	}

	public double getNew_price() {
		return new_price;
	}

	public void setNew_price(double new_price) throws Exception {
		if(new_price > 0)
			this.new_price = new_price;
		else
			throw new Exception();
	}
	
	

}
