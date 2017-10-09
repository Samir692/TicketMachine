package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.Functions_ticket_machine;
import controller.Functions_tickets;
import controller.Login_access;
import controller.TicketMachineInfo;
import controller.TicketMachineSearchInfo;
import model.Boat_ticket;
import model.Bus_ticket;
import model.Metro_ticket;
import model.Suburban_railway_ticket;
import model.Ticket_machine;
import model.Tram_ticket;
import model.Ticket;
import model.TicketMachineTicketPair;
import view.View_Ticket_machine;



public class Main {
	final static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BKK");
	final static EntityManager entityManager = emfactory.createEntityManager();
	final static View_Ticket_machine view = new View_Ticket_machine();
	
	public static void main(String[] args) throws Exception {
	
		Ticket bus = null;
		Ticket boat = null;
		Ticket metro = null;
		Ticket tram = null;
		Ticket suburban_railway = null;

		Ticket_machine ti1 = null;
		Ticket_machine ti2 = null;
		Ticket_machine ti3 = null;
		Ticket_machine ti4 = null;
		
		entityManager.getTransaction().begin();
		Functions_ticket_machine tm = new Functions_ticket_machine();
		
		//enter details of user
		try{
			char[] pass = "admin".toCharArray();
			Login_access log = new Login_access();
			log.setPassword(pass);
			log.setUserName("Admin");
			log.register(entityManager);
		}
		catch(Exception e){
			System.out.println("Please fill username and password parts!!");
		}
		//enter details of ticket and types
		try{
			
			bus = new Bus_ticket(250);
			tm.setBus(bus);
			boat = new Boat_ticket(200);
			tm.setBoat(boat);
			metro = new Metro_ticket(350);
			tm.setMetro(metro);
			tram = new Tram_ticket(320);
			tm.setTram(tram);
			suburban_railway = new Suburban_railway_ticket(400);
			tm.setSuburban(suburban_railway);
			
			
			entityManager.persist(bus);
			entityManager.persist(boat);
			entityManager.persist(metro);
			entityManager.persist(tram);
			entityManager.persist(suburban_railway);
			
		}
		catch(Exception e){
			System.out.println("Please enter right amount of price");
		}
		
		//enter details for Ticket Machine
		try{
			ti1 = new Ticket_machine("Budafoki utca", 11, 0);
			ti2 = new Ticket_machine("Pazmany Peter utca", 15, 0);
			ti3 = new Ticket_machine("Bartok Bela utca", 11, 0);
			ti4 = new Ticket_machine("Etele utca", 11, 0);
			
			entityManager.persist(ti1);
			entityManager.persist(ti2);
			entityManager.persist(ti3);
			entityManager.persist(ti4);
		}
		catch(Exception e){
			System.out.println("Please fill the relevant parts of ticket machine");
		}
		//enter details of ticketMachineTicketPair table
		try{
			TicketMachineTicketPair pair1_1 = new TicketMachineTicketPair(ti1, bus, 20);
			TicketMachineTicketPair pair1_2 = new TicketMachineTicketPair(ti1, boat, 30);
			TicketMachineTicketPair pair1_3 = new TicketMachineTicketPair(ti1, metro, 40);
			TicketMachineTicketPair pair1_4 = new TicketMachineTicketPair(ti1, tram, 50);
			TicketMachineTicketPair pair1_5 = new TicketMachineTicketPair(ti1, suburban_railway, 60);
			
			TicketMachineTicketPair pair2_1 = new TicketMachineTicketPair(ti2, bus, 20);
			TicketMachineTicketPair pair2_2 = new TicketMachineTicketPair(ti2, boat, 30);
			TicketMachineTicketPair pair2_3 = new TicketMachineTicketPair(ti2, metro, 40);
			TicketMachineTicketPair pair2_4 = new TicketMachineTicketPair(ti2, tram, 50);
			TicketMachineTicketPair pair2_5 = new TicketMachineTicketPair(ti2, suburban_railway, 60);
			
			TicketMachineTicketPair pair3_1 = new TicketMachineTicketPair(ti3, bus, 20);
			TicketMachineTicketPair pair3_2 = new TicketMachineTicketPair(ti3, boat, 30);
			TicketMachineTicketPair pair3_3 = new TicketMachineTicketPair(ti3, metro, 40);
			TicketMachineTicketPair pair3_4 = new TicketMachineTicketPair(ti3, tram, 50);
			TicketMachineTicketPair pair3_5 = new TicketMachineTicketPair(ti3, suburban_railway, 60);
			
			TicketMachineTicketPair pair4_1 = new TicketMachineTicketPair(ti4, bus, 20);
			TicketMachineTicketPair pair4_2 = new TicketMachineTicketPair(ti4, boat, 30);
			TicketMachineTicketPair pair4_3 = new TicketMachineTicketPair(ti4, metro, 40);
			TicketMachineTicketPair pair4_4 = new TicketMachineTicketPair(ti4, tram, 50);
			TicketMachineTicketPair pair4_5 = new TicketMachineTicketPair(ti4, suburban_railway, 60);
			
			entityManager.persist(pair1_1);
			entityManager.persist(pair1_2);
			entityManager.persist(pair1_3);
			entityManager.persist(pair1_4);
			entityManager.persist(pair1_5);
			entityManager.persist(pair2_1);
			entityManager.persist(pair2_2);
			entityManager.persist(pair2_3);
			entityManager.persist(pair2_4);
			entityManager.persist(pair2_5);
			entityManager.persist(pair3_1);
			entityManager.persist(pair3_2);
			entityManager.persist(pair3_3);
			entityManager.persist(pair3_4);
			entityManager.persist(pair3_5);
			entityManager.persist(pair4_1);
			entityManager.persist(pair4_2);
			entityManager.persist(pair4_3);
			entityManager.persist(pair4_4);
			entityManager.persist(pair4_5);
			entityManager.getTransaction().commit();
			
		}
		catch(Exception e){
			System.out.println("Please enter right amount of tickets");
		}
		
		//run the menu
		if(login()){
			System.out.println("Welcome to BKK ADMIN application!!" + "\n");
			
			boolean ext = false;
			String choice = view.getUserChoice();
			do{
				switch(choice){
				case "a":
					listTickMach();
					if(addTickMach(tm) == null || view.getBackMenu() ){
						choice = view.getUserChoice();
					}
					
					break;
				case "l":
					listTickMach();
					if(view.getBackMenu()){
						choice = view.getUserChoice();
					}
					break;
				case "t":
					listTickets();
					if(view.getBackMenu()){
						choice = view.getUserChoice();
					}
					break;
				case "m":
					listTickMach();
					if(modifyTickMach(tm, entityManager) == null || view.getBackMenu()){
						choice = view.getUserChoice();
					}
					break;
				case "s":
					listTickMach();
					if(searchTickMach() == null || view.getBackMenu()){
						choice = view.getUserChoice();
					}
					break;
				case "g":
					getSizeTickMach();
					if(view.getBackMenu()){
						choice = view.getUserChoice();
					}
					break;
				case "c":
					if(changePriceOfTick(tm) == -1 || view.getBackMenu()){
						choice = view.getUserChoice();
					}
					break;
				case "sl":
					listTickMach();
					if(sellTickets(tm) == null || view.getBackMenu()){
						choice = view.getUserChoice();
					}
					break;
				case "e":
					boolean temp = false;
					do{
						String answer = view.getSureExit();
						if("y".equals(answer)){
							temp = true;
							ext = true;
						}
						else if("n".equals(answer)){
							System.out.println("Exit process is denied!!");
							temp = true;
							choice = view.getUserChoice();
						}
						else
							System.out.println("Please enter right operation!!");
						
					}while(!temp);
					break;
			    default:
			    	System.out.println("Please enter right operation");
			    	choice = view.getUserChoice();
				}
			}while(!ext);
			System.out.println("Application terminated!!");
		}
		else{
			System.out.println("Application terminated!!");
		}
		
		entityManager.close();
		emfactory.close();
	}
	
