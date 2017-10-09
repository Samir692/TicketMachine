package controller;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Ticket;
import model.TicketMachineTicketPair;
import model.Ticket_machine;

public class TicketMachineInfo {

	public TicketMachineInfo(){}
	
	String location;
	int district;
	Ticket category_t;
	double total_income;
	private int nums;

	ArrayList<Integer> num_of_tick;
	
	
	
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) throws Exception {
		if(nums >= 0 && nums <= TicketMachineTicketPair.MAXX)
			this.nums = nums;
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

	public ArrayList<Integer> getNum_of_tick() {
		return num_of_tick;
	}
	public void setNum_of_tick(ArrayList<Integer> num_of_tick) {
		this.num_of_tick = num_of_tick;
	}
	public Ticket getCategory_t() {
		return category_t;
	}
	public void setCategory_t(Ticket category_t) {
		this.category_t = category_t;
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
	public boolean checkTable(EntityManager em) {
		
		
		TypedQuery<Ticket_machine> t = em.createQuery("SELECT a FROM Ticket_machine a "
				+ "WHERE a.location = :loc AND a.district = :distr"
				, Ticket_machine.class);
		t.setParameter("loc", location);
		t.setParameter("distr", district);
		t.setMaxResults(1);
		
		
		try{
			Ticket_machine tick_mach = t.getSingleResult();;
			System.out.println("That ticket machine exists in the table!!");
			return false;
            
		}catch(Exception e){
			return true;
        }
		
	}
	
	
}
