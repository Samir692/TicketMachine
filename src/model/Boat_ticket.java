package model;

import javax.persistence.Entity;

@Entity
public class Boat_ticket extends Ticket {

	public Boat_ticket() {
	}

	public Boat_ticket(int price) throws Exception{
		super(price);
	}
}