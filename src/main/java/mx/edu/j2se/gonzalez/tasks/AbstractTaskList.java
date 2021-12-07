package mx.edu.j2se.gonzalez.tasks;

public abstract class AbstractTaskList {
    int length;
    ListTypes.types typeList;

    /**
     * Method to add a task to the list
     * @param task The task to add
     * @throws NullPointerException-if the task is null
     */
    abstract void add(Task task) throws NullPointerException;

    /**
     * @param task The to be removed
     * @return True if the task was removed, if the task wasn't
     * found returns false.
     * @throws NullPointerException-if the task is null
     */
    abstract boolean remove(Task task) throws NullPointerException;

    /**
     * Returns the number of tasks in the list
     */
    public int size(){
        return length;
    }


    /**
     * @param index Index of the task
     * @return An object task.
     * @throws IndexOutOfBoundsException-if the index is bigger than the task list
     * @throws IllegalArgumentException-if the index is negative
     */
    abstract Task getTask(int index) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Determines what task will be active in the interval of time.
     * @param from The start of the interval
     * @param to The end of the interval
     * @return Returns a subset of tasks that are scheduled for execution
     * at least once after the "from" time, and not later than the "to" time.
     * If the task list or the subset list have no elements returns null.
     * @throws IllegalArgumentException-if the 'from' time is bigger than the
     * 'to' time or either argument is negative
     */
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException{
        if(from >= to || from < 0){throw new IllegalArgumentException();}
        if(size()<1){return  null;}
        else {
            AbstractTaskList tmpAbstractList = TaskListFactory.createTaskList(typeList);
            for (int i = 0; i < this.size(); i++) {
                if (this.getTask(i).nextTimeAfter(from) <= to &&
                        this.getTask(i).nextTimeAfter(from) != -1)
                    tmpAbstractList.add(this.getTask(i));
            }
            return tmpAbstractList.size()==0?null:tmpAbstractList;
        }
    }
}