	private static void getSizeTickMach() {
		Functions_ticket_machine tick_mach = new Functions_ticket_machine();
		int result = tick_mach.get_size_t_m(entityManager);
		view.sizeOfTickView(result);
		
	}

	private static List<Ticket>  listTickets() {
		Functions_ticket_machine tick_mach = new Functions_ticket_machine();
		List<model.Ticket> ret_res = tick_mach.list_tickets(entityManager);
		view.list_ticket_view(ret_res);
		return ret_res;
		
	}

	//login
	private static boolean login() throws Exception{
		Login_access log = new Login_access();
		int attempts = 0;
		boolean authenitcated = false;
		
		
		  do{
			  ArrayList<String> log_info = view.getInfoLogin();
			  
			//set details
		    try{
		    	log.setUserName(log_info.get(0));
		    	char[] pass = log_info.get(1).toCharArray();
		    	log.setPassword(pass);
		    }
			catch (Exception e) {
				System.out.println("Fill username or password fields");
			}	
		    
		    //check for validation
		    if (log.validate(entityManager)) {

		        System.out.println("Authentication Complete!");
		        authenitcated = true;
		        return true;

		    } else {
		    	attempts ++;
		    	
		    	//if access is denied for 3 times terminate
		    	if(attempts == 3){
		    		System.out.println("You entered wrong username or password 3 times");
		    		System.out.println("Authentication failed :(");
		    		return false;
		    	}
		    	else
		    		System.out.println("You entered wrong Username or Password. Please try again!!");
		    }
		   }while(!authenitcated);
		  
		return false;
	}
	
	
	//Below functions helping function which is in Functions_ticket_machine and Functions_tickets class
	//view is separated in view package
	//search for ticket machine 
	private static List<Ticket_machine> searchTickMach() throws Exception {
		
		try{
			Functions_ticket_machine tick_mach = new Functions_ticket_machine();
				
			//get details of ticket machine
			TicketMachineSearchInfo tmsinfo = view.getSearchChoice();
			
			if(tmsinfo == null)
				return null;
			
			//set details
			tick_mach.setLocation(tmsinfo.getLocation());
			tick_mach.setDistrict(tmsinfo.getDistrict());
				
			//search for ticket machine
			List<Ticket_machine> ret_tick = tick_mach.search_t_m(entityManager);
			
			//show result
			view.search_ticket_machine_view(ret_tick);
			return ret_tick;
			
		}catch(Exception e){
			System.out.println("Search failed please try again!!");
		}
		return null;		
	}
	
