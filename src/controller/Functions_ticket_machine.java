package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//import model.Metro_tickets;
//import model.Suburban_railway_tickets;
import model.Ticket_machine;
import model.Ticket;
import model.TicketMachineTicketPair;

public class Functions_ticket_machine {


    private int id;
	private String location;
	private int district;
	private double total_income;
	private int num_of_tick;
	private Ticket category;
	private Ticket_machine ticketMachine;
	private Ticket bus;
	private Ticket boat;
	private Ticket tram;
	private Ticket metro;
	private Ticket suburban;
	
	
	
	
	public Functions_ticket_machine(){}
	
	public Ticket getTram() {
		return tram;
	}


	public void setTram(Ticket tram) {
		this.tram = tram;
	}


	public Ticket getBus() {
		return bus;
	}


	public void setBus(Ticket bus) {
		this.bus = bus;
	}


	public Ticket getBoat() {
		return boat;
	}


	public void setBoat(Ticket boat) {
		this.boat = boat;
	}


	public Ticket getMetro() {
		return metro;
	}


	public void setMetro(Ticket metro) {
		this.metro = metro;
	}


	public Ticket getSuburban() {
		return suburban;
	}


	public void setSuburban(Ticket suburban) {
		this.suburban = suburban;
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
		if(district > 0){
			this.district = district;
		}
		else{
			throw new Exception();
		}
	}
	
