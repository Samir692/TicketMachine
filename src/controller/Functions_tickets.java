package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Boat_ticket;
import model.Bus_ticket;
//import model.Metro_tickets;
//import model.Suburban_railway_tickets;
import model.Ticket;
import model.TicketMachineTicketPair;
import model.Ticket_machine;


public class Functions_tickets {

	
	private int id;
	private Ticket category;
	private double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) throws Exception {
		if(id > 0)
			this.id = id;
		else
			throw new Exception();
	}
	public Ticket getCategory() {
		return category;
	}
	public void setCategory(Ticket category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) throws Exception {
		if(price > 0)
			this.price = price;
		else
			throw new Exception();
	}
	public Functions_tickets(){}
	

	
	public double change_price_t(EntityManager em) throws Exception{
		
		
		TypedQuery<TicketMachineTicketPair> t = em.createQuery("SELECT a FROM TicketMachineTicketPair a "
				+ "WHERE a.ticket = :cat"
				, TicketMachineTicketPair.class);
		t.setParameter("cat", category);
		t.setMaxResults(1);
		
		
		TicketMachineTicketPair machine = null;
		
		try{
			machine = t.getSingleResult();
	    }catch(Exception e){

	    }
		
		Ticket ticket = machine.getTicket();
		if(price <= machine.MAXX)
		{
			em.getTransaction().begin();	
			ticket.setPrice(price);
			em.getTransaction().commit();
		}
		else{
			throw new Exception();
		}
		return 0;
		
	}

}
