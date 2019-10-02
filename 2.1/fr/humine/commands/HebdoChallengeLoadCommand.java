package fr.humine.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.humine.main.ChallengeMain;
import fr.humine.utils.files.ChallengeFile;

public class HebdoChallengeLoadCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		try
		{
			ChallengeMain.getHebdoChallenge().clear();
			File f = new File(ChallengeMain.getInstance().FOLDERHEBDOCHALLENGE, ChallengeMain.getInstance().getCurrentHebdoChallengeDate().toString() + ".hc");
			if(!f.exists())
				return false;
			
			List<String> commands = ChallengeFile.loadCommandFile(f);
			for(String str : commands) {
				ChallengeMain.getInstance().getServer().dispatchCommand(sender, str + " HEBDOCHALLENGE");
			}
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

}
