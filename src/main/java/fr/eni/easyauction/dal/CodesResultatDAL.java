package fr.eni.easyauction.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;

	
	/**
	 * Echec de la lecture des listes d'objet
	 */
	public static final int LECTURE_LISTES_ECHEC = 10002;
	/**
	 * Echec de la lecture d'une liste d'objet
	 */
	public static final int LECTURE_LISTE_ECHEC = 10003;
	/**
	 * Liste d'objet inexistante
	 */
	public static final int LECTURE_UTILISATEUR_INEXISTANTE = 10004;
	
	public static final int LECTURE_ENCHERE_INEXISTANTE = 10004;
	/**
	 * Erreur à la suppression d'un objet
	 */
	public static final int SUPPRESSION_ARTICLE_ERREUR = 10005;
	/**
	 * Erreur à la suppression d'une liste
	 */
	public static final int SUPPRESSION_UTILISATEUR_ERREUR = 10006;
	/**
	 * Erreur au cochage d'un article
	 */
	public static final int UPDATE_UTILISATEUR_ERREUR = 10007;
	/**
	 * Erreur au décochage d'un article
	 */
	public static final int UPDATE_PRIX_ARTICLE_ERREUR = 10008;
	/**
	 * Erreur au décochage de tous les articles d'une liste
	 */
	public static final int DECOCHE_ARTICLES_ERREUR = 10009;
}