	//increase price of ticket
	private static double changePriceOfTick(Functions_ticket_machine tm) throws Exception {
		boolean res = false;
		Ticket typeT = null;
		double new_priceT = 0;
		
		Functions_tickets ticket = new Functions_tickets();
		System.out.println("Please enter Ticket Type:");
		
		//choose ticket type
		String type = view.getTicketType();
		if(type == null)
			return -1;
		
		//find ticket id
		switch(type){
		case "Bus":
			typeT = tm.getBus();
			break;
		case "Boat":
			typeT = tm.getBoat();
			break;
		case "Tram":
			typeT = tm.getTram();
			break;
		case "Metro":
			typeT = tm.getMetro();
			break;
		case "Suburban railway":
			typeT = tm.getSuburban();
			break;
		default :
			System.out.println("Everthing is okay!!");
		}
		
		//show old price and get desired one
		
		
		//increase price of ticket
		do{
			try{
				new_priceT = view.showOldPriceGetNew(typeT);
				if(new_priceT == -1)
					return -1;
				ticket.setCategory(typeT);
				ticket.setPrice(new_priceT);
				double result = ticket.change_price_t(entityManager);
				res = true;
				
				//show result
				view.changePriceOfTickRes(ticket);
				return result;
			}
			catch(Exception e){
				//e.printStackTrace();
				System.out.println("Enter right amount!!");
			}
		}while(!res);
		
		return -1;

	}
	
