package mx.edu.j2se.gonzalez.tasks;

import sun.util.resources.cldr.yav.LocaleNames_yav;

import java.util.Iterator;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Iterable<Task>,Cloneable{
    Node first;
    Node last;
    public final static ListTypes.types type = ListTypes.types.LINKED;
    private class Node {
        Task task;
        Node next;
    }

    public LinkedTaskList(){
        this.length = 0;
    }

    @Override
    public void add(Task task) throws NullPointerException{
        if (task == null){throw new NullPointerException();}
        else{
            length += 1;
            if(size() == 1){
                first = this.new Node();
                first.task = task;
                last = first;
            }
            else{
                last.next = this.new Node();
                last = last.next;
                last.task = task;
            }
            last.next = null;
        }
    }

    @Override
    public boolean remove(Task task) throws NullPointerException{
        if(task == null){throw new NullPointerException();}
        if (size()<1){return false;}
        else {
            Node tmpNode = first;
            if(task.equals(tmpNode.task)){
                first = first.next;
                last.next = first;
                length -= 1;
                return true;
            }
            else{
                for(int i=0;i<size()-1;i++){
                    if(task.equals(tmpNode.next.task)){
                        tmpNode.next = tmpNode.next.next;
                        length -= 1;
                        return true;
                    }
                    tmpNode = tmpNode.next;
                }
                return false;
            }
        }
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if(index >= size() || index < 0){throw new IndexOutOfBoundsException();}
        else{
            Node tmpNode = first;
            int i = 0;
            while(i < index){
                tmpNode = tmpNode.next;
                i++;
            }
            return tmpNode.task;
        }
    }

    @Override
    ListTypes.types getType(){return type;}

    @Override
    public Stream<Task> getStream(){
        Stream.Builder<Task> builder = Stream.builder();
        Iterator<Task> it = this.iterator();
        builder.add(first.task);
        it.forEachRemaining(builder::add);
        return builder.build();
    }

    @Override
    public Iterator<Task> iterator() {
        LinkedListIterator i = new LinkedListIterator();
        return i.iterator();
    }

    protected class LinkedListIterator implements Iterable<Task>{
        Node current;
        public LinkedListIterator(){
            this.current = first;
        }
        @Override
        public Iterator<Task> iterator() {
            return new Iterator<Task>() {
                @Override
                public boolean hasNext() {
                    return current.next!=null;
                }

                @Override
                public Task next() {
                    current = current.next;
                    return current.task;
                }
            };
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("Linked List ");
        for (Task task: this) {
            s.append(task.toString()).append(" , ");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)return true;
        if(obj == null)return false;
        if(obj.getClass() != getClass())return false;
        LinkedTaskList ATL = (LinkedTaskList) obj;
        Iterator<Task> it1 = this.iterator();
        Iterator<Task> it2 = ATL.iterator();
        while (it1.hasNext() && it2.hasNext()){
            if(!it1.next().equals(it2.next()))
                return false;
        }
        return this.first.task.equals(ATL.first.task);
    }

    @Override
    public LinkedTaskList clone() {
        try {
            LinkedTaskList clone = (LinkedTaskList) super.clone();
            Iterator<Task> it = this.iterator();
            clone.first = null;
            clone.last = null;
            clone.add(this.first.task.clone());
            while (it.hasNext()) {
                clone.add(it.next().clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
