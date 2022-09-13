/**

 * 
 */
package fr.eni.easyauction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bo.ArticleVendu;
import fr.eni.easyauction.bo.Categorie;
import fr.eni.easyauction.bo.Enchere;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Projet : auction_web
 * Date : 5 sept. 2022
 * Auteur : jvankerrebrouck2022
 */
public class EasyAuctionDAOJdbcImpl implements EasyAuctionDAO {
	
	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS a LEFT JOIN UTILISATEURS u ON  a.no_utilisateur = u.no_utilisateur LEFT JOIN CATEGORIES c ON  a.no_categorie = c.no_categorie;";
	private static final String SELECT_ALL_ARTICLES_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS a LEFT JOIN UTILISATEURS u ON  a.no_utilisateur = u.no_utilisateur LEFT JOIN CATEGORIES c ON  a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article=a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article where a.no_utilisateur=?;";
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS a LEFT JOIN UTILISATEURS u ON  a.no_utilisateur = u.no_utilisateur LEFT JOIN CATEGORIES c ON  a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article=a.no_article where a.no_article=?;";
	
	private static final String SELECT_ALL_ENCHERES_USER = "SELECT * FROM ENCHERES e LEFT JOIN UTILISATEURS u ON  e.no_utilisateur = u.no_utilisateur LEFT JOIN ARTICLES_VENDUS a ON  a.no_article = e.no_article LEFT JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN RETRAITS r ON a.no_article = r.no_article where a.no_utilisateur=? ORDER BY no_enchere DESC;";
	private static final String SELECT_ALL_ENCHERES_ID_ARTICLE = "SELECT * FROM ENCHERES e LEFT JOIN UTILISATEURS u ON  e.no_utilisateur = u.no_utilisateur LEFT JOIN ARTICLES_VENDUS a ON  a.no_article = e.no_article LEFT JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN RETRAITS r ON a.no_article = r.no_article where a.no_article=? ORDER BY montant_enchere DESC;";
	private static final String INSERT_ARTICLE = "insert into ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres,prix_initial, no_utilisateur, no_categorie) values(?,?,?,?,?,?,?);";
	private static final String DELETE_ARTICLE = "delete from ARTICLES_VENDUS where no_article=?";
	private static final String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
	
	private static final String UPDATE_UTILISATEUR= "update UTILISATEURS set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? where no_utilisateur=?";
	
	private static final String DELETE_UTILISATEUR = "delete from UTILISATEURS where no_utilisateur=?";
	private static final String SELECT_ALL_USER = "SELECT * from UTILISATEURS";
	private static final String SELECT_ALL_ENCHERE ="SELECT * FROM ENCHERES";


	private static final String INSERT_ENCHERE = "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?);";
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT  libelle FROM CATEGORIES WHERE no_categorie=?";
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES;";
	private static final String SELECT_ALL_ARTICLES_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS a LEFT JOIN UTILISATEURS u ON  a.no_utilisateur = u.no_utilisateur LEFT JOIN CATEGORIES c ON  a.no_categorie = c.no_categorie WHERE a.no_categorie=?";

	 /* ----------- ARTICLES --------------*/
	@Override
	public List<ArticleVendu> selectAllArticle() throws BusinessException {
		List<ArticleVendu> listesArticles = new ArrayList<ArticleVendu>();



		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				Utilisateur utilisateur = new Utilisateur();
				Categorie categorie = new Categorie();

				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				
				
				
				listesArticles.add(new ArticleVendu(
					rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
					rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
					rs.getInt("prix_initial"), rs.getInt("prix_vente"), utilisateur,
					categorie));
				
				

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		
		return listesArticles;
	}
	
