package fr.challenge.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.challenge.main.ChallengeMain;
import fr.challenge.utils.files.ChallengeFile;

public class DailyChallengeLoadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		try
		{
			ChallengeMain.getDailyChallenge().clear();
			File f = new File(ChallengeMain.getInstance().FOLDERDAILYCHALLENGE, ChallengeMain.getInstance().getCurrentDailyChallengeDate().toString() + ".hc");
			if(!f.exists())
				return false;
			
			List<String> commands = ChallengeFile.loadCommandFile(f);
			for(String str : commands) {
				ChallengeMain.getInstance().getServer().dispatchCommand(sender, str);
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
