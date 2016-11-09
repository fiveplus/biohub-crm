package com.crm.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.crm.entity.User;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;
	
	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator){
		this.randomNumberGenerator = randomNumberGenerator;
	}
	
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}
	
	public void encryptPassword(User user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				hashIterations).toHex();
		user.setPassword(newPassword);
	}
	
	public static void main(String[] args) {
		String algorithmName = "md5";
		String username = "admin";
		String password = "Wuhan2016";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 3;
		SimpleHash hash = new SimpleHash(algorithmName, password,
				salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(encodedPassword);
		System.out.println(salt2);
	}
	
}
