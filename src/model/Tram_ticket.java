package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tram_ticket extends Ticket{

	
	//private String category;
	private double price;
	
	public Tram_ticket(){
		
	}
	public Tram_ticket(double price) throws Exception{
		super(price);
	}

}
