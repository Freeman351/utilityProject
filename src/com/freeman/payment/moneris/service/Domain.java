/*
 * Created on 8-May-2009
 *
 */
package com.freeman.payment.moneris.service;

import java.io.Serializable;

/**
 * @author BDu
 *
 */
public abstract class Domain implements Serializable{
	private static final long serialVersionUID = 1L;
	public String value;
    public String label;
    public abstract String toString();

}
