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
	/**
	 * Getter pour rue
	 * 
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * Setter pour rue
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * Getter pour codePostal
	 * 
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * Setter pour codePostal
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * Getter pour ville
	 * 
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * Setter pour ville
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * Getter pour articleVendu
	 * 
	 * @return the articleVendu
	 */
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	/**
	 * Setter pour articleVendu
	 * @param articleVendu the articleVendu to set
	 */
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
