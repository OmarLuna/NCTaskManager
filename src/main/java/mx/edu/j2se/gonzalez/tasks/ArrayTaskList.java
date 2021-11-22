package mx.edu.j2se.gonzalez.tasks;

import sun.plugin.javascript.navig.Array;

public class ArrayTaskList {
    Task[] taskList;
    int length =0;

    /**
     * Adds a task to the list.
     * @param task The task that will be added.
     */
    public void add(Task task){
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
     */
    public boolean remove(Task task){
        for (int i=0;i<size()-1;i++){
            if(task.equals(taskList[i])){
                Task[] tmpTaskList = new Task[size()-1];

                /* Copies every task except the one to be removed to a new array */
                System.arraycopy(taskList,0,tmpTaskList,0,size()-i-2);
                System.arraycopy(taskList,i+1,tmpTaskList,i,size()-i-1);

                taskList = tmpTaskList;
                length-=1;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of tasks in the list
     */
    public int size(){
        return length;
    }

    /**
     * @param index Index of the task
     * @return An object task.
     */
    public Task getTask(int index) {
        return taskList[index];
    }

    /**
     * Determines what task will be active in the interval of time.
     * @param from The start of the interval
     * @param to The end of the interval
     * @return Returns a subset of tasks that are scheduled for execution
     * at least once after the "from" time, and not later than the "to" time.
     */
    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList tmpArrayList = new ArrayTaskList();
        for (int i=0;i<this.size();i++){
            //System.out.println(taskList[i].getTitle());
            if(taskList[i].nextTimeAfter(from)<= to && taskList[i].nextTimeAfter(from)!=-1)
                tmpArrayList.add(taskList[i]);
        }
        return tmpArrayList;
    }
}
