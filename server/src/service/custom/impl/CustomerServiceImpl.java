package service.custom.impl;

import bo.BoFactory;
import bo.custom.CustomerBo;
import dto.CustomerDTO;
import service.custom.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {

    public CustomerServiceImpl() throws RemoteException{

    }
    CustomerBo bo= BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return bo.saveCustomer(dto);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return bo.updateCustomer(dto);
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return bo.deleteCustomer(id);
    }

    @Override
    public CustomerDTO geteCustomer(String id) throws Exception {
        return bo.geteCustomer(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws Exception {
        return bo.getAllCustomer();
    }
}
