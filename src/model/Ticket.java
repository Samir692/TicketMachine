package model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public abstract class Ticket {
	
	@Id
    @GeneratedValue
    private int id;
	
	@OneToMany(mappedBy = "ticket")
	private Set<TicketMachineTicketPair> ticketMachineInformation;

	private double price;
	
	
	public Ticket(){
		
	}
	
	public Ticket(double price) throws Exception{
		if(price > 0)
			this.price = price;
		else
			throw new Exception();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) throws Exception {
		if(id > 0)
			this.id = id;
		else
			throw new Exception();
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
	
	public Set<TicketMachineTicketPair> getTicketMachineInformation() {
		return ticketMachineInformation;
	}

	public void setTicketMachineInformation(Set<TicketMachineTicketPair> ticketMachineInformation) throws Exception {
		if(!ticketMachineInformation.isEmpty())
			this.ticketMachineInformation = ticketMachineInformation;
		else
			throw new Exception();
	}

}
