package model;

import javax.persistence.Entity;


@Entity
public class Metro_ticket extends Ticket{
	
	
	public Metro_ticket(){
		
	}
	public Metro_ticket(double price) throws Exception{
		super(price);
	}

}