	//list ticket machines
	private static List<Ticket_machine> listTickMach() {
		Functions_ticket_machine tick_mach = new Functions_ticket_machine();
		List<Ticket_machine> ret_res = tick_mach.list_t_m(entityManager);
		view.list_ticket_machine_view(ret_res);
		return ret_res;
	}
	
	//sell tickets
	private static Functions_ticket_machine sellTickets(Functions_ticket_machine tm) throws Exception {
		String type = "";
		Ticket typeT = null;
		boolean ext = false;

		Functions_ticket_machine tick_mach = new Functions_ticket_machine();
		System.out.println("Please enter details of ticket machine with full location name:");
		
		//get the ticket machine
		List<Ticket_machine> found = searchTickMach();
		if(found == null)
			return null;
		Ticket_machine id_t_m = found.get(0);
		listTickets();
		
		//find ticket type
		do{
			try{
				System.out.println("\n" + "Please enter Ticket Type:");
				type = view.getTicketType();
				if(type == null)
					return null;
						
				switch(type){
				case "Bus":
					typeT = tm.getBus();
					break;
				case "Boat":
					typeT = tm.getBoat();
					break;
				case "Tram":
					typeT = tm.getTram();
					break;
				case "Metro":
					typeT = tm.getMetro();
					break;
				case "Suburban railway":
					typeT = tm.getSuburban();
					break;
				default :
					System.out.println("Please enter right ticket type!");
				}
				ext = true;
			}
			catch(Exception e){
				System.out.println("PLease enter right Ticket Type");
			}
		}while(!ext);
		
		//sell the ticket
		try{
			tick_mach.setTicketMachine(id_t_m);
			tick_mach.setCategory(typeT);
			tick_mach.sell_t(entityManager);
			
			//show results
			view.sellTicketView(tick_mach);
		}
		catch(Exception e){
			System.out.println("Ticket could not be sold. Please try again!!");
		}
		return tick_mach;
	}

