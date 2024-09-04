package Bank.src;

import java.util.PriorityQueue;

public class PriorityQueueDatabase {
    private PriorityQueue<Customer> priorityQueue;

    public PriorityQueueDatabase() {
        priorityQueue = new PriorityQueue<>((c1, c2) -> Double.compare(c2.getBalance(), c1.getBalance()));
    }

    public void addCustomer(Customer customer) {
        priorityQueue.offer(customer);
    }

    public Customer getNextEligibleForLoan() {
        return priorityQueue.poll();
    }

    public PriorityQueue<Customer> getPriorityQueue() {
        return priorityQueue;
    }
}

