package Bank.src;

public class Database {
    private Customer[] customers;
    private int count;

    public Database(int size) {
        customers = new Customer[size];
        count = 0;
    }

    public void addCustomer(Customer customer) {
        if (count < customers.length) {
            customers[count++] = customer;
        } else {
            System.out.println("Database is full");
        }
    }

    public int getSize() {
        return count;
    }
}

