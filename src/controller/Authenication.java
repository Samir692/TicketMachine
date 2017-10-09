package controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.User_table;

public class Authenication {
	
	private final SecureRandom rand;
    
	private boolean constantTimeEquals(byte[] a, byte[] b) {
		if (a.length != b.length) {
			return false;
		}
		
		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result |= a[i] ^ b[i];
		}
		
		return result == 0;
	
	}
	
	public Authenication() {
		super();
		this.rand = new SecureRandom();
	}

	public boolean authenticate(String username, char[] password, EntityManager em)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		Query query = 
				em.createQuery("SELECT u FROM User_table u WHERE u.username = :username")
					.setParameter("username", username)
					.setMaxResults(1);
		User_table u = null;;
		try{
			u = (User_table) query.getSingleResult();
		}
		catch (Exception e) {

		}
		byte[] hash = hashPassword(password, u.getSalt());
		
		return constantTimeEquals(hash, u.getPassword());
	}
	
	public User_table register(String username, char[] password, EntityManager em)
			throws Exception {
		byte[] salt = new byte[256];
		rand.nextBytes(salt);;
		
		byte[] hash = hashPassword(password, salt);
		User_table user = new User_table(username, hash, salt);
		em.persist(user);
		
		return user;
	}
	
	public byte[] hashPassword(char[] password, byte[] salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 2_000_000;
        
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 256);
        SecretKeyFactory skf =
        		SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return skf.generateSecret(spec).getEncoded();
	}
}
