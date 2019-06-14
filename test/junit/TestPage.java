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

import fr.humine.utils.shop.Page;
import fr.humine.utils.shop.Palier;

public class TestPage
{

	private Page				page;
	private ObjectOutputStream	out;
	private ObjectInputStream	in;
	private File				file;

	@Before
	public void before() throws IOException
	{

		page = new Page();
		
		Palier p1 = new Palier(0, "12e", 25, 10, 220, "zef", false, true);
		Palier p2 = new Palier(1, "ef", 25, 10, 130, "02145df", true, false);
		Palier p3 = new Palier(2, "21589", 25, 50, 120, "ef", false, false);
		Palier p4 = new Palier(3, "195848", 65, 10, 120, "001151521", false, true);
		
		page.addPalier(p4);
		page.addPalier(p3);
		page.addPalier(p2);
		page.addPalier(p1);
		
		file = new File("C:/Users/allan/Desktop/testPage.txt");
		if (!file.exists())
			file.createNewFile();

		out = new ObjectOutputStream(new FileOutputStream(file));
		in = new ObjectInputStream(new FileInputStream(file));
	}

	@Test
	public void test() throws ClassNotFoundException, IOException
	{
		out.writeObject(page);
		out.flush();

		Page p2 = (Page) in.readObject();
		assertTrue(p2.equals(page));
	}
}
