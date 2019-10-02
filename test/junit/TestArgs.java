package junit;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TestArgs
{

	@Test
	public void test()
	{
		String args[] = {"Bleu","ouai","","","hebdo","daily"};
		
		assertTrue(Arrays.asList(args).contains("hebdo"));
		assertFalse(Arrays.asList(args).contains("Hebdo"));
	}

}
