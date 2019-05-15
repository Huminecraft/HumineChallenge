package fr.humine.utils.exceptions;

public class SaveFileException extends Exception{

	private static final long serialVersionUID = 1L;

	public SaveFileException() {
		this("Erreur sauvegarde d'un fichier");
	}
	
	public SaveFileException(String message) {
		System.err.println(message);
	}
}
