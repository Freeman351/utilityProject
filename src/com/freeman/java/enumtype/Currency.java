package com.freeman.java.enumtype;

public enum Currency {

    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
    private int value;

	private Currency(int value) {
        this.value = value;
	}

	public String toString() {
		switch (this) {
         	case PENNY:
         		System.out.println("Penny: " + value);
         		break;
         	case NICKLE:
         		System.out.println("Nickle: " + value);
         		break;
         	case DIME:
         		System.out.println("Dime: " + value);
         		break;
         	case QUARTER:
         		System.out.println("Quarter: " + value);
       }
       return super.toString();
 	}

}
