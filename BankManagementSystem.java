package Bank.src;

import java.util.Scanner;

public class BankManagementSystem {
    public static void main(String[] args) {
        // Initialize the required classes
        Database database = new Database(100); // Example size of 100
        CustomerDatabase customerDatabase = new CustomerDatabase();
        UndoStack undoStack = new UndoStack();
        CustomerQueue customerQueue = new CustomerQueue();
        PriorityQueueDatabase priorityQueueDatabase = new PriorityQueueDatabase();
        CustomerSearch customerSearch = new CustomerSearch(customerDatabase);
        CustomerSorter customerSorter = new CustomerSorter(customerDatabase);

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nBank Management System");
            System.out.println("1. Add Customer");
            System.out.println("2. Undo Last Added Customer");
            System.out.println("3. Print Last Added Customer");
            System.out.println("4. Veiw Customer List");
            System.out.println("5. Get Next Eligible Customer for Loan");
            System.out.println("6. Search Customer");
            System.out.println("7. Sort Customers by Balance");
            System.out.println("8. Sort Customers by CustomerID");
            System.out.println("9. Print Database Size");
            System.out.println("10. Withdraw");
            System.out.println("11. Print Transaction History");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add Customer
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter intial Balance: ");
                    double balance = scanner.nextDouble();
                    Customer customer = new Customer(name, id, accountNumber, phoneNumber, balance);
                    database.addCustomer(customer);
                    customerDatabase.addCustomer(customer);
                    undoStack.addCustomer(customer);
                    customerQueue.addCustomer(customer);
                    priorityQueueDatabase.addCustomer(customer);
                    System.out.println("Customer added successfully.");
                    break;
                case 2:
                    // Undo Last Added Customer
                    Customer undoneCustomer = undoStack.undoLastAdded();
                    if (undoneCustomer != null) {
                        System.out.println("Last added customer undone: " + undoneCustomer);
                    }
                    break;
                case 3:
                    // Print Last Added Customer
                    undoStack.printLastAdded();
                    break;
                case 4:
                    // Veiw Customer List
                        customerQueue.viewCustomerList();
                    break;
                case 5:
                    // Get Next Eligible Customer for Loan
                    Customer eligibleCustomer = priorityQueueDatabase.getNextEligibleForLoan();
                    if (eligibleCustomer != null) {
                        System.out.println("Next eligible customer for loan: " + eligibleCustomer);
                    } else {
                        System.out.println("No customers eligible for loan.");
                    }
                    break;
                case 6:
                    // Search Customer
                    System.out.println("Search by: 1. Name 2. ID 3. Account Number");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Customer foundCustomer = null;
                    switch (searchChoice) {
                        case 1:
                            System.out.print("Enter Name: ");
                            String searchName = scanner.nextLine();
                            foundCustomer = customerSearch.searchByName(searchName);
                            break;
                        case 2:
                            System.out.print("Enter ID: ");
                            int searchById = scanner.nextInt();
                            foundCustomer = customerSearch.searchById(searchById);
                            break;
                        case 3:
                            System.out.print("Enter Account Number: ");
                            String searchAccountNumber = scanner.nextLine();
                            foundCustomer = customerSearch.searchByAccountNumber(searchAccountNumber);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    if (foundCustomer != null) {
                        System.out.println("Customer found: " + foundCustomer);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 7:
                    // Sort Customers by Balance
                    System.out.println("Customers sorted by balance.");
                    customerSorter.sortByBalance();
                    break;
                case 8:
                    // Sort Customers by Customer ID
                    System.out.println("Customers sorted by CustomerID:");
                    customerSorter.sortById();
                    break;
                case 9:
                    // Print Database Size
                    System.out.println("Database Size: " + database.getSize());
                    break;
                case 10:
                    // Withdraw
                    System.out.print("Enter Customer ID for Withdrawal: ");
                    int withdrawId = scanner.nextInt();
                    System.out.print("Enter Amount to Withdraw: ");
                    double amount = scanner.nextDouble();
                    Customer withdrawCustomer = customerSearch.searchById(withdrawId);
                    if (withdrawCustomer != null) {
                        undoStack.withdraw(withdrawCustomer, amount);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 11:
                    // Print Actions History
                    undoStack.printActionsHistory();
                    break;
                case 12:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

