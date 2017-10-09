package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import controller.Functions_ticket_machine;
import controller.Functions_tickets;
import controller.TicketInfo;
import controller.TicketMachineInfo;
import controller.TicketMachineSearchInfo;
import model.Ticket;
import model.Ticket_machine;

public class View_Ticket_machine {
	

	
	public View_Ticket_machine(){}
	
	//get information for login
	public ArrayList<String> getInfoLogin() {
		final String EnteredUsername;
		final String EnteredPassword;
		
		ArrayList<String> log_info = new ArrayList<>();	
		Scanner console = new Scanner(System.in);
	    
	    System.out.println("Enter Username");
	    EnteredUsername = console.nextLine();
	    log_info.add(EnteredUsername);

	    System.out.printf("%10s\n", " ");

	    System.out.println("Enter Password");
	    EnteredPassword = console.nextLine();
	    log_info.add(EnteredPassword);
	    System.out.printf("%10s\n", " ");
	    
	    return log_info;
	}
	
	//show menu and get user choice
	public String getUserChoice() {
		final String EnteredChoice;
		Scanner console = new Scanner(System.in);
		
		System.out.println("Please choose one of the options from followings");
		System.out.println(
						   "Write 'a'      : To add ticket machine"         								+   "\n"  +  
						   "Write 'l'      : To list Ticket machines"       								+   "\n"  +
						   "Write 't'      : To list Tickets and prices"    								+   "\n"  +
						   "Write 'm'      : To modify the ticket machine"  								+   "\n"  +
						   "Write 's'      : To search for ticket machine"  								+   "\n"  +
						   "Write 'g'      : To get how many ticket machines is in database" 				+   "\n"  +
						   "Write 'c'      : To change price of ticket"     								+   "\n"  +
						   "Write 'sl'     : To sell ticket"                								+   "\n"  +
						   "Write 'e'      : To exit from application"										+   "\n"  +
						   "Enter:"
							);
		EnteredChoice = console.nextLine();
		System.out.println("Chosen opearion is " + EnteredChoice + "\n");
		return EnteredChoice;
	}
	
	//show results of added ticket machine
	public void add_ticket_machine_view(Ticket_machine tick_mach){
		System.out.println("New added ticket machine:");
		System.out.printf("%-22s%-22s%-22s\n","District", "Total_income", "Location");
		System.out.printf("%-22s%-22.2f%-22s\n", tick_mach.getDistrict(), tick_mach.getTotal_income(),
				tick_mach.getLocation());
		System.out.println();
	}
	
	//show results of listed ticket machines
	public void list_ticket_machine_view(List<Ticket_machine> results){
		System.out.printf("%-22s%-22s%-22s\n", "District","Total_income", "Location");
		for(Ticket_machine tt : results){
			System.out.printf("%-22d%-22.2f%-22s\n",tt.getDistrict(),tt.getTotal_income(), tt.getLocation() );
		}
	}
	
	//show results of listed tickets and prices
	public void list_ticket_view(List<Ticket> results) {
		System.out.printf("%-50s%-50s\n", "Ticket","Price");
		for(Ticket tt : results){
			System.out.printf("%-50s%-50.2f\n", tt.getClass().toString().substring(12), tt.getPrice());
			}
		
	}
	
	//get how many ticket machines is in the table
	public void sizeOfTickView(int result) {
		System.out.println("There are " + result + " ticket machines in the database");
	}
	
	//show result of modified ticket machine
	public void modifyTickMachView(Functions_ticket_machine tick_mach) {
		System.out.println("\n" + "New modified ticket machine:");
		System.out.printf("%-22s%-22s%-22s\n","District", "Total_income", "Location");
		System.out.printf("%-22s%-22.2f%-22s\n", tick_mach.getDistrict(), tick_mach.getTotal_income(),
				tick_mach.getLocation());
	}
	
