package model.data.xml.test;

import java.util.Date;
import java.util.Map;

import main.error.VideothekException;
import model.*;
import model.data.xml.*;

/**
 * InRentParserTest.java
 * @author Christopher Bertels (chbertel@uos.de)
 * @date 12.09.2008
 */
public class InRentParserTest extends AbstractParserTest
{

	InRentParser parser = null;
	Map<Integer, InRent> parsedInRents = null;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testParseInRents()
	{
		try
		{
			parser = new InRentParser();
			parsedInRents = parser.parseInRents("xml-spec/inRents.xml");
		}
		catch (VideothekException e1)
		{
			e1.printStackTrace();
		}
		
		assertNotNull(parsedInRents);
		assertEquals(2, parsedInRents.size());
		assertEquals(3, parser.getMinID());
		
		try
		{	
			InRent first = parsedInRents.get(1);
			InRent second = parsedInRents.get(2);
			
			assertEquals(1, first.getID());
			assertEquals(new Date(2008, 9, 10), first.getDate());
			assertEquals(1, first.getVideoUnitID());
			assertEquals(2, first.getDuration());
			
			assertEquals(2, second.getID());
			assertEquals(new Date(2008, 9, 11), second.getDate());
			assertEquals(2, second.getVideoUnitID());
			assertEquals(1, second.getDuration());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}