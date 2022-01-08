package mx.edu.j2se.gonzalez.tasks;

import java.time.LocalDateTime;

public class Task implements Cloneable{
    String title;
    LocalDateTime time;
    LocalDateTime start;
    LocalDateTime end;
    int interval=-1;
    boolean active=false;

    /**
     *
     * @param title Title of the task
     * @param time The time the task will be run
     * @throws IllegalArgumentException-if the time is negative
     */
    public Task(String title, LocalDateTime time) throws NullPointerException{
        if(time == null){throw new NullPointerException();}
        this.title=title;
        this.time=time;
    }

    /**
     * @param title Title of the task
     * @param start Time when teh task starts
     * @param end Time when the task ends
     * @param interval The intervals the task should be repeated
     * @throws IllegalArgumentException-if the start,end or interval
     * time are negative or the start time is bigger than the end time
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval)
            throws IllegalArgumentException{
        if(start == null || end == null || interval < 0 || start.isAfter(end)){throw new IllegalArgumentException();}
        this.title=title;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }
    public String getTitle (){
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public boolean isActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active=active;
    }
    public LocalDateTime getTime(){
        return isRepeated()?start:time;
    }

    /**
     * Sets a new time for the task, if the task is repetitive
     * it becomes non-repetitive
     * @param time The new time
     * @throws IllegalArgumentException-if the time is negative
     */
    public void setTime(LocalDateTime time) throws NullPointerException{
        if (time == null){throw new NullPointerException();}
        if(this.isRepeated()) {
            this.start = null;
            this.end = null;
            this.interval = -1;
        }
        this.time = time;
    }
    public LocalDateTime getStartTime(){
        return isRepeated()?time:start;
    }
    public LocalDateTime getEndTime(){
        return isRepeated()?time:end;
    }
    public int getRepeatInterval(){
        return isRepeated()?interval:-1;
    }

    /**
     * If the task wasn't a repetitive task executing this method will change it to
     * a repetitive task
     * @param start The time the task starts
     * @param end The time the task ends
     * @param interval The interval of the task
     * @throws IllegalArgumentException-if one of the arguments is negative or
     * the start time is bigger than the end time
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException{
        if(start == null || end == null || interval < 0 || start.isAfter(end))
            throw new IllegalArgumentException();
        this.time=null;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }
    public boolean isRepeated(){
        return interval == -1;
    }

    /**
     * Calculates the time for the next task
     * @param current The current time
     * @return Returns the next start time of the task execution after the current time.
     * If after the specified time the task is not executed
     * anymore, the method will return null
     * @throws NullPointerException-if the current time is null
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) throws NullPointerException{
        if (current == null)throw new NullPointerException();
        if (!isActive()){return null;}
        if (isRepeated()){
            int hours = 0;
            while (start.plusHours(hours).isBefore(end)) {
                if (start.plusHours(hours).isAfter(current)) {
                    return start.plusHours(hours);
                }
                hours += interval;
            }
            return null;
        }
        else
            return current.isBefore(time)?time:null;
    }

    @Override
    public String toString(){
        String ret;
        if(isRepeated()){
            ret = "Task: "+title+" Start: "+start+" End: "+end+
                    " Interval: "+interval+(isActive()?"is active":"is not active");
        }else{
            ret = "Task: "+title+" Time: "+time+(isActive()?"is active":"is not active");
        }
        return ret;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)return true;
        if (obj == null)return false;
        if(obj.getClass()!= getClass())return false;
        Task task = (Task) obj;
        if(task.isRepeated()){
            return this.title.equals(task.title)&&
                    this.start.isEqual(task.start)&&
                    this.end.isEqual(task.end)&&
                    this.interval == task.interval&&
                    this.isActive() == task.isActive();
        }else{
            return this.title.equals(task.title)&&
                    this.time.isEqual(task.time)&&
                    this.isActive()==task.isActive();
        }
    }


    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
