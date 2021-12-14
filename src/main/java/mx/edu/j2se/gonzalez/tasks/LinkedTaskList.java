package mx.edu.j2se.gonzalez.tasks;

import sun.awt.image.ImageWatched;

import javax.sound.sampled.Line;
import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList implements Iterable<Task>,Cloneable{
    Node first;
    Node last;
    private class Node {
        Task task;
        Node next;
    }

    public LinkedTaskList(){
        this.length = 0;
        this.typeList = ListTypes.types.LINKED;
    }

    /**
     * Adds a task to the list.
     * @param task The task that will be added.
     * @throws NullPointerException-if the task is null
     */
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
            last.next = first;
        }
    }

    /**Removes a task from the array of task
     * @param task The task that will be removed.
     * @return Returns true if the task was removed from the list.
     * If the task wasn't found returns false.
     * @throws NullPointerException-if the task is null
     */
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


    /**
     * @param index Index of the task
     * @return An object task.
     * @throws IndexOutOfBoundsException-if the index is bigger than the task list
     * @throws IllegalArgumentException-if the index is negative
     */
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
                    return current.next!=first;
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
        String s = "Linked List ";
        for (Task task: this) {
            s += task.toString();
        }
        return s;
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
            if(it1.next() != it2.next())
                return false;
        }
        return this.first.task.equals(ATL.first.task);
    }

    @Override
    public LinkedTaskList clone() {
        try {
            LinkedTaskList clone = (LinkedTaskList) super.clone();
            Iterator<Task> it = this.iterator();
            clone.add(first.task.clone());
            while (it.hasNext()) {
                clone.add(it.next().clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
