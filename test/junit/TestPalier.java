package junit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import fr.humine.utils.shop.Palier;

public class TestPalier
{

	private Palier				palier;
	private ObjectOutputStream	out;
	private ObjectInputStream	in;
	private File				file;

	@Before
	public void before() throws IOException
	{

		palier = new Palier(0, "00d564", 25, 10, 120, "001521", false, true);
		
		file = new File("C:/Users/allan/Desktop/testPalier.txt");
		if (!file.exists())
			file.createNewFile();

		out = new ObjectOutputStream(new FileOutputStream(file));
		in = new ObjectInputStream(new FileInputStream(file));
	}

	@Test
	public void test() throws ClassNotFoundException, IOException
	{
		out.writeObject(palier);
		out.flush();

		Palier p2 = (Palier) in.readObject();
		assertTrue(p2.equals(palier));
	}

}
