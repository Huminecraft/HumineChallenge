package fr.challenge.utils.challenges;

public class ChallengeOpenChest extends ChallengeCount {

	private static final long serialVersionUID = -1569200708983290540L;
	
	public ChallengeOpenChest(String title, String description, int amount, boolean premium) {
		super(title, description, amount, premium);
	}

	@Override
	public ChallengeType getType() {
		return ChallengeType.OPEN_CHEST;
	}

	@Override
	public boolean checkCondition(Object o) {
		return true;
	}

//	@Override
//	public Challenge clonage() {
//		ChallengeOpenChest challenge = new ChallengeOpenChest(title, description, amount, premium);
//		challenge.currentAmount = this.currentAmount;
//		challenge.award = new Award(this.award.getExp(), this.award.getToken());
//		return challenge;
//	}

}
