package model.exceptions;

import main.error.VideothekException;

public class FalseFieldException extends VideothekException
{

	public FalseFieldException(String message)
	{
		super(message);
	}

	public FalseFieldException()
	{
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