	//modify existing ticket machine
	public static Functions_ticket_machine modifyTickMach(Functions_ticket_machine tm, EntityManager em) throws Exception{
		Ticket_machine param = null;
		Ticket_machine old = null;
		boolean fin_res = false;
		int bus_num = 0;
		int boat_num = 0;
		int tram_num = 0;
		int metro_num = 0;
		int suburb_num = 0;
		
		Functions_ticket_machine tick_mach = new Functions_ticket_machine();
		ArrayList<Integer> num_ticks = new ArrayList<>();
		
		//get information about current ticket machine
		TicketMachineSearchInfo tmsinfo = view.getSearchChoice();
		if(tmsinfo == null)
			return null;
		//check for existence of current ticket machine
		try{
			tick_mach.setDistrict(tmsinfo.getDistrict());
			tick_mach.setLocation(tmsinfo.getLocation());
		}
		catch (Exception e) {
			System.out.println("\n" + "Entered ticket machine does not exist please enter the name and district correctly");
		}
		
		
		//set the details of updated ticket machine
		if((param = tick_mach.search_spec_t_m(entityManager)) != null){
			old = param;
			
			//get input from user
			TicketMachineInfo tinf = view.getInfoModdTickMach(param, em);
			if(tinf == null)
				return null;
		
			num_ticks = tinf.getNum_of_tick();
			Iterator<Integer> itr = num_ticks.iterator();
			while(itr.hasNext()){
				bus_num = (int) itr.next();
				boat_num = (int) itr.next();
				tram_num = (int) itr.next();
				metro_num = (int) itr.next();
				suburb_num = (int) itr.next();	
			}
				
				
			do{
				try{
					//set new details of ticket machine
					tick_mach.setLocation(tinf.getLocation());
					tick_mach.setDistrict(tinf.getDistrict());
					tick_mach.setTotal_income(tinf.getTotal_income());
					tick_mach.modify_t_m(param, entityManager);

					//set new details of tickets
					//enter number of tickets for Bus tickets
					Ticket bus = null;
					tick_mach.setNum_of_tick(bus_num);
					bus = tm.getBus();
					tick_mach.setCategory(bus);								
					tick_mach.persist_pairs(param,bus, entityManager);
							
					//enter number of tickets for Boat tickets
					Ticket boat = null;
					tick_mach.setNum_of_tick(boat_num);
					boat = tm.getBoat();
					tick_mach.setCategory(boat);
					tick_mach.persist_pairs(param, boat, entityManager);
								
					//enter number of tickets for Tram tickets
					Ticket tram = null;
					tick_mach.setNum_of_tick(tram_num);
					tram = tm.getTram();
					tick_mach.setCategory(tram);
					tick_mach.persist_pairs(param, tram, entityManager);
								
					//enter number of tickets for Metro tickets
					Ticket metro = null;
					tick_mach.setNum_of_tick(metro_num);
					metro = tm.getMetro();
					tick_mach.setCategory(metro);
					tick_mach.persist_pairs(param, metro, entityManager);
						
					//enter number of tickets for Suburban Railway tickets
					Ticket suburb_rail = null;
					tick_mach.setNum_of_tick(suburb_num);
					suburb_rail = tm.getSuburban();
					tick_mach.setCategory(suburb_rail);
					tick_mach.persist_pairs(param, suburb_rail, entityManager);
					fin_res = true;
				}
				catch (Exception e) {
					System.out.println("Problem occured please fill the fields correctly");
				}
			}while(!fin_res);
			
			//show the results
			view.modifyTickMachView(tick_mach);
		}
		return tick_mach;
					
	}
	
	
	//add new ticket machine
	public static Ticket_machine addTickMach(Functions_ticket_machine tm) throws Exception{
		Ticket_machine ret_pair = null;
		boolean ext = false;
		int bus_num = 0;
		int boat_num = 0;
		int tram_num = 0;
		int metro_num = 0;
		int suburb_num = 0;
		
		Functions_ticket_machine tick_mach = new Functions_ticket_machine();
		ArrayList<Integer> num_ticks = new ArrayList<>();
		
		
		do{
			//get choices from user
			TicketMachineInfo tinf = view.getInfoAddTickMach();
			if(tinf == null){
				return null;	
			}
			num_ticks = tinf.getNum_of_tick();
			Iterator<Integer> itr = num_ticks.iterator();
			while(itr.hasNext()){
				bus_num = (int) itr.next();
				boat_num = (int) itr.next();
				tram_num = (int) itr.next();
				metro_num = (int) itr.next();
				suburb_num = (int) itr.next();
			}
			
			try{
				//add new ticket machine details
				tick_mach.setDistrict(tinf.getDistrict());
				tick_mach.setLocation(tinf.getLocation());
				tick_mach.setTotal_income(tinf.getTotal_income());
				
				ret_pair = tick_mach.add_t_m(entityManager);
				//if that ticket machine exists in the table terminate
				if(ret_pair == null){
					return null;
				}
					
				//set the details of tickets	
				//Bus
				tick_mach.setCategory(tm.getBus());
				tick_mach.setNum_of_tick(bus_num);
				tick_mach.add_pairs(ret_pair, entityManager);	
				
				//Boat
				tick_mach.setCategory(tm.getBoat());
				tick_mach.setNum_of_tick(boat_num);
				tick_mach.add_pairs(ret_pair, entityManager);	
				
				//Tram
				tick_mach.setCategory(tm.getTram());
				tick_mach.setNum_of_tick(tram_num);
				tick_mach.add_pairs(ret_pair, entityManager);	
				
				//Metro
				tick_mach.setCategory(tm.getMetro());
				tick_mach.setNum_of_tick(metro_num);
				tick_mach.add_pairs(ret_pair, entityManager);	
				
				//Suburban railway
				tick_mach.setCategory(tm.getSuburban());
				tick_mach.setNum_of_tick(suburb_num);
				tick_mach.add_pairs(ret_pair, entityManager);	
				
				//show the results
				view.add_ticket_machine_view(ret_pair);
				ext = true;
				return ret_pair;
			}
			catch(Exception e){
				System.out.println("Please fill relevant parts correctly");
			}
		}while(!ext);
		
		return null;
	}
	
}
