package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Ticket_machine {
	
	@Id
    @GeneratedValue
    private int id;
	
	

	@OneToMany(mappedBy = "ticketMachine", cascade = CascadeType.ALL)
	private Set<TicketMachineTicketPair> ticketInformation;
	
	private String location;
	private int district;
	private double total_income;
	
	public Ticket_machine(){
		
	}
	
	public Ticket_machine(String location, int district,
						  double total_income) throws Exception{
		if(!location.isEmpty() && district > 0 && total_income >= 0){
			this.location = location;
			this.district = district;
			//this.num_tick_cat = num_tick_cat_ti1;
			this.total_income = total_income;
		}
			else{
				throw new Exception();
			}
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
	

	public Set<TicketMachineTicketPair> getTicketInformation() {
		return ticketInformation;
	}

	public void setTicketInformation(Set<TicketMachineTicketPair> ticketInformation) throws Exception {
		if(!ticketInformation.isEmpty())
			this.ticketInformation = ticketInformation;
		else
			throw new Exception();
	}

	public double getTotal_income() {
		return total_income;
	}

	public void setTotal_income(double total_income) throws Exception {
		if(total_income >= 0)
			this.total_income = total_income;
		else
			throw new Exception();
	}

	
	
	

	
	
}
