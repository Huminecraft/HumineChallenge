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

import fr.humine.utils.token.TokenAccount;
import fr.humine.utils.token.TokenBank;

public class TestTokenBank
{

	private TokenBank			bank;
	private ObjectOutputStream	out;
	private ObjectInputStream	in;
	private String				moneyName;

	private File				file;

	@Before
	public void before() throws IOException
	{
		moneyName = "SuperMoney";
		bank = new TokenBank(moneyName);
		bank.addAccount(new TokenAccount("Allan", 0));
		bank.addAccount(new TokenAccount("Nicolas", 15));
		bank.addAccount(new TokenAccount("Corentin", 55));

		file = new File("C:/Users/allan/Desktop/testTokenBank.txt");
		if (!file.exists())
			file.createNewFile();

		out = new ObjectOutputStream(new FileOutputStream(file));
		in = new ObjectInputStream(new FileInputStream(file));
	}

	@Test
	public void test() throws IOException, ClassNotFoundException
	{
		out.writeObject(bank);
		out.flush();

		TokenBank b2 = (TokenBank) in.readObject();
		assertTrue(b2.equals(bank));
	}

}
