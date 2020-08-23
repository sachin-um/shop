package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import db.HibernateUtil;
import dto.CustomerDTO;
import entity.Customer;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBo {

    CustomerDao dao= DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            dao.setSession(session);
            session.beginTransaction();
            boolean isSaved =dao.save(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getSalary())
            );
            session.getTransaction().commit();
            return isSaved;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            dao.setSession(session);
            session.beginTransaction();
            boolean isUpdated =dao.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getSalary())
            );
            session.getTransaction().commit();
            return isUpdated;
        }
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            dao.setSession(session);
            session.beginTransaction();
            boolean isDeleted =dao.delete(id);
            session.getTransaction().commit();
            return isDeleted;
        }
    }

    @Override
    public CustomerDTO geteCustomer(String id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            dao.setSession(session);
            session.beginTransaction();
            Customer c = dao.get(id);
            session.getTransaction().commit();
            if (c != null) {
                return new CustomerDTO(c.getId(), c.getName(), c.getAddress(), c.getSalary());
            } else {
                return null;
            }
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            dao.setSession(session);
            session.beginTransaction();
            List<Customer> customerList = dao.getAll();
            session.getTransaction().commit();
            List<CustomerDTO> dtos = new ArrayList<>();

            for (Customer c : customerList) { //iter then Tab
                dtos.add(new CustomerDTO(c.getId(), c.getName(), c.getAddress(), c.getSalary()));
            }
            return dtos;

        }
    }

    @Override
    public void setSession(Session session) {

    }
}
