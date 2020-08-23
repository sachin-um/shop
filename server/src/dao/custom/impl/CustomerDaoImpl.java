package dao.custom.impl;

import dao.custom.CustomerDao;
import entity.Customer;
import org.hibernate.Session;

import java.util.List;

public  class CustomerDaoImpl implements CustomerDao {

    private Session session;

    @Override
    public boolean save(Customer customer) throws Exception {
        return session.save(customer)!=null;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return false;
    }

    @Override
    public Customer get(String s) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Customer> getAll() throws Exception {
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;

    }
}
