package fr.humine.utils.exceptions;

public class SettingMissingException extends Exception{

	private static final long serialVersionUID = 1L;

	public SettingMissingException() {
		this("Parametre manquant dans le chargement du fichier");
	}
	
	public SettingMissingException(String message) {
		System.err.println(message);
	}
}
