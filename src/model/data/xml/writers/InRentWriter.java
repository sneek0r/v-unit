package model.data.xml.writers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.apache.ecs.xml.XML;
import org.apache.ecs.xml.XMLDocument;

import model.InRent;
import model.data.exceptions.DataSaveException;
import model.data.exceptions.RecordNotFoundException;

/**
 * InRentWriter.java
 * @author Christopher Bertels (chbertel@uos.de)
 * @date 15.09.2008
 */
public class InRentWriter extends AbstractWriter
{

	public InRentWriter(String inRentsFile) throws DataSaveException,
			FileNotFoundException
	{
		super(inRentsFile);
	}
	
	public void saveInRents(Collection<InRent> inRentsToSave) throws IOException, RecordNotFoundException
	{
		XMLDocument document = new XMLDocument();
		XML inRentsTag = new XML("inRents");
		inRentsTag.addXMLAttribute("minID", Integer.toString(InRent.getMinID()));
		
		document.addElement(inRentsTag);
		
		for (InRent ir : inRentsToSave)
		{
			document.addElement(new XML("inRent")
				.addXMLAttribute("rID", Integer.toString(ir.getID()))
				.addXMLAttribute("customerID", Integer.toString(ir.getCustomer().getID()))
				.addXMLAttribute("videoUnitID", Integer.toString(ir.getVideoUnit().getID()))
				.addXMLAttribute("date",ir.getDate().getDate() + ":"
											+ ir.getDate().getMonth() + ":"
											+ ir.getDate().getYear())
				.addXMLAttribute("duration", Integer.toString(ir.getDuration()))
			);
		}
		
		writeToFile(document);
	}
}