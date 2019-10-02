package junit;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import fr.humine.utils.files.ChallengeFile;

public class LoadPalierFile
{

	@Test
	public void test() throws IOException
	{
		File f = new File("C:/Users/allan/Desktop/palier.hc");
		List<String> list = ChallengeFile.loadCommandFile(f);
	
		for(String str : list)
			System.out.println(str);
	}

}
