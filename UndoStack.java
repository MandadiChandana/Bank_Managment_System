package Bank.src;

import java.util.Stack;

public class UndoStack {
    private Stack<Customer> stack;
    private Stack<String> transactionHistory;
    private Stack<Customer> redoStack;

    public UndoStack() {
        stack = new Stack<>();
        transactionHistory = new Stack<>();
    }

    public void addCustomer(Customer customer) {
        stack.push(customer);
        transactionHistory.push("Added: " + customer);
    }

    public Customer undoLastAdded() {
        if (!stack.isEmpty()) {
            Customer customer = stack.pop();
            transactionHistory.push("Undone: " + customer);
            return customer;
        } else {
            System.out.println("No customer to undo");
            return null;
        }
    }
    public Customer redoLastUndone() {
        if (!redoStack.isEmpty()) {
            Customer customer = redoStack.pop();
            stack.push(customer);
            transactionHistory.push("Redone: " + customer);
            return customer;
        } else {
            System.out.println("No customer to redo");
            return null;
        }
    }

    public void printLastAdded() {
        if (!stack.isEmpty()) {
            System.out.println(stack.peek());
        } else {
            System.out.println("No customer to print");
        }
    }

    public void withdraw(Customer customer, double amount) {
        if (customer.getBalance() >= amount) {
            customer.setBalance(customer.getBalance() - amount);
            transactionHistory.push("Withdrawal: " + amount + " from " + customer);
        } else {
            System.out.println("Insufficient balance for withdrawal");
        }
    }

    public void printActionsHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

