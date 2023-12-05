package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // Adding elements to the list
        synchronizedList.add("One");
        synchronizedList.add("Two");
        synchronizedList.add("Three");

        // Creating and starting two threads that modify the list concurrently
        Thread thread1 = new Thread(new ModifyListTask(synchronizedList, "Thread-1"));
        Thread thread2 = new Thread(new ModifyListTask(synchronizedList, "Thread-2"));

        thread1.start();
        thread2.start();
    }

    static class ModifyListTask implements Runnable {
        private List<String> synchronizedList;
        private String threadName;

        ModifyListTask(List<String> synchronizedList, String threadName) {
            this.synchronizedList = synchronizedList;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            // Synchronized block to ensure proper synchronization
            synchronized (synchronizedList) {
                Iterator<String> iterator = synchronizedList.iterator();
                while (iterator.hasNext()) {
                    String element = iterator.next();
                    System.out.println(threadName + " is iterating over: " + element);

                    // Simulating some modification
                    if (element.equals("Two")) {
                        iterator.remove();
                        System.out.println(threadName + " removed element 'Two'");
                    }
                }
            }
        }
    }
}
