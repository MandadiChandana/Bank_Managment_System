package Bank.src;

import java.util.LinkedList;
import java.util.Queue;

public class CustomerQueue {
    private Queue<Customer> queue;

    public CustomerQueue() {
        queue = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        queue.offer(customer);
    }

    public int getPosition(Customer customer) {
        int position = 1;
        for (Customer c : queue) {
            if (c.equals(customer)) {
                return position;
            }
            position++;
        }
        return -1; // Customer not found
    }
    public void viewCustomerList() {
        System.out.println("Customer List:");
        while (!queue.isEmpty()) {
            Customer customer = queue.poll();
            System.out.println(customer);
        }
    }
}

