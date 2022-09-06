/**
 * 
 */
package fr.eni.easyauction.bo;

/**
 * Classe en charge de 
 * Projet : auction_website3
 * Version : auction_website3 -V1.0
 * Date : 5 sept. 2022 - 16:28:58
 * @author mrousseau2022
 *
 */
public class Retrait {
	private String rue;
	private String codePostal;
	private String ville;
	private ArticleVendu articleVendu;
	
	
	public String getRue() {
		return rue;
	}
	
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", articleVendu="
				+ articleVendu + "]";
	}
	/**
	 * Constructeur.
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param articleVendu
	 */
	public Retrait(String rue, String codePostal, String ville, ArticleVendu articleVendu) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.articleVendu = articleVendu;
	}
	public Retrait() {
		
	}
	
}
