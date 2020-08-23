package dao;

import dao.custom.impl.CustomerDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return (daoFactory==null)?(daoFactory=new DaoFactory()):(daoFactory);
    }
    public enum DaoType{
        CUSTOMER,ITEM,ORDER
    }
    public <T> T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return null;
            case ORDER:
                return null;
            default:
                return null;
        }
    }
}
