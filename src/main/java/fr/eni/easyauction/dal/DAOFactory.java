package fr.eni.easyauction.dal;

public abstract class DAOFactory {
	
	public static EasyAuctionDAO getListeCourseDAO()
	{
		return new EasyAuctionDAOJdbcImpl();
	}
}
	