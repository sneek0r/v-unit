package logging;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Logger.java
 * @author Christopher Bertels (chbertel@uos.de)
 * @date 11.09.2008
 */
public class Logger implements Closeable
{
	private static Logger __instance = null;
	
	private File logFile = null;
	private FileWriter fw = null;
	
	/**
	 * Konstruktor. Privat, da Singleton-Objekt.
	 * @param filename
	 * @throws IOException 
	 */
	private Logger(String filename)
	{
		logFile = new File(filename);
		try
		{
			fw = new FileWriter(logFile, true);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(String message)
	{
		if(logFile.canWrite())
		{
			StringBuilder sb = new StringBuilder();
			Calendar now = Calendar.getInstance();

			sb.append(now.get(Calendar.DAY_OF_MONTH));
			sb.append(".");
			sb.append(now.get(Calendar.MONTH));
			sb.append(".");
			sb.append(now.get(Calendar.YEAR));
			sb.append(" - ");
			sb.append(now.get(Calendar.HOUR));
			sb.append(":");
			sb.append(now.get(Calendar.MINUTE));
			sb.append(":");
			sb.append(now.get(Calendar.SECOND));
			sb.append(" :: ");
			sb.append(message);
			
			try
			{
				this.fw.append(sb.toString());
				this.fw.append("\n");
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void writeWithoutDate(String message)
	{	
		try
		{
			this.fw.append(message);
			this.fw.append("\n");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws IOException
	{
		this.fw.flush();
		this.fw.close();
	}
	
	/**
	 * Gibt Singleton-Logger Instanz zurück.
	 * @return Das globale Logger-Objekt. Kann ausgaben in Log-Datei (Videothek.log) vornehmen.
	 */
	public static Logger get()
	{
		if(__instance == null)
		{
			__instance = new Logger("Videothek.log");
		}
		
		return __instance;
	}
}