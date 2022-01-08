package mx.edu.j2se.gonzalez.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Tasks {
    /**
     * Determines what task will be active in the interval of time.
     * @param tasks The iterator for the list of tasks
     * @param start The start of the interval
     * @param end The end of the interval
     * @return Returns a subset of tasks that are scheduled for execution
     * at least once after the "start" time, and not later than the "end" time.
     * If the task list or the subset list have no elements returns null.
     * @throws NullPointerException-if the tasks, start or end arguments are null
     */
    public static Iterator<Task> incoming(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws NullPointerException{
        if(tasks == null || start == null || end == null){throw new NullPointerException();}
        else {
            Stream.Builder<Task> builder = Stream.builder();
            while(tasks.hasNext()){
                builder.add(tasks.next());
            }
            return builder.build()
                    .filter(task -> task.nextTimeAfter(start).isBefore(end) ||
                            task.nextTimeAfter(start).isEqual(end))
                    .iterator();
        }
    }

    /**
     * This method builds the calendar of tasks for the specified period – a table, where each
     * date corresponds to a set of tasks that should be performed at this time.
     * At this, one task can refer to several dates, if it should be performed multiple
     * times for the specified period.
     * @param tasks An iterator of tasks.
     * @param start The start time.
     * @param end The end time.
     * @return A map with the tasks in the specified start time to end time.
     * @throws NullPointerException-if any argument is null.
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterator<Task> tasks,
                                                               LocalDateTime start,
                                                               LocalDateTime end) throws NullPointerException{
        if(tasks == null || start == null || end == null) throw new NullPointerException();
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<LocalDateTime, Set<Task>>();
        while(tasks.hasNext()){
            Task tmptask = tasks.next();
            if(map.containsKey(tmptask.getTime())){
                map.get(tmptask.getTime()).add(tmptask);
            }
            else {
                Set<Task> taskSet = new HashSet<Task>();
                taskSet.add(tmptask);
                map.put(tmptask.getTime(),taskSet);
            }
        }
        return map.subMap(start,end);
    }
}