	@Override
	public void insertArticle(ArticleVendu articleVendu) throws BusinessException {
		if(articleVendu==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				
				cnx.setAutoCommit(false);

				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, articleVendu.getNomArticle());
				pstmt.setString(2, articleVendu.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
				pstmt.setInt(5,articleVendu.getMiseAPrix());
				pstmt.setInt(6, articleVendu.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(7, articleVendu.getCategorie().getNoCategorie());
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					articleVendu.setNoArticle(rs.getInt(1));
					
				}
				rs.close();
				pstmt.close();
				
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	
	@Override
	public void deleteArticle(int idArticle) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, idArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
		
	}
	
	
	 /* ----------- UTILISATEURS --------------*/
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				
				cnx.setAutoCommit(false);

				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, 0);
				pstmt.setInt(11, 0);
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					utilisateur.setNoUtilisateur(1);
					
				}
				rs.close();
				pstmt.close();
				
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}
	
	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ERREUR);
			throw businessException;
		}
		
	}
	
	public void deleteUtilisateur(int idUtilisateur) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);
			pstmt.setInt(1, idUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_UTILISATEUR_ERREUR);
			throw businessException;
		}
		
	}
	
	
	@Override
	public List<Utilisateur> selectAllUtilisateur() throws BusinessException {
		
		List<Utilisateur> listesUtilisateurs = new ArrayList<Utilisateur>();
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_USER);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listesUtilisateurs.add(new Utilisateur(
					rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), 
					rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
					rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),
					rs.getString("mot_de_passe")));

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return listesUtilisateurs;
	}

	
	
	@Override
	public List<Enchere> selectAllEnchere() throws BusinessException {
		List<Enchere> enchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERE);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				enchere.add(new Enchere(
					rs.getInt("no_enchere"),  rs.getDate("date_enchere").toLocalDate(),
					rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur")));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return enchere;
	}
	

	
	@Override
	public ArticleVendu selectArticleById(int idArticle, List<Enchere> listeEnchereArticle) throws BusinessException {
		ArticleVendu articleVendu = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			pstmt.setInt(1, idArticle);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				

			Utilisateur utilisateur = new Utilisateur();
			Categorie categorie = new Categorie();
			
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			
			articleVendu=new ArticleVendu(
				rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
				rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
				rs.getInt("prix_initial"), rs.getInt("prix_vente"), utilisateur,
				categorie, listeEnchereArticle);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}

		return articleVendu;
	}
	


	
	/* ----------- ENCHERES --------------*/

	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		if(enchere==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				
				cnx.setAutoCommit(false);

				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
				pstmt.setInt(2, enchere.getMontantEnchere());
				pstmt.setInt(3, enchere.getArticleVendu().getNoArticle());
				pstmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					enchere.setNoEnchere(1);
					
				}
				rs.close();
				pstmt.close();
				
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}


	
	@Override
	public Categorie selectCategorieById(int idCategorie) throws BusinessException {
		Categorie categorie = new Categorie();
		if(idCategorie==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_INEXISTANTE);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID);
			pstmt.setInt(1, idCategorie);
			ResultSet rs = pstmt.executeQuery();

			categorie.setLibelle(rs.getString("libelle"));
			
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}

		
		return categorie;
	}

	 @Override
	    public List<Categorie> selectAllCategories() throws BusinessException {
	        List<Categorie> listeCategorie = new ArrayList<>();
	        try(Connection cnx = ConnectionProvider.getConnection())
	        {
	            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                listeCategorie.add(new Categorie(
	                    rs.getInt("no_categorie"),  rs.getString("libelle")));
	                
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
	            throw businessException;
	        }
	        return listeCategorie;
	    }

	 
	 @Override
		public List<ArticleVendu> selectAllArticleByCategorie(int idCategorie) throws BusinessException {
			List<ArticleVendu> listesArticles = new ArrayList<ArticleVendu>();
			Utilisateur utilisateur = new Utilisateur();
			Categorie categorie = new Categorie();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_BY_CATEGORIE);
				pstmt.setInt(1, idCategorie);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{
					
					

					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setPrenom(rs.getString("prenom"));
					utilisateur.setEmail(rs.getString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					categorie.setLibelle(rs.getString("libelle"));
					
					listesArticles.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), utilisateur,
						categorie));
					

				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
				throw businessException;
			}
			
			return listesArticles;
		}
	
	 @Override
	    public List<ArticleVendu> selectAllArticleByUser(int idUtilisateur) throws BusinessException {
	        List<ArticleVendu> listesArticles = new ArrayList<ArticleVendu>();

	        try(Connection cnx = ConnectionProvider.getConnection())
	        {
	            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_UTILISATEUR);
	            pstmt.setInt(1, idUtilisateur);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	            	
	    	        Utilisateur utilisateur = new Utilisateur();
	    	        Categorie categorie = new Categorie();
	    	        
	               utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
	                utilisateur.setPseudo(rs.getString("pseudo"));
	                utilisateur.setNom(rs.getString("nom"));
	                utilisateur.setPrenom(rs.getString("prenom"));
	                utilisateur.setEmail(rs.getString("email"));
	                utilisateur.setTelephone(rs.getString("telephone"));
	                utilisateur.setRue(rs.getString("rue"));
	                utilisateur.setCodePostal(rs.getString("code_postal"));
	                utilisateur.setVille(rs.getString("ville"));
	                utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
	                
	                categorie.setNoCategorie(rs.getInt("no_categorie"));
	                categorie.setLibelle(rs.getString("libelle"));
	                
	                listesArticles.add(new ArticleVendu(
	                    rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
	                    rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
	                    rs.getInt("prix_initial"), rs.getInt("prix_vente"), utilisateur,
	                    categorie));



	          }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
	            throw businessException;
	        }
	        
	        return listesArticles;
	    }
	 
	 @Override
	    public List<Enchere> selectAllEncheresByUser(int idUtilisateur) throws BusinessException {
	        List<Enchere> listesEncheres = new ArrayList<Enchere>();

	        try(Connection cnx = ConnectionProvider.getConnection())
	        {
	            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES_USER);
	            pstmt.setInt(1, idUtilisateur);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	            	
	    	        Utilisateur utilisateur = new Utilisateur();
	    	        ArticleVendu article = null;
	    	        Categorie categorie = new Categorie();
	    	        
	    	        utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
	                utilisateur.setPseudo(rs.getString("pseudo"));
	                utilisateur.setNom(rs.getString("nom"));
	                utilisateur.setPrenom(rs.getString("prenom"));
	                utilisateur.setEmail(rs.getString("email"));
	                utilisateur.setTelephone(rs.getString("telephone"));
	                utilisateur.setRue(rs.getString("rue"));
	                utilisateur.setCodePostal(rs.getString("code_postal"));
	                utilisateur.setVille(rs.getString("ville"));
	                utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
	                
	                categorie.setNoCategorie(rs.getInt("no_categorie"));
	                categorie.setLibelle(rs.getString("libelle"));
	                
	                article=new ArticleVendu(
	                    rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
	                    rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
	                    rs.getInt("prix_initial"), rs.getInt("prix_vente"), utilisateur,
	                    categorie);
	                
	                listesEncheres.add(new Enchere(
		                    rs.getInt("no_enchere"), rs.getDate("date_enchere").toLocalDate(),
		                    rs.getInt("montant_enchere"), utilisateur ,article));   

	          }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
	            throw businessException;
	        }
	        
	        return listesEncheres;
	    }
	 
	 @Override
	    public List<Enchere> selectAllEncheresByArticle(int idArticle) throws BusinessException {
	        List<Enchere> listesEncheres = new ArrayList<Enchere>();

	        try(Connection cnx = ConnectionProvider.getConnection())
	        {
	            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES_ID_ARTICLE);
	            pstmt.setInt(1, idArticle);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	            	
	    	        Utilisateur utilisateur = new Utilisateur();
	    	        ArticleVendu article = null;
	    	        Categorie categorie = new Categorie();
	    	        
	    	        utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
	                utilisateur.setPseudo(rs.getString("pseudo"));
	                utilisateur.setNom(rs.getString("nom"));
	                utilisateur.setPrenom(rs.getString("prenom"));
	                utilisateur.setEmail(rs.getString("email"));
	                utilisateur.setTelephone(rs.getString("telephone"));
	                utilisateur.setRue(rs.getString("rue"));
	                utilisateur.setCodePostal(rs.getString("code_postal"));
	                utilisateur.setVille(rs.getString("ville"));
	                utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
	                
	                categorie.setNoCategorie(rs.getInt("no_categorie"));
	                categorie.setLibelle(rs.getString("libelle"));
	                
	                article=new ArticleVendu(
	                    rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
	                    rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
	                    rs.getInt("prix_initial"), rs.getInt("prix_vente"), utilisateur,
	                    categorie);
	                
	                listesEncheres.add(new Enchere(
		                    rs.getInt("no_enchere"), rs.getDate("date_enchere").toLocalDate(),
		                    rs.getInt("montant_enchere"), utilisateur ,article));   

	          }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            BusinessException businessException = new BusinessException();
	            businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
	            throw businessException;
	        }
	        
	        return listesEncheres;
	    }
