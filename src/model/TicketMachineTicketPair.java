package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TicketMachineTicketPair {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Ticket_machine ticketMachine;

	@ManyToOne(cascade = CascadeType.ALL)
	private Ticket ticket;

	private int remaining;
	public static final int MAXX = 500; 

	public TicketMachineTicketPair() {
	}

	public TicketMachineTicketPair(Ticket_machine ticketMachine, Ticket ticket, int remaining) throws Exception {
		this.ticketMachine = ticketMachine;
		this.ticket = ticket;
		
		if(remaining >= 0 && getRemaining() + remaining <= MAXX)	
			this.remaining = remaining;
		else
			throw new Exception();
	}

	public int getId() {
		return id;
	}

	public Ticket_machine getTicketMachine() {
		return ticketMachine;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setTicketMachine(Ticket_machine ticketMachine) {
		this.ticketMachine = ticketMachine;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void setRemaining(int remaining) throws Exception {
		if(remaining >= 0 && getRemaining() + remaining <= MAXX)
			this.remaining = remaining;
		else
			throw new Exception();
	}
	

}