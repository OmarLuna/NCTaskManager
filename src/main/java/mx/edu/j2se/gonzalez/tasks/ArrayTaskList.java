package mx.edu.j2se.gonzalez.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Iterable<Task>,Cloneable {
    Task[] taskList;

    public ArrayTaskList(){
        this.length = 0;
        this.typeList = ListTypes.types.ARRAY;
    }

    @Override
    public void add(Task task) throws NullPointerException{
        if(task == null){throw new NullPointerException();}
        length += 1;
        Task[] tmpTaskList = new Task[size()];
        if (size() - 1 > 0)
            System.arraycopy(taskList, 0, tmpTaskList, 0, size() - 1);
        tmpTaskList[size()-1]=task;
        taskList=tmpTaskList;
    }

    @Override
    public boolean remove(Task task) throws NullPointerException{
        if (task == null){throw new NullPointerException();}
        if (size() >= 1) {
            for (int i = 0; i < size() - 1; i++) {
                if (task.equals(taskList[i])) {
                    Task[] tmpTaskList = new Task[size() - 1];

                    /* Copies every task except the one to be removed to a new array */
                    System.arraycopy(taskList, 0, tmpTaskList, 0, size() - i - 2);
                    System.arraycopy(taskList, i + 1, tmpTaskList, i, size() - i - 1);

                    taskList = tmpTaskList;
                    length -= 1;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException, IllegalArgumentException{
        if (index>= size()){throw new IndexOutOfBoundsException();}
        if(index<0){throw new IllegalArgumentException();}
        return taskList[index];
    }

    @Override
    Stream<Task> getStream() {
        return Arrays.stream(taskList);
    }

    @Override
    public Iterator<Task> iterator() {
        ArrayTaskList.ArrayListIterator i = new ArrayTaskList.ArrayListIterator();
        return i.iterator();
    }

    protected class ArrayListIterator implements Iterable<Task>{
        private int pos;
        public ArrayListIterator(){ pos = 0;}
        @Override
        public Iterator<Task> iterator() {
            return new Iterator<Task>() {
                @Override
                public boolean hasNext() {
                    return size()-1<pos;
                }

                @Override
                public Task next() {
                    return taskList[pos++];
                }
            };
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("Array List ");
        for (Task task: taskList) s.append(task.toString());
        return s.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)return true;
        if(obj == null)return false;
        if(obj.getClass() != getClass())return false;
        ArrayTaskList ATL = (ArrayTaskList) obj;
        Iterator<Task> it1 = this.iterator();
        Iterator<Task> it2 = ATL.iterator();
        while (it1.hasNext() && it2.hasNext()){
            if(it1.next() != it2.next())
                return false;
        }
        return this.taskList[0].equals(ATL.taskList[0]);
    }

    @Override
    public ArrayTaskList clone() {
        try {
            ArrayTaskList clone = (ArrayTaskList) super.clone();
            for (Task task: taskList) {
                clone.add(task.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