	//show results of increased price with its ticket
	public void changePriceOfTickRes(Functions_tickets ticket) {
		System.out.println("Ticket price is updated" + "\n");
		System.out.printf("%-50s%-50s\n","Ticket", "Price");
		System.out.printf("%-50s%-50.2f\n", ticket.getCategory().toString().substring(6), ticket.getPrice());	
	}
	
	//show results of sold ticket
	public void sellTicketView(Functions_ticket_machine tick_mach) {
		System.out.printf("%-34s%-34s\n","Ticket Machine", "Sold Ticket Type");
		System.out.printf("%-34s%-34s\n", tick_mach.getTicketMachine().toString().substring(6) ,tick_mach.getCategory().toString().substring(6));
		System.out.println();
	}
		
	//show results of found ticket machines
	public void search_ticket_machine_view(List<Ticket_machine> output){
		if(!output.isEmpty()){
			System.out.println("Found ticket machine:");
			
			Iterator<Ticket_machine> itr = output.iterator();
			Ticket_machine tick_mach = new Ticket_machine();
			
			System.out.printf("%-22s%-22s%-22s\n","District", "Total_income", "Location");
			while(itr.hasNext()){
				tick_mach = (Ticket_machine) itr.next();
				System.out.printf("%-22s%-22.2f%-22s\n", tick_mach.getDistrict(), tick_mach.getTotal_income(),
						tick_mach.getLocation());
			}
			System.out.println();
		}
		
		return ;
	}

