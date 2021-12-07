package mx.edu.j2se.gonzalez.tasks;


public class ArrayTaskList extends AbstractTaskList{
    Task[] taskList;
    int length =0;
    ListTypes.types typeList = ListTypes.types.ARRAY;

    /**
     * Adds a task to the list.
     * @param task The task that will be added.
     * @throws NullPointerException-if the task is null
     */
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

    /**
     * Removes a task from the array of task
     * @param task The task that will be removed.
     * @return Returns true if the task was removed from the list.
     * If the task wasn't found returns false.
     * @throws NullPointerException-if the task is null
     */
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


    /**
     * @param index Index of the task
     * @return An object task.
     * @throws IndexOutOfBoundsException-if the index is bigger than the task list
     * @throws IllegalArgumentException-if the index is negative
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException, IllegalArgumentException{
        if (index>= size()){throw new IndexOutOfBoundsException();}
        if(index<0){throw new IllegalArgumentException();}
        return taskList[index];
    }

    /*
    @Override
    public ArrayTaskList incoming(int from, int to) throws IllegalArgumentException{
        if(from >= to || from < 0){throw new IllegalArgumentException();}
        if(size()<1){return  null;}
        else {
            ArrayTaskList tmpArrayList = new ArrayTaskList();
            for (int i = 0; i < this.size(); i++) {
                if (taskList[i].nextTimeAfter(from) <= to && taskList[i].nextTimeAfter(from) != -1)
                    tmpArrayList.add(taskList[i]);
            }
            return tmpArrayList.size()==0?null:tmpArrayList;
        }
    }*/
}