//	@Override
//	public Enchere selectEnchere(int noEnchere) throws BusinessException {
//		
//		Enchere enchere = new Enchere();
//		if(noEnchere==0)
//		{
//			BusinessException businessException = new BusinessException();
//			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_INEXISTANTE);
//			throw businessException;
//		}
//		try(Connection cnx = ConnectionProvider.getConnection())
//		{
//			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE);
//			pstmt.setInt(1, noEnchere);
//			ResultSet rs = pstmt.executeQuery();
//
//			utilisateur.setPseudo(rs.getString("pseudo"));
//			utilisateur.setNom(rs.getString("nom"));
//			utilisateur.setPrenom(rs.getString("prenom"));
//			utilisateur.setEmail(rs.getString("email"));
//			utilisateur.setTelephone(rs.getString("telephone"));
//			utilisateur.setRue(rs.getString("rue"));
//			utilisateur.setCodePostal(rs.getString("code_postal"));
//			utilisateur.setVille(rs.getString("ville"));
//			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
//			
//
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			BusinessException businessException = new BusinessException();
//			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
//			throw businessException;
//		}
//
//		
//		return enchere;
//		
//	}
	/*
>>>>>>> branch 'master' of https://github.com/JesseVANKER/auction_website.git



	
	
//	@Override
//	public Enchere selectEnchere(int noEnchere) throws BusinessException {
//		
//		Enchere enchere = new Enchere();
//		if(noEnchere==0)
//		{
//			BusinessException businessException = new BusinessException();
//			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_INEXISTANTE);
//			throw businessException;
//		}
//		try(Connection cnx = ConnectionProvider.getConnection())
//		{
//			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE);
//			pstmt.setInt(1, noEnchere);
//			ResultSet rs = pstmt.executeQuery();
//
//			utilisateur.setPseudo(rs.getString("pseudo"));
//			utilisateur.setNom(rs.getString("nom"));
//			utilisateur.setPrenom(rs.getString("prenom"));
//			utilisateur.setEmail(rs.getString("email"));
//			utilisateur.setTelephone(rs.getString("telephone"));
//			utilisateur.setRue(rs.getString("rue"));
//			utilisateur.setCodePostal(rs.getString("code_postal"));
//			utilisateur.setVille(rs.getString("ville"));
//			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
//			
//
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			BusinessException businessException = new BusinessException();
//			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
//			throw businessException;
//		}
//
//		
//		return enchere;
//		
//	}
	/*

	private static final String SELECT_BY_ID =	"select " + 
												"	l.id as id_liste, " + 
												"	l.nom as nom_liste, " + 
												"	a.id as id_article, " + 
												"	a.nom as nom_article, " + 
												"	a.coche " +
												"from " + 
												"	listes l " + 
												"	left join articles a on l.id=a.id_liste "+
												"where l.id=?";
	private static final String INSERT_LISTE = "insert into LISTES(nom) values(?);";

	private static final String DELETE_ARTICLE = "delete from ARTICLES where id=?";
	private static final String DELETE_LISTE = "delete from LISTES where id=?";
	private static final String UPDATE_COCHE_ARTICLE="update ARTICLES set coche=1 where id=?";
	private static final String UPDATE_DECOCHE_ARTICLE="update ARTICLES set coche=0 where id=?";
	private static final String UPDATE_DECOCHE_ARTICLES="update ARTICLES set coche=0 where id_liste=?";
	




	

	@Override
	public ListeCourse selectById(int id) throws BusinessException {
		ListeCourse listeCourse = new ListeCourse();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne=true;
			while(rs.next())
			{
				if(premiereLigne)
				{
					listeCourse.setId(rs.getInt("id_liste"));
					listeCourse.setNom(rs.getString("nom_liste"));
					premiereLigne=false;
				}
				if(rs.getString("nom_article")!=null)
				{
					listeCourse.getArticles().add(new Article(rs.getInt("id_article"), rs.getString("nom_article"), rs.getBoolean("coche")));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}
		if(listeCourse.getId()==0)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_INEXISTANTE);
			throw businessException;
		}
		
		return listeCourse;
	}

	@Override
	public void deleteArticle(int idArticle) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, idArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
		
	}



	@Override
	public void decocherArticle(int idArticle) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_DECOCHE_ARTICLE);
			pstmt.setInt(1, idArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DECOCHE_ARTICLE_ERREUR);
			throw businessException;
		}
		
	}

	@Override
	public void decocherListeCourse(int idListeCourse) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_DECOCHE_ARTICLES);
			pstmt.setInt(1, idListeCourse);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DECOCHE_ARTICLES_ERREUR);
			throw businessException;
		}
		
	}
	*/

}
