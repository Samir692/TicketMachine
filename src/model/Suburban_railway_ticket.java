package model;

import javax.persistence.Entity;


@Entity
public class Suburban_railway_ticket extends Ticket{
	

	public Suburban_railway_ticket(){
		
	}
	
	public Suburban_railway_ticket(double price) throws Exception{
		super(price);
	}

}
