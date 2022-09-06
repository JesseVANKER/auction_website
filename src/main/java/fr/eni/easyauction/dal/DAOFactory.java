package fr.eni.easyauction.dal;

public abstract class DAOFactory {
	
	public static EasyAuctionDAO getEasyAuctionDAO()
	{
		return new EasyAuctionDAOJdbcImpl();
	}
}
	