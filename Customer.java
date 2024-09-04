package Bank.src;

public class Customer {
    private String name;
    private int id;
    private String accountNumber;
    private String phoneNumber;
    private double balance;

    // Constructor, getters, and setters

    public Customer(String name, int id, String accountNumber, String phoneNumber, double balance) {
        this.name = name;
        this.id = id;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // toString method for printing
    @Override
    public String toString() {
        return "------------------------------------------\n" +
                "          CUSTOMER DETAILS SLIP           \n" +
                "------------------------------------------\n" +
                "Name           : " + name + "\n" +
                "Customer ID    : " + id + "\n" +
                "Account Number : " + accountNumber + "\n" +
                "Phone Number   : " + phoneNumber + "\n" +
                "Balance        : " + String.format("%.2f", balance) + "\n" +
                "##########################################\n";
    }

    public void withdraw(double amount) {

    }
}


