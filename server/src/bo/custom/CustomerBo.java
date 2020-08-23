package bo.custom;

import dao.SuperDao;
import dto.CustomerDTO;

import java.util.List;

public interface CustomerBo extends SuperDao {
    public boolean saveCustomer(CustomerDTO dto)throws Exception;
    public boolean updateCustomer(CustomerDTO dto)throws Exception;
    public boolean deleteCustomer(String id)throws Exception;
    public CustomerDTO geteCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomer()throws Exception;
}
