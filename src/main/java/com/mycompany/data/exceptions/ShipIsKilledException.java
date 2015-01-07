package com.mycompany.data.exceptions;

public class ShipIsKilledException extends Exception {

	public ShipIsKilledException(){}
	
	public ShipIsKilledException(String string)
	{
		super(string);
	}
	
	private static final long serialVersionUID = -1563849385457660144L;
}
