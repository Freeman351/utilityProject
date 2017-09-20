package com.freeman.security.windows;

public class WindowsCredentials {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
		System.out.println(NTSystem.getName());
	}

}
