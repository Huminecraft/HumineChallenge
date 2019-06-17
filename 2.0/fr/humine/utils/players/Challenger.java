package fr.humine.utils.players;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.bukkit.entity.Player;

import fr.humine.ChallengeMain;
import fr.humine.utils.challenge.date.DailyChallenge;
import fr.humine.utils.challenge.date.WeeklyChallenge;
import fr.humine.utils.levels.Level;
import fr.humine.utils.token.TokenAccount;

public class Challenger implements Serializable {

	private static final long serialVersionUID = -464273580620826820L;
	private String name;
	private boolean premium;
	private DailyChallenge dailyChallenge;
	private WeeklyChallenge weeklyChallenge;
	private ChallengerData data;
	
	public Challenger(Player player) {
		this(player.getName());
	}
	
	public Challenger(String playerName)
	{
		this.name = playerName;
		this.dailyChallenge = ChallengeMain.getInstance().getCurrentDailyChallenge();
		this.weeklyChallenge = ChallengeMain.getInstance().getCurrentWeeklyChallenge();
		this.data = new ChallengerData();
		this.premium = false;
	}
	
	public Challenger(){}
	
	public String getChallengerName() {
		return name;
	}
	
	public ChallengerData getData() {
		return data;
	}
	
	public DailyChallenge getDailyChallenge() {
		return dailyChallenge;
	}
	
	public void setDailyChallenge(DailyChallenge dailyChallenge) {
		this.dailyChallenge = dailyChallenge;
	}
	
	public WeeklyChallenge getWeeklyChallenge() {
		return weeklyChallenge;
	}
	
	public void setWeeklyChallenge(WeeklyChallenge weeklyChallenge) {
		this.weeklyChallenge = weeklyChallenge;
	}
	
	public TokenAccount getTokenAccount() {
		return ChallengeMain.getInstance().getBankToken().getAccount(this.name);
	}
	
	public Level getLevel() {
		return ChallengeMain.getInstance().getBankLevel().getLevelOf(this.name);
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	@SuppressWarnings("resource")
	public void saveWeeklyChallenge() {
		File f = new File(ChallengeMain.getInstance().getChallengerFolder(), name + File.separator + "WeeklyChallenge" + File.separator + this.weeklyChallenge.getStartDate().toString() + ".txt");
		if(!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(this.weeklyChallenge);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public boolean loadWeeklyChallenge(LocalDate date) {
		if(date.getDayOfWeek() != DayOfWeek.MONDAY)
			date = date.with(DayOfWeek.MONDAY);
		
		File f = new File(ChallengeMain.getInstance().getChallengerFolder(), name + File.separator + "WeeklyChallenge" + File.separator + date.toString() + ".txt");
		if(!f.exists())
			return false;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			this.weeklyChallenge = (WeeklyChallenge) in.readObject();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dailyChallenge == null) ? 0 : dailyChallenge.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((weeklyChallenge == null) ? 0 : weeklyChallenge.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenger other = (Challenger) obj;
		if (dailyChallenge == null) {
			if (other.dailyChallenge != null)
				return false;
		} else if (!dailyChallenge.equals(other.dailyChallenge))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (weeklyChallenge == null) {
			if (other.weeklyChallenge != null)
				return false;
		} else if (!weeklyChallenge.equals(other.weeklyChallenge))
			return false;
		return true;
	}
	
	
}
