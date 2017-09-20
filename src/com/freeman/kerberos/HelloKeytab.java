package com.freeman.kerberos;

import java.net.URL;

import net.sourceforge.spnego.SpnegoHttpURLConnection;

public class HelloKeytab {

    public static void main(final String[] args) throws Exception {
        System.setProperty("java.security.krb5.conf", "krb5.conf");
        System.setProperty("sun.security.krb5.debug", "true");
        System.setProperty("java.security.auth.login.config", "login.conf");
          
        SpnegoHttpURLConnection spnego = null;
        
        try {
            spnego = new SpnegoHttpURLConnection("custom-client");
            spnego.connect(new URL("http://m005557mis:8080/hello.jsp"));
            
            System.out.println("HTTP Status Code: " 
                    + spnego.getResponseCode());
            
            System.out.println("HTTP Status Message: "
                    + spnego.getResponseMessage());

        } finally {
            if (null != spnego) {
                spnego.disconnect();
            }
        }
    }
}
