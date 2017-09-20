package com.freeman.kerberos;

/* 
 * Copyright (C) 2009  "Darwin V. Felix" <dfelix@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

import com.freeman.kerberos.gss.client.LoginCallbackHandler;

public final class HelloKDC {

	private Subject subject;
	private byte[] serviceTicket;

	private HelloKDC() {
		// default private
	}

	public static void main(final String[] args) throws Exception {

		// Domain (pre-authentication) account
		final String username = "Q04706";

		// Password for the pre-auth acct.
		final String password = "Spring2016";

		// Name of our krb5 config file
		final String krbfile = "krb5.conf";

		// Name of our login config file
		final String loginfile = "login.conf";

		// Name of our login module
		final String module = "spnego-client";

		// set some system properties
		System.setProperty("java.security.krb5.conf", krbfile);
		System.setProperty("java.security.auth.login.config", loginfile);
		System.setProperty("sun.security.krb5.debug", "true");

		HelloKDC client = new HelloKDC();
		
		// assert 
		client.validate(username, password, krbfile, loginfile, module);

		LoginContext loginContext = client.login(module, username, password);

		
		// output some info
		System.out.println("Subject=" + client.subject);

		client.getServiceTicket("krbtgt@CCI.CANON.INFO");
		System.out.println("service ticket=" + client.serviceTicket.length);
		
		// logout
		loginContext.logout();

		System.out.println("Connection test successful.");
	}

	private void validate(final String username, final String password
			, final String krbfile, final String loginfile, final String moduleName) 
					throws FileNotFoundException, NoSuchAlgorithmException {

		// confirm username was provided
//		if (null == username || username.isEmpty()) {
//			throw new IllegalArgumentException("Must provide a username");
//		}
//
//		// confirm password was provided
//		if (null == password || password.isEmpty()) {
//			throw new IllegalArgumentException("Must provide a password");
//		}

		// confirm krb5.conf file exists
		if (null == krbfile || krbfile.isEmpty()) {
			throw new IllegalArgumentException("Must provide a krb5 file");
		} else {
			final File file = new File(krbfile);
			if (!file.exists()) {
				throw new FileNotFoundException(krbfile);
			}
		}

		// confirm loginfile
		if (null == loginfile || loginfile.isEmpty()) {
			throw new IllegalArgumentException("Must provide a login file");
		} else {
			final File file = new File(loginfile);
			if (!file.exists()) {
				throw new FileNotFoundException(loginfile);
			}
		}

		// confirm that runtime loaded the login file
		final Configuration config = Configuration.getConfiguration();

		// confirm that the module name exists in the file
		if (null == config.getAppConfigurationEntry(moduleName)) {
			throw new IllegalArgumentException("The module name " 
					+ moduleName + " was not found in the login file");
		}        
	}

	// Authenticate against the KDC using JAAS.
	private LoginContext login(String module, String username, String password) throws LoginException {
		LoginContext loginCtx = null;
	    // "Client" references the JAAS configuration in the jaas.conf file.
	    loginCtx = new LoginContext( module, new LoginCallbackHandler( username, password));
	    loginCtx.login();
	    this.subject = loginCtx.getSubject();
	    return loginCtx;
	}
	  
	private void getServiceTicket( String servicePrincipalName) throws GSSException {
		GSSManager manager = GSSManager.getInstance();
		GSSName serverName = manager.createName( servicePrincipalName, 
				GSSName.NT_HOSTBASED_SERVICE);
		final GSSContext context = manager.createContext( serverName, new Oid( "1.2.840.113554.1.2.2"), null, 
				GSSContext.DEFAULT_LIFETIME);
		// The GSS context initiation has to be performed as a privileged action.
		this.serviceTicket = Subject.doAs( subject, new PrivilegedAction<byte[]>() {
			public byte[] run() {
				try {
					byte[] token = new byte[0];
					// This is a one pass context initialisation.
					context.requestMutualAuth( false);
					context.requestCredDeleg( false);
					return context.initSecContext( token, 0, token.length);
				}
				catch ( GSSException e) {
					e.printStackTrace();
					return null;
				}
			}
		});

	}    
}