	//get information for adding new ticket machine
	public TicketMachineInfo getInfoAddTickMach(){
	
		String location;
		int district,num_of_tick_bus, num_of_tick_boat, num_of_tick_tram, num_of_tick_metro, num_of_tick_suburb;
		boolean ext = false;
		boolean temp = false;
		int districtT = 1;
		
		ArrayList<Integer> 			   num_tick_arr = new ArrayList<>();
		TicketMachineInfo              tinfo        = new TicketMachineInfo();
		
		tinfo.setNum_of_tick(num_tick_arr);
		Scanner console = new Scanner(System.in);
		
		//get details of new ticket machine
		do{
			do{
				//enter location
				try{
					System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					System.out.println("Please enter location of new ticket machine:");
					location = console.nextLine();
					
					if( (location = getBackMenuALLForString(location)).isEmpty())
						return null;
					
						
					tinfo.setLocation(location);
					temp = true;
				}
				catch(Exception e){
					System.out.println("Please enter right location name:");
				}
			}while(!temp);
			temp = false;
			
			//enter district number
			do{
				try{
					System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					System.out.println("Please enter district number of new ticket machine:");
					String input = console.nextLine();
					if(getBackMenuALLForInteger(input) == -1)
						return null;
					else
						districtT = getBackMenuALLForInteger(input);
					tinfo.setDistrict(districtT);
					temp = true;
				}
				catch(InputMismatchException e){
					System.out.println("Invalid district number for new ticket machine!! Please enter number which is bigger or equal to 1" );
					district = 1;
					console.next();
				}
				catch(Exception e){
					System.out.println("Invalid district number for new ticket machine!! Please enter number which is bigger or equal to 1" );
					district = 1;
				}
			}while(!temp);
			temp = false;
			
			//enter number of tickets for bus ticket to ticket machine
			do{
				try{
					System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					System.out.println("Please enter number of tickets for Bus tickets:");
					String input  = console.nextLine();
					if(getBackMenuALLForInteger(input) == -1)
						return null;
					else
						num_of_tick_bus = getBackMenuALLForInteger(input);
					
					tinfo.setNums(num_of_tick_bus);
					tinfo.getNum_of_tick().add(num_of_tick_bus);
					temp = true;
				}
				catch(InputMismatchException e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger or equal to 0" );
					num_of_tick_bus = 0;
		            console.next();
				}
				catch(Exception e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger or equal to 0" );
					num_of_tick_bus = 0;
				}
			}while(!temp);
			temp = false;
			
			//enter number of tickets for boat ticket to ticket machine
			do{
				try{
					System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					System.out.println("Please enter number of tickets for Boat tickets:");
					String input = console.nextLine();
					if(getBackMenuALLForInteger(input) == -1)
						return null;
					else
						num_of_tick_boat = getBackMenuALLForInteger(input);
					
					
					tinfo.setNums(num_of_tick_boat);
					tinfo.getNum_of_tick().add(num_of_tick_boat);
					temp = true;
				}
				catch(InputMismatchException e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_boat = 0;
		            console.next();
				}
				catch(Exception e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_boat = 0;
				}
			}while(!temp);
			temp = false;
			
			//enter number of tickets for tram ticket to ticket machine
			do{
				try{
					System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					System.out.println("Please enter number of tickets for Tram tickets:");
					
					String input = console.nextLine();
					if(getBackMenuALLForInteger(input) == -1)
						return null;
					else
						num_of_tick_tram = getBackMenuALLForInteger(input);
					
					tinfo.setNums(num_of_tick_tram);
					tinfo.getNum_of_tick().add(num_of_tick_tram);
					temp = true;
				}
				catch(InputMismatchException e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_tram = 0;
		            console.next();
				}
				catch(Exception e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_tram = 0;
				}
			}while(!temp);
			temp = false;
			
			//enter number of tickets for metro ticket to ticket machine
			do{
				try{
					System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					System.out.println("Please enter number of tickets for Metro tickets:");
					
					String input = console.nextLine();
					if(getBackMenuALLForInteger(input) == -1)
						return null;
					else
						num_of_tick_metro = getBackMenuALLForInteger(input);
					
					tinfo.setNums(num_of_tick_metro);
					tinfo.getNum_of_tick().add(num_of_tick_metro);
					temp = true;
				}
				catch(InputMismatchException e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_metro = 0;
		            console.next();
				}
				catch(Exception e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_metro = 0;
				}
			}while(!temp);
			temp = false;
			
			//enter number of tickets for suburban railway ticket to ticket machine
			do{
				try{
					System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					System.out.println("Please enter number of tickets for Suburban Railway tickets:");
					
					String input = console.nextLine();
					if(getBackMenuALLForInteger(input) == -1)
						return null;
					else
						num_of_tick_suburb = getBackMenuALLForInteger(input);
					
					tinfo.setNums(num_of_tick_suburb);
					tinfo.getNum_of_tick().add(num_of_tick_suburb);
					temp = true;
				}
				catch(InputMismatchException e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_suburb = 0;
		            console.next();
				}
				catch(Exception e){
					System.out.println("Invalid number of tickets for new ticket machine!! Please enter number which is bigger which is bigger or equal to 0" );
					num_of_tick_suburb = 0;
				}
			}while(!temp);
			ext = true;
			
		}while(!ext);
		
		return tinfo;
	}


	//get information for searching ticket machine
	public TicketMachineSearchInfo getSearchChoice() {
		
		boolean ext = false;
		boolean temp = false;
		String location;
		int district;
		
		TicketMachineSearchInfo tminfo = new TicketMachineSearchInfo();
		Scanner console = new Scanner(System.in);
		
		//get location and district number of ticket machine
		do{
			do{
				//enter location
				try{
					System.out.println("Write 'b' OR press Enter key    : To go back to MENU");
					System.out.println("Please enter location of ticket machine:");
					location = console.nextLine();
					
					if( (location = getBackMenuALLForString(location)).isEmpty()){
						return null;
					}
					
					tminfo.setLocation(location);
					temp = true;
				}
				catch(Exception e){
					System.out.println("Please enter right location name:");
				}
			}while(!temp);
			temp = false;
			
			//enter district number
			do{
				try{
					System.out.println("Write 'b' OR press Enter key    : To go back to MENU");
					System.out.println("Please enter district number of ticket machine:");
					
					String input = console.nextLine();
					if(getBackMenuALLForInteger(input) == -1)
						return null;
					else
						district = getBackMenuALLForInteger(input);
					
					tminfo.setDistrict(district);
					temp = true;
				}
				catch(InputMismatchException e){
					System.out.println("Invalid district number of ticket machine!! Please enter number which is bigger or equal to 1" );
					district = 1;
					console.next();
				}
				catch(Exception e){
					System.out.println("Invalid district number of ticket machine!! Please enter number which is bigger or equal to 1" );
					district = 1;
				}
			}while(!temp);
			ext = true;
		}
		while(!ext);
		
		return tminfo;
	}

