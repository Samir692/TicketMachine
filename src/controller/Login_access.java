package controller;

import model.User_table;

import javax.persistence.EntityManager;


public class Login_access {
	
	    private String userName;
	    private char[] password;
	    
	    
	    public Login_access() { }
	
	    public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public char[] getPassword() {
			return password;
		}
		public void setPassword(char[] password) {
			this.password = password;
		}
		
		public boolean validate(EntityManager em) throws Exception {
			
			Authenication ath = new Authenication();
			
			try{
				if(ath.authenticate(userName, password, em))
					return true;
			}
			catch (Exception e) {
				return false;
			}
			return false;
				
		}
		
		public User_table register(EntityManager em) throws Exception{
			Authenication ath = new Authenication();
			return ath.register(userName, password, em);
		}
		

   




}
