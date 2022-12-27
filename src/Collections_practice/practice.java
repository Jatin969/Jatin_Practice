package Collections_practice;

import java.util.*;

/**    Collections interface is an implementation of iterable interface.
                                     -   ArrayList == implemented over arrays insert - 0(1), delete - (N), delete at end - O(1), get - O(1)
                                List -  linkedlist == implemented using nodes and pointers (Default DDL)
                                     -   vector - stack

    Iterable - collections -
                                Set - HashSet ==  Hashing and List based collision handling
                                    - linkedHashSet == Hashing and linkedList based configuration , maintains order.
                                    - SortedSet - TreeSet == stores data in sorted order



                                QUeue - PriorityQueue<(lambda operation)>
                                        - Deque - ArrayDeque





                Map - sortedMap     - HashTree (implemented using red-black tree)

                        hash -          - HashMap
                                        - LinkedHashMap



 */





class NEWOBJECT implements Comparable<NEWOBJECT>{
    public String name;
    public List<String> list_of_task_it_perform;
    public int priority;

    public NEWOBJECT(String name, List<String> list_of_task_it_perform, int priority) {
        this.name = name;
        this.list_of_task_it_perform = list_of_task_it_perform;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "NEWOBJECT{" +
                "name='" + name + '\'' +
                ", list_of_task_it_perform=" + list_of_task_it_perform +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(NEWOBJECT o) {
        return this.priority - o.priority;
    }


}

class NEWObjectComparator implements Comparator<NEWOBJECT>{

    public static int compareThem(NEWOBJECT o1, NEWOBJECT o2) {
        return o1.name.compareTo(o2.name);
    }
    @Override
    public int compare(NEWOBJECT o1, NEWOBJECT o2){
        return 0;
    }

    public int compareByTaskSize(NEWOBJECT a, NEWOBJECT b){
        return a.list_of_task_it_perform.size() - b.list_of_task_it_perform.size();
    }

}
public class practice {
    List<Integer> list = new ArrayList<>();
    List<String> forString = new LinkedList<>();

    Set<Long> longSet = new HashSet<>();
    Set<Long> treeSet = new TreeSet<>();
    Set<Long>  LinkedSEt = new LinkedHashSet<>();

    Stack<Integer> stack = new Stack<>();
    Queue<Object> objQueue = new ArrayDeque<>();
    static Queue<NEWOBJECT> usingPriorityQueue = new PriorityQueue<>((a,b) -> {return NEWObjectComparator.compareThem(a,b);});

    public static void main(String[] args) {
        usingPriorityQueue.add(new NEWOBJECT("jatin", new ArrayList<String>(), 10));
        usingPriorityQueue.add(new NEWOBJECT("utsav", new ArrayList<String>(), 9));
        usingPriorityQueue.add(new NEWOBJECT("prashant", new ArrayList<String>(), 5));
        usingPriorityQueue.add(new NEWOBJECT("gokul", new ArrayList<String>(), 2));
        usingPriorityQueue.add(new NEWOBJECT("narendra", new ArrayList<String>(), 4));

        StringBuffer buffer = new StringBuffer(15);
        while(usingPriorityQueue.size() != 0){
            buffer.append(usingPriorityQueue.remove().toString() + "\n");
        }
        System.out.println(buffer.toString());



    }


}
