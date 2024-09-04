package Bank.src;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BankManagementSystemGUI extends JFrame {
    private CustomerDatabase customerDatabase;
    private CustomerQueue customerQueue;
    private CustomerSearch customerSearch;
    private CustomerSorter customerSorter;
    private UndoStack undoStack;
    private PriorityQueueDatabase priorityQueueDatabase;

    private JTextArea outputArea;
    private JTextField nameField, idField, accountNumberField, phoneNumberField, balanceField;
    private JComboBox<String> searchTypeComboBox;
    private JButton addButton, viewButton, searchButton, sortButton, queueButton, undoButton, loanPriorityButton, withdrawButton;

    public BankManagementSystemGUI(CustomerDatabase customerDatabase, CustomerQueue customerQueue,
                                   CustomerSearch customerSearch, CustomerSorter customerSorter, UndoStack undoStack,
                                   PriorityQueueDatabase priorityQueueDatabase) {
        this.customerDatabase = customerDatabase;
        this.customerQueue = customerQueue;
        this.customerSearch = customerSearch;
        this.customerSorter = customerSorter;
        this.undoStack = undoStack;
        this.priorityQueueDatabase = priorityQueueDatabase;

        setTitle("Bank Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initializeComponents();
        setupGUI();
    }

    private void initializeComponents() {
        outputArea = new JTextArea(20, 60);
        outputArea.setEditable(false);

        nameField = new JTextField(20);
        idField = new JTextField(10);
        accountNumberField = new JTextField(15);
        phoneNumberField = new JTextField(15);
        balanceField = new JTextField(10);

        searchTypeComboBox = new JComboBox<>(new String[]{"Name", "ID", "Account Number"});

        addButton = new JButton("Add Customer");
        viewButton = new JButton("View Customers");
        searchButton = new JButton("Search");
        sortButton = new JButton("Sort by Balance");
        queueButton = new JButton("View Queue");
        undoButton = new JButton("Undo Last Action");
        loanPriorityButton = new JButton("Loan Priority");
        withdrawButton = new JButton("Withdraw");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCustomers();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomers();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortByBalance();
            }
        });

        queueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewQueue();
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoLastAction();
            }
        });

        loanPriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoanPriority();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawFunds();
            }
        });
    }

    private void setupGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header panel with blue text
        JLabel headerLabel = new JLabel("Team_Starks");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.BLUE);
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx++;
        inputPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx++;
        inputPanel.add(idField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Account Number:"), gbc);
        gbc.gridx++;
        inputPanel.add(accountNumberField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx++;
        inputPanel.add(phoneNumberField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Balance:"), gbc);
        gbc.gridx++;
        inputPanel.add(balanceField, gbc);
        gbc.gridx++;
        inputPanel.add(addButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(searchTypeComboBox, gbc);
        gbc.gridx++;
        inputPanel.add(searchButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(viewButton, gbc);
        gbc.gridx++;
        inputPanel.add(sortButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(queueButton, gbc);
        gbc.gridx++;
        inputPanel.add(undoButton, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(loanPriorityButton, gbc);
        gbc.gridx++;
        inputPanel.add(withdrawButton, gbc);

        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addCustomer() {
        try {
            String name = nameField.getText().trim();
            int id = Integer.parseInt(idField.getText().trim());
            String accountNumber = accountNumberField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();
            double balance = Double.parseDouble(balanceField.getText().trim());

            Customer newCustomer = new Customer(name, id, accountNumber, phoneNumber, balance);
            customerDatabase.addCustomer(newCustomer);
            undoStack.addCustomer(newCustomer);
            outputArea.append("Added customer: " + newCustomer + "\n");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.");
        }
    }

    private void viewCustomers() {
        outputArea.setText("");
        LinkedList<Customer> customers = customerDatabase.getCustomers();
        for (Customer customer : customers) {
            outputArea.append(customer.toString() + "\n");
        }
    }

    private void searchCustomers() {
        String searchTerm = nameField.getText().trim();
        outputArea.setText("");
        Customer foundCustomer = null;
        switch (searchTypeComboBox.getSelectedIndex()) {
            case 0:
                foundCustomer = customerSearch.searchByName(searchTerm);
                break;
            case 1:
                try {
                    int id = Integer.parseInt(searchTerm);
                    foundCustomer = customerSearch.searchById(id);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a valid numeric ID.");
                }
                break;
            case 2:
                foundCustomer = customerSearch.searchByAccountNumber(searchTerm);
                break;
        }
        if (foundCustomer != null) {
            outputArea.append("Customer found: " + foundCustomer + "\n");
        } else {
            outputArea.append("Customer not found.\n");
        }
    }

    private void sortByBalance() {
        outputArea.setText("");
        customerSorter.sortByBalance();
    }

    private void viewQueue() {
        outputArea.setText("");
        customerQueue.viewCustomerList();
    }

    private void undoLastAction() {
        Customer undoneCustomer = undoStack.undoLastAdded();
        if (undoneCustomer != null) {
            outputArea.append("Undone last action: " + undoneCustomer + "\n");
        }
    }

    private void showLoanPriority() {
        outputArea.setText("");
        PriorityQueue<Customer> loanPriorityQueue = priorityQueueDatabase.getPriorityQueue();
        if (loanPriorityQueue.isEmpty()) {
            outputArea.append("No customers eligible for loan found.\n");
        } else {
            outputArea.append("Customers Eligible for Loan (Sorted by Balance in Descending Order):\n");
            while (!loanPriorityQueue.isEmpty()) {
                Customer customer = loanPriorityQueue.poll();
                outputArea.append(customer.toString() + "\n");
            }
        }
    }

    private void withdrawFunds() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            double amount = Double.parseDouble(balanceField.getText().trim());

            Customer customer = customerSearch.searchById(id);
            if (customer != null) {
                customer.withdraw(amount);
                undoStack.withdraw(customer, amount);
                outputArea.append("Withdrawal of " + amount + " from " + customer.getName() + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        idField.setText("");
        accountNumberField.setText("");
        phoneNumberField.setText("");
        balanceField.setText("");
    }

    public static void main(String[] args) {
        // Create instances of all necessary classes
        CustomerDatabase customerDatabase = new CustomerDatabase();
        CustomerQueue customerQueue = new CustomerQueue();
        CustomerSearch customerSearch = new CustomerSearch(customerDatabase);
        CustomerSorter customerSorter = new CustomerSorter(customerDatabase);
        UndoStack undoStack = new UndoStack();
        PriorityQueueDatabase priorityQueueDatabase = new PriorityQueueDatabase();

        // Create the GUI frame and make it visible
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankManagementSystemGUI gui = new BankManagementSystemGUI(customerDatabase, customerQueue,
                        customerSearch, customerSorter, undoStack, priorityQueueDatabase);
                gui.setVisible(true);
            }
        });
    }
}