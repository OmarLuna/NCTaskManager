package mx.edu.j2se.gonzalez.tasks;

import java.util.stream.Stream;

public abstract class AbstractTaskList{
    int length;

    /**
     * Method to add a task to the list
     * @param task The task to add
     * @throws NullPointerException-if the task is null
     */
    abstract void add(Task task);

    /**
     * @param task The to be removed
     * @return True if the task was removed, if the task wasn't
     * found returns false.
     * @throws NullPointerException-if the task is null
     */
    abstract boolean remove(Task task);

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
    abstract Task getTask(int index) throws IllegalArgumentException;

    abstract ListTypes.types getType();

    abstract Stream<Task> getStream();
}