	//get the details of updated ticket machine
	public TicketMachineInfo getInfoModdTickMach(Ticket_machine param, EntityManager em) {
		
		boolean term = false;
		boolean fin_res = false;
		
		ArrayList<Integer> num_tick_arr  = new ArrayList<>();
		TicketMachineInfo  tinfo         = new TicketMachineInfo();

		tinfo.setNum_of_tick(num_tick_arr);
		Scanner console = new Scanner(System.in);
		
		do{
			try{
				//update values of current one
				//enter new location
				do{
					try{
						String EnteredLocation;
						System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					    System.out.println("Enter New Location");
					    EnteredLocation = console.nextLine();
					    
					    if( (EnteredLocation = getBackMenuALLForString(EnteredLocation)).isEmpty())
							return null;
					    
					    tinfo.setLocation(EnteredLocation);
						term = true;
					}
					catch(Exception e){
						System.out.println("Entered new location is not correct try again!!");
					}
				}while(!term);
				term = false;
				
				//enter new district
				do{
					int EnteredDistrict;
					try{
						System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					    System.out.println("Enter New District");
					    String input = console.nextLine();
					    
						if(getBackMenuALLForInteger(input) == -1)
							return null;
						else
							EnteredDistrict = getBackMenuALLForInteger(input);
						
					    tinfo.setDistrict(EnteredDistrict);
					    
					    //if new details are already in the table terminate
					    if(!tinfo.checkTable(em))
					    	return null;
						term = true;
					}
					catch(InputMismatchException e){
						System.out.println("Invalid district number!! Please enter number which is bigger or equal to 1 ");
			            EnteredDistrict = param.getDistrict();
			            console.next();
					}
					catch(Exception e){
						System.out.println("Invalid district number!! Please enter number which is bigger or equal to 1");
						EnteredDistrict = param.getDistrict();
					}
				}while(!term);
				term = false;	
				
				//enter new total income
				do{
					double EnteredIncome;
					try{
						System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					    System.out.println("Enter New Total Income");
					    String input = console.nextLine();
					    
					    if(getBackMenuALLForDouble(input) == -1)
							return null;
						else
							EnteredIncome = getBackMenuALLForDouble(input);
						
					    tinfo.setTotal_income(EnteredIncome);
						term = true;
					}
					catch(InputMismatchException e){
						System.out.println("Invalid total_income!! Please enter number which is bigger or equal to 0");
						EnteredIncome = param.getTotal_income();
			            console.next();
					}
					catch(Exception e){
						System.out.println("Invalid total_income!! Please enter number which is bigger or equal to 0");
						EnteredIncome = param.getTotal_income();
					}
				}while(!term);
				term = false;
							
				//enter data for TicketMachineTicketPair table
				//enter number of tickets for Bus tickets
				do{
					int EnteredBusTick;
					try{
						System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					    System.out.println("Enter Number of Tickets for Bus");
					    String input = console.nextLine();
					    
						if(getBackMenuALLForInteger(input) == -1)
							return null;
						else
							EnteredBusTick = getBackMenuALLForInteger(input);
					    tinfo.setNums(EnteredBusTick);
					    tinfo.getNum_of_tick().add(EnteredBusTick);
						term = true;
					}
					catch(InputMismatchException e){
						System.out.println("Invalid new Number of Bus Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredBusTick = 0;
			            console.next();
					}
					catch(Exception e){
						System.out.println("Invalid new Number of Bus Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredBusTick = 0;
					}
				}while(!term);
				term = false;
				
				//enter number of tickets for Boat tickets
				do{
					int EnteredBoatTick;
				   try{	
					    System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
						System.out.println("Enter Number of Tickets for Boat");
						String input = console.nextLine();
						    
						if(getBackMenuALLForInteger(input) == -1)
							return null;
						else
							EnteredBoatTick = getBackMenuALLForInteger(input);
						
					    tinfo.setNums(EnteredBoatTick);
					    tinfo.getNum_of_tick().add(EnteredBoatTick);
						term = true;
					}
					catch(InputMismatchException e){
						System.out.println("Invalid new Number of Boat Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredBoatTick = 0;
			            console.next();
					}
					catch(Exception e){
						System.out.println("Invalid new Number of Boat Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredBoatTick = 0;
					}
				}while(!term);
				term = false;
						
				//enter number of tickets for Tram tickets
				do{
					int EnteredTramTick;
					try{	
						System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
						System.out.println("Enter Number of Tickets for Tram");
						String input = console.nextLine();
					    
						if(getBackMenuALLForInteger(input) == -1)
							return null;
						else
							EnteredTramTick = getBackMenuALLForInteger(input);
						
					    tinfo.setNums(EnteredTramTick);
					    tinfo.getNum_of_tick().add(EnteredTramTick);
						term = true;
					}	
					catch(InputMismatchException e){
						System.out.println("Invalid new Number of Tram Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredTramTick = 0;
			            console.next();
			        }
					catch(Exception e){
						System.out.println("Invalid new Number of Tram Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredTramTick = 0;
			        }
				}while(!term);
				term = false;
						
				//enter number of tickets for Metro tickets
				do{
					int EnteredMetroTick;
					try{
						System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
					    System.out.println("Enter Number of Tickets for Metro Tickets");
					    String input = console.nextLine();
					    
						if(getBackMenuALLForInteger(input) == -1)
							return null;
						else
							EnteredMetroTick = getBackMenuALLForInteger(input);
						
					    tinfo.setNums(EnteredMetroTick);
					    tinfo.getNum_of_tick().add(EnteredMetroTick);
						term = true;
					}	
					catch(InputMismatchException e){
						System.out.println("Invalid new Number of Metro Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredMetroTick = 0;
			            console.next();
					}
					catch(Exception e){
						System.out.println("Invalid new Number of Metro Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredMetroTick = 0;
					}
				}while(!term);	
				term = false;
				
				//enter number of tickets for Suburban Railway tickets
				do{
					int EnteredSuburbRailTick;
					try{
						System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
						System.out.println("Enter Number of Tickets for Suburban Railway");
						String input = console.nextLine();
					    
						if(getBackMenuALLForInteger(input) == -1)
							return null;
						else
							EnteredSuburbRailTick = getBackMenuALLForInteger(input);
						
					    tinfo.setNums(EnteredSuburbRailTick);
					    tinfo.getNum_of_tick().add(EnteredSuburbRailTick);
						term = true;
					}	
					catch(InputMismatchException e){
						System.out.println("Invalid new Number of Suburban Railway Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredSuburbRailTick = 0;
			            console.next();
			        }
					catch(Exception e){
						System.out.println("Invalid new Number of Suburban Railway Tickets!! Please enter number which is bigger or equal to 0" );
						EnteredSuburbRailTick = 0;
			        }
				}while(!term);
				fin_res = true;
			}
			catch (Exception e) {
				System.out.println("New entered details are not correct please fill the fields correctly");
			}
			
		}while(!fin_res);
		
		return tinfo;
	}

	//get which ticket type user wants
	public String getTicketType() {
		boolean tmp = false;
		String EnteredCategory = "";
		
		TicketInfo tinfo = new TicketInfo();
		Scanner console = new Scanner(System.in);
		
		//get ticket type
		do{
			try{
				System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
				System.out.println("Bus" + "\n" + "Boat" + "\n" +"Tram" + "\n" + "Metro" + "\n" + "Suburban railway");
				EnteredCategory = console.nextLine();
				
				if( (EnteredCategory = getBackMenuALLForString(EnteredCategory)).isEmpty()){
					return null;
				}
				System.out.println("Chosen ticket type is " + EnteredCategory + "\n");
				
				tinfo.setCategory(EnteredCategory);
				tmp = true;
			}
			catch(Exception e){
				System.out.println("Please enter type of ticket correctly");
			}
					
		}while(!tmp);
	
		return EnteredCategory;
	}

	//show old price of chosen ticket and get new price
	public double showOldPriceGetNew(Ticket typeT){
		boolean tmp = false;
		double EnteredPrice = 0;

		TicketInfo tinfo = new TicketInfo();
		Scanner console = new Scanner(System.in);
		
		//old price and new price
		do{
			try{
				System.out.println("Write 'b' OR press Enter key     : To go back to MENU");
				System.out.println("Old price of ticket is " + typeT.getPrice());
				System.out.println("Enter new price for that ticket");
				String input = console.nextLine();
			    
				if(getBackMenuALLForDouble(input) == -1)
					return -1;
				else
					EnteredPrice = getBackMenuALLForDouble(input);
				
				tinfo.setNew_price(EnteredPrice);
				tmp = true;
			}
			catch(InputMismatchException e){
				System.out.println("Invalid price!! Please enter number which is bigger or equal to 0");
		        EnteredPrice = 0;
		        console.next();
			}
			catch(Exception e){
				System.out.println("Invalid price!! Please enter number which is bigger or equal to 0");
		        EnteredPrice = 0;
			}
			
		}while(!tmp);
		
		return EnteredPrice;
	}
	
	//back operation to back to menu
	public boolean getBackMenu(){
		boolean ext = false;
		String MenuChoice;
		
		System.out.println("Write 'b'    : To go back to MENU");	
		Scanner console = new Scanner(System.in);

		do{
			MenuChoice = console.nextLine();
			System.out.println("Chosen opearion is " + MenuChoice);
			if(MenuChoice.compareTo("b") == 0){
				ext = true;
				return ext;
			}
			else{
				System.out.println("Please write operation correctly!!");
			}
		}while(!ext);
		
		return ext;
	}
	
	public String getBackMenuALLForString(String input){
		int  num;
		try{
	       num = Integer.parseInt(input);
	    } catch (NumberFormatException e)
	    {
	    	if("b".equals(input)){
				return "";
			}
	    	else{
	    		if(input.isEmpty()) System.out.println("You pressed the Enter Key, Welcome to the Main Menu");
				return input;
	    	}
	     }
		return Integer.toString(num);
		
		
	}

public int getBackMenuALLForInteger(String input) throws Exception{
		
		int num = -1;
		try{
	        num = Integer.parseInt(input);
	        if(num == -1)
	        	throw new Exception();
	    } catch (NumberFormatException e) 
	    {
	    	if("b".equals(input)){
				return -1;
			}
	    	else{
	    		if(input.isEmpty()){
	    			System.out.println("You pressed the Enter Key, Welcome to the Main Menu");
	    			return -1;
	    		}
	    		throw new Exception();
	    	}
	     }
		return num;
		
		
	}

	public double getBackMenuALLForDouble(String input) throws Exception{
		
		double num = -1;
		try{
	        num = Double.parseDouble(input);
	        if(num == -1)
	        	throw new Exception();
	    } catch (NumberFormatException e) 
	    {
	    	if("b".equals(input)){
				return -1;
			}
	    	else{
	    		if(input.isEmpty()){
	    			System.out.println("You pressed the Enter Key, Welcome to the Main Menu");
	    			return -1;
	    		}
	    		throw new Exception();
	    	}
	     }
		return num;
		
		
}

	public String getSureExit() {
		System.out.println("Are you sure to exit application???");
		System.out.println("Choose from followings:" + "\n" + "y" + "\n" + "n" + "\n" + "Enter:");
		final String answer;
		Scanner console = new Scanner(System.in);
		answer = console.nextLine();

		return answer;
		
	}



	

}

