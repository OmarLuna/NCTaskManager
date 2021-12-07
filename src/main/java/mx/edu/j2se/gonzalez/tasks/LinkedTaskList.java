package mx.edu.j2se.gonzalez.tasks;

public class LinkedTaskList extends AbstractTaskList{
    int length=0;
    Node first;
    Node last;
    ListTypes.types typeList = ListTypes.types.LINKED;
    private class Node {
        Task task;
        Node next;
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
                last.next = first;
            }
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

    /*
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException{
        if(from >= to || from < 0){throw new IllegalArgumentException();}
        if (this.size() < 1){return null;}
        else {
            Node tmpNode = first;
            LinkedTaskList tmpLTL = new LinkedTaskList();
            for (int i=0;i<this.size();i++){
                if(tmpNode.task.nextTimeAfter(from) <= to && tmpNode.task.nextTimeAfter(from)!=-1)
                    tmpLTL.add(tmpNode.task);
                tmpNode = tmpNode.next;
            }
            return tmpLTL.size()==0?null:tmpLTL;
        }
    }*/
}