	public int getNum_of_tick() {
		return num_of_tick;
	}
	public void setNum_of_tick(int num_of_tick) throws Exception {
		if(num_of_tick >= 0)
			this.num_of_tick = num_of_tick;
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
	public int getId() {
		return id;
	}
	public void setId(int id) throws Exception {
		if(id > 0)
			this.id = id;
		else
			throw new Exception();
	}
	
	
	public Ticket_machine getTicketMachine() {
		return ticketMachine;
	}
	
	public void setTicketMachine(Ticket_machine ticketMachine) {
		this.ticketMachine = ticketMachine;
	}

	public Ticket getCategory() {
		return category;
	}
	public void setCategory(Ticket category) {
		this.category = category;
	}
	//add ticket machine 
	public Ticket_machine add_t_m(EntityManager em) throws Exception{
		
		Ticket_machine tick_mach = new Ticket_machine();
		
		TypedQuery<Ticket_machine> t = em.createQuery("SELECT a FROM Ticket_machine a "
				+ "WHERE a.location = :loc AND a.district = :distr"
				, Ticket_machine.class);
		t.setParameter("loc", location);
		t.setParameter("distr", district);
		t.setMaxResults(1);
		
		
		try{
            tick_mach = t.getSingleResult();
            System.out.println("That ticket machine is in the table");
            return null;
            
		}catch(Exception e){
		
			//fill TicketMachineTicketPair table
		  	
			tick_mach.setDistrict(district);
    		tick_mach.setLocation(location);
    		tick_mach.setTotal_income(total_income);
    		em.getTransaction().begin();
    		em.persist(tick_mach);
    		em.getTransaction().commit();

        }
	
	    return tick_mach;
		
	}

	public void add_pairs(Ticket_machine param, EntityManager em) throws Exception {
		Ticket tt = getCategory();
		TicketMachineTicketPair tick_mach_pair = new TicketMachineTicketPair();
		TypedQuery<TicketMachineTicketPair> t = em.createQuery("SELECT a FROM TicketMachineTicketPair a "
				+ "WHERE a.ticketMachine = :tm AND a.ticket = :tt"
				, TicketMachineTicketPair.class);
		t.setParameter("tm", param);
		t.setParameter("tt", tt);
		t.setMaxResults(1);

		try{
	        tick_mach_pair = t.getSingleResult();
	        
	    }catch(Exception e){
	    	tick_mach_pair.setTicket(category);
			tick_mach_pair.setTicketMachine(param);
			tick_mach_pair.setRemaining(num_of_tick);
			
			TicketMachineTicketPair pair = new TicketMachineTicketPair(param, category, num_of_tick);
			
			em.getTransaction().begin();
			param.getTicketInformation().add(tick_mach_pair);
			em.getTransaction().commit();
	    	
	    }		
	}
	
// search for ticket machine
public List<Ticket_machine> search_t_m(EntityManager em){
	
	Ticket_machine tick_mach = new Ticket_machine();
	TypedQuery<Ticket_machine> t = em.createQuery("SELECT a FROM Ticket_machine a "
			+ "WHERE a.location LIKE CONCAT('%',?1,'%')"
			, Ticket_machine.class);
	t.setParameter("1", location);
	
	/*
	List<Ticket_machine> results = t.getResultList();
    if (results.isEmpty()) return null;
    else if (results.size() == 1) return results.get(0);
    throw new NonUniqueResultException();
*/	
	try{
		List<Ticket_machine> results = t.getResultList();
		List<Ticket_machine> output = new ArrayList<>();
		Iterator itr = results.iterator();
		if(results.isEmpty()){
			throw new Exception();
		}
		else{
			while(itr.hasNext()){
				tick_mach = (Ticket_machine) itr.next();
		        if(tick_mach.getDistrict()  == district){
		        	output.add(tick_mach);
		        }
			}
			if(!output.isEmpty())
				return output;
			else
				throw new Exception();
		}
    }catch(Exception e){
    	System.out.println("Search failed!! Entered ticket machine is not in the list!!");
    }
	
	return null;
}
//list ticket machines
public List<Ticket_machine> list_t_m(EntityManager em){

	TypedQuery<Ticket_machine> t = em.createQuery("SELECT a FROM Ticket_machine a ", Ticket_machine.class);
	
	try{
		List<Ticket_machine> results =  t.getResultList();
		if (results.isEmpty()){
		    System.out.println("There is no ticket machine in the list!!");
		}
        return results;
        
    }catch(Exception e){
    	System.out.println("No Ticket Machine found!!");
    }
	
	return null;
	
}

//get size of Ticket machines
public int get_size_t_m(EntityManager em){

	TypedQuery<Ticket_machine> t = em.createQuery("SELECT a FROM Ticket_machine a ", Ticket_machine.class);
	
	try{
		List<Ticket_machine> results =  t.getResultList();
		if (results.isEmpty())
		    System.out.println("There is no ticket machine in the list!!");
		else
			return results.size();
      
	}catch(Exception e){
		//System.out.println("No Ticket Machine found!!");
	}
	
	return 0;
	
}

//search for ticket machine
public Ticket_machine search_spec_t_m(EntityManager em){
	
	Ticket_machine tick_mach = new Ticket_machine();
	TypedQuery<Ticket_machine> t = em.createQuery("SELECT a FROM Ticket_machine a "
			+ "WHERE a.location = :loc AND a.district = :dist"
			, Ticket_machine.class);
	t.setParameter("loc", location);
	t.setParameter("dist", district);
	t.setMaxResults(1);
	

	try{
		tick_mach = t.getSingleResult();
		return tick_mach;
		
	 }catch(Exception e){
	 	System.out.println("Search failed!! Ticket machine you entered is not in the list!!");
	 }
	
	return null;
}

// modify existing ticket machine
public Ticket_machine modify_t_m(Ticket_machine param, EntityManager em) throws Exception {

	Ticket_machine tick_mach = param;

	TypedQuery<Ticket_machine> t = em.createQuery("SELECT a FROM Ticket_machine a "
			+ "WHERE a.location = :loc AND a.district = :distr"
			, Ticket_machine.class);
	t.setParameter("loc", tick_mach.getLocation());
	t.setParameter("distr", tick_mach.getDistrict());
	t.setMaxResults(1);
	try{
        tick_mach = t.getSingleResult();
        
    }catch(Exception e){
    	System.out.println("Entered ticket machine does not exist");
    }
	

	
	//enter new data	
	tick_mach.setDistrict(district);
	tick_mach.setLocation(location);
	tick_mach.setTotal_income(total_income);
	em.getTransaction().begin();
	em.persist(tick_mach);
	em.getTransaction().commit();
	
    
    return tick_mach;
	
	
}

//modify TicketMachineTicketPair table 
public void persist_pairs(Ticket_machine param,Ticket tt, EntityManager em) throws Exception {

	TicketMachineTicketPair tick_mach_pair = null;
	TypedQuery<TicketMachineTicketPair> t = em.createQuery("SELECT a FROM TicketMachineTicketPair a "
			+ "WHERE a.ticketMachine = :tm AND a.ticket = :tt"
			, TicketMachineTicketPair.class);
	t.setParameter("tm", param);
	t.setParameter("tt", tt);
	t.setMaxResults(1);

	try{
        tick_mach_pair = t.getSingleResult();
        
    }catch(Exception e){
    	System.out.println("Problem occured in ticket machine ticket pairs table");
    }
	
	tick_mach_pair.setTicket(category);
	tick_mach_pair.setTicketMachine(param);
	tick_mach_pair.setRemaining(num_of_tick);
	
	em.getTransaction().begin();
	param.getTicketInformation().add(tick_mach_pair);
	em.getTransaction().commit();
	
	
}
//sell ticket
public Ticket sell_t(EntityManager em) throws Exception{
	

	
	//find TicketMachineTicketPair pair
	TicketMachineTicketPair pair = null;
	TypedQuery<TicketMachineTicketPair> machine = em.createQuery("SELECT a FROM TicketMachineTicketPair a "
			+ "WHERE a.ticketMachine = :tm AND a.ticket = :t"
			, TicketMachineTicketPair.class);
	machine.setParameter("tm", ticketMachine);
	machine.setParameter("t", category);
	machine.setMaxResults(1);
	
	try{
        pair = machine.getSingleResult();
    }catch(Exception e){
    	System.out.println("Ticket pair could not be found");
    }
	
	
	try{
		//decrease number of tickets
		pair.setRemaining(pair.getRemaining() - 1);
		ticketMachine.setTotal_income(ticketMachine.getTotal_income() + category.getPrice());

	}
	catch(Exception e){
		System.out.println("There is no ticket anymore");
	}
    return pair.getTicket();
	
	
}


public List<Ticket> list_tickets(EntityManager em) {
	TypedQuery<Ticket> t = em.createQuery("SELECT a FROM Ticket a ", Ticket.class);
	
	try{
		List<Ticket> results =  t.getResultList();
		if (results.isEmpty()){
		    System.out.println("There is no ticket in the list!!");
		}
        return results;
        
    }catch(Exception e){
    	System.out.println("No Ticket found!!");
    }
	return null;
	
}





}
