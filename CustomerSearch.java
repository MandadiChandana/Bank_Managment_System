package Bank.src;

public class CustomerSearch {
    private CustomerDatabase database;

    public CustomerSearch(CustomerDatabase database) {
        this.database = database;
    }

    public Customer searchByName(String name) {
        for (Customer customer : database.getCustomers()) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public Customer searchById(int id) {
        for (Customer customer : database.getCustomers()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public Customer searchByAccountNumber(String accountNumber) {
        for (Customer customer : database.getCustomers()) {
            if (customer.getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        return null;
    }
}

