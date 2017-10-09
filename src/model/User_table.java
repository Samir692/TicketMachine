package model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User_table {


	@Id
    @GeneratedValue
    private int id;
	
	@Column(name="USERNAME",columnDefinition = "VARCHAR(250) COLLATE latin1_general_cs")
	private String username;
	
	//private char[] password;
	private byte[] password;
	
	private byte[] salt;
	

	public User_table(){
		
	}
	
	public User_table(String username, byte[] password, byte[] salt) throws Exception{
		if(!username.isEmpty()) {
			this.username = username;
			this.password = password;
			this.salt= salt;
		}
		else{
			throw new Exception();
		}
	}
	

	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	
}
