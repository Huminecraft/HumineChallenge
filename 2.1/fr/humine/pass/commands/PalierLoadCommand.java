package fr.humine.pass.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PalierLoadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3)
	{
		return false;
	}

//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		
//		List<Cosmetique> cosmetiques = FileCosmetiqueManager.loadCosmetiques();
//		List<Palier> freePaliers = FileShopManager.loadFreePaliers();
//		List<Palier> premiumPalier = FileShopManager.loadPremiumPaliers();
//		
//		ChallengeMain.getInstance().getBankCosmetique().setCosmetiques(cosmetiques);
//		trie(freePaliers);
//		trie(premiumPalier);
//		
//		List<Page> pages = new ArrayList<Page>();
//		fillPages(pages, freePaliers, premiumPalier);
//		
//		for(Challenger challenger : ChallengeMain.getInstance().getBankChallenger().getChallengers()) {
//			challenger.getShop().setPages(pages);
//		}
//		
//		return true;
//	}
//
//	private void fillPages(List<Page> pages, List<Palier> listFree, List<Palier> listPremium) {
//		Iterator<Palier> ite = listFree.iterator();
//		while(ite.hasNext()) {
//			if(pages.isEmpty() || pages.get(pages.size() - 1).getFreeLine().isFull())
//				pages.add(new Page());
//			
//			pages.get(pages.size() - 1).getFreeLine().addPalier(ite.next());
//		}
//		
//		int idx = 0;
//		ite = listPremium.iterator();
//		while(ite.hasNext()) {
//			if(pages.isEmpty() || pages.get(pages.size() - 1).getPremiumLine().isFull())
//				pages.add(new Page());
//			
//			pages.get(idx).getPremiumLine().addPalier(ite.next());
//			
//			if(pages.get(idx).getPremiumLine().isFull())
//				idx++;
//		}
//		
//	}
//
//	private void trie(List<Palier> list) {
//		Collections.sort(list, new Comparator<Palier>() {
//
//			@Override
//			public int compare(Palier p1, Palier p2) {
//				return (p1.getNumeroPalier() < p2.getNumeroPalier()) ? 1 : -1;
//			}
//		});
//	}
}
