/**
 * 
 */
package fr.eni.easyauction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bo.ArticleVendu;

/**
 * Projet : auction_web
 * Date : 5 sept. 2022
 * Auteur : jvankerrebrouck2022
 */
public class EasyAuctionDAOJdbcImpl implements EasyAuctionDAO {
	
	private static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article FROM ARTICLES_VENDUS";
	private static final String INSERT_ARTICLE = "insert into ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie) values(?,?,?,?,?,?);";
	
	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> listesArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				//listesArticles.add(new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article")));
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
				pstmt.setInt(5, articleVendu.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(6, articleVendu.getCategorie().getNoCategorie());
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
	public void delete(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_LISTE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_LISTE_ERREUR);
			throw businessException;
		}
		
	}

	

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
	public void cocherArticle(int idArticle) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_COCHE_ARTICLE);
			pstmt.setInt(1, idArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.COCHE_ARTICLE_ERREUR);
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
