package Bank.src;

import java.util.ArrayList;
import java.util.LinkedList;

public class CustomerDatabase {
    private LinkedList<Customer> customers;

    public CustomerDatabase() {
        customers = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public LinkedList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Integer> getCustomerIDs() {
        ArrayList<Integer> customerIDs = new ArrayList<>();
        for (Customer customer : customers) {
            customerIDs.add(customer.getId());
        }
        return customerIDs;
    }
}

