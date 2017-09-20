package com.freeman.j2ee.service.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Conn4JDBC {
	
	public static void main (String[] args) throws Exception{
		Class.forName ("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@//edphp085-vip.cusa.canon.com:1778/B2BPRD.cusa.canon.com", "sqs_cci21", "sqs989cci");
                        // @//machineName:port/SID,   userid,  password
			//jdbc:oracle:thin:@//10.64.10.54:1778/B2BPRD.cusa.canon.com
		try {
			Statement stmt = conn.createStatement();
			try {
				ResultSet rset = stmt.executeQuery("select BANNER from SYS.V_$VERSION");
				try {
					while (rset.next()){
						System.out.println (rset.getString(1));   // Print col 1
					}
				}finally {
					try { rset.close(); } catch (Exception ignore) {}
				}
			}finally {
				try { stmt.close(); } catch (Exception ignore) {}
			}
		}finally {
			try { conn.close(); } catch (Exception ignore) {}
		}
	}
}
