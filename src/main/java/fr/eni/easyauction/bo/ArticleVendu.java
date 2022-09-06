/**
 * 
 */
package fr.eni.easyauction.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe en charge de 
 * Projet : auction_website3
 * Version : auction_website3 -V1.0
 * Date : 5 sept. 2022 - 16:17:22
 * @author mrousseau2022
 *
 */
public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int etatVente;
	private Utilisateur utilisateur;
	private List<Enchere> ListeEnchere = new ArrayList<Enchere>();
	private Categorie categorie;
	private Retrait[] retrait = new Retrait[1];
	
	public int getNoArticle() {
		return noArticle;
	}
	
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	
	public String getNomArticle() {
		return nomArticle;
	}
	
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public int getMiseAPrix() {
		return miseAPrix;
	}
	
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	
	public int getPrixVente() {
		return prixVente;
	}
	
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	
	public int getEtatVente() {
		return etatVente;
	}
	
	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public List<Enchere> getListeEnchere() {
		return ListeEnchere;
	}
	
	public void setListeEnchere(List<Enchere> listeEnchere) {
		ListeEnchere = listeEnchere;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public Retrait[] getRetrait() {
		return retrait;
	}
	
	public void setRetrait(Retrait[] retrait) {
		this.retrait = retrait;
	}
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", ListeEnchere=" + ListeEnchere + ", categorie=" + categorie + ", retrait="
				+ Arrays.toString(retrait) + "]";
	}
	/**
	 * Constructeur.
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param utilisateur
	 * @param listeEnchere
	 * @param categorie
	 * @param retrait
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int etatVente, Utilisateur utilisateur,
			List<Enchere> listeEnchere, Categorie categorie, Retrait[] retrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		ListeEnchere = listeEnchere;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int etatVente, Utilisateur utilisateur
			) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		
	}
	public ArticleVendu() {
		super();
		
		
	}
	public ArticleVendu(int noArticle, String nomArticle ) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		
	}
	
	public ArticleVendu( String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int etatVente, Utilisateur utilisateur
			) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		
	}
	
}
