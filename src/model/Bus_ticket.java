package model;

import javax.persistence.Entity;

@Entity
public class Bus_ticket extends Ticket {

	public Bus_ticket() {
	}

	public Bus_ticket(int price) throws Exception{
		super(price);
	}
}