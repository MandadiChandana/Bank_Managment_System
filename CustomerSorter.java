package Bank.src;

import java.util.*;

public class CustomerSorter {
    private CustomerDatabase database;

    public CustomerSorter(CustomerDatabase database) {
        this.database = database;
    }

    public void sortByBalance() {
        LinkedList<Customer> customers = database.getCustomers();
        Collections.sort(customers, Comparator.comparingDouble(Customer::getBalance));
        System.out.println("Sorted Customer List by Balance:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    // Swap method
    private void swap(List<Customer> list, int i, int j) {
        Customer temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // Partition method for quicksort
    private int partition(List<Customer> list, int low, int high) {
        int pivot = list.get(high).getId();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getId() <= pivot) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    // Quicksort method
    private void quickSort(List<Customer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    // Method to sort customers by ID using quicksort
    public void sortById() {
        List<Customer> customers = new ArrayList<>(database.getCustomers());
        quickSort(customers, 0, customers.size() - 1);

        System.out.println("Sorted Customer List by ID:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}


