package com.freeman.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author BDu
 * 
 */
public class BaseException extends Exception {

    protected Throwable cause;
    
    protected String forwardName;

    protected String messageKey;

    /**
     * The list of properties pertaining to this exception, will be be
     * used by Struts to generate the error message.
     */
    protected Object[] properties = new Object[0];

    /**
     * Creates a new <code>BaseException</code>.
     */
    public BaseException() {
        super();
    }


    /**
     * Creates a new <code>BaseException</code> with the specified
     * detail.
     * 
     * @param message  the detail about this exception.
     */
    public BaseException(String message) {
        super(message);
        properties = new Object[1];
        this.properties[0] = message;
    }


    public BaseException(Throwable cause) {
        this(cause, null);
    }


    /**
     * Creates a new <code>BaseException</code> with the specified
     * cause and detail message.
     * 
     * @param cause    the Throwable object that generates this exception
     * @param message  the detail about this exception.
     */
    public BaseException(Throwable cause, String message) {
        super(message);

        this.cause = cause;
    }

    public BaseException(String msgKey, Object[] propoerties) {
        messageKey = msgKey;
        this.properties = propoerties;
    }

    public BaseException(String msgKey, String message) {
        messageKey = msgKey;
        properties = new Object[1];
        this.properties[0] = message;
    }

    /**
     * Returns the Throwable object that causes this exception.
     */
    public Throwable getCause() {
        return cause;
    } 


    public String getMessage() {
        String message = super.getMessage();

        if (message != null && message.length() > 0)
            return message;
        else if (cause != null)
            return cause.getMessage();
        else
            return "";
    } 


    public Object[] getProperties() {
        return properties;
    }
    
    public Object getProperty(int i){
    	return properties[i];
    }

    public String getMessageKey() {
        return messageKey;
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        Object[] properties = getProperties();


        if (properties != null && properties.length > 0) {
            sb.append("\nproperties [");

            for (int i = 0; i < properties.length; i++) {
                if (i > 0)
                    sb.append(", ");

                sb.append(properties[i]);
            } 

            sb.append("]");
        } 

        if (cause != null) {
            sb.append("\nCaused by:\n");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            cause.printStackTrace(pw);
            sb.append(sw.toString());
        } 

        return sb.toString();
    } 


    public String getForwardName() {
        return forwardName;
    }

}
