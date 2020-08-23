package proxy;

import service.ServiceFactory;
import service.custom.CustomerService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProxyHandler implements ServiceFactory {
    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private CustomerService customerService;


    private ProxyHandler() {
        try {

            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/cmjd83");
            customerService = serviceFactory.getService(ServiceType.CUSTOMER);


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProxyHandler getInstance() {
        return (proxyHandler == null) ? (proxyHandler = new ProxyHandler()) : (proxyHandler);
    }


    @Override
    public <T> T getService(ServiceType type) throws Exception {
        switch (type) {
            case CUSTOMER:
                return (T) customerService;
            case ORDER:
                return null;
            case ITEM:
                return null;
            default:
                return null;
        }
    }
}