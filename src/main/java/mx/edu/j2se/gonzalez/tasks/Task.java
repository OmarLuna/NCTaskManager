package mx.edu.j2se.gonzalez.tasks;

public class Task {
    String title;
    int time=-1;
    int start=-1;
    int end=-1;
    int interval=-1;
    boolean active=false;

    /**
     *
     * @param title Title of the task
     * @param time The time the task will be run
     * @throws IllegalArgumentException-if the time is negative
     */
    public Task(String title, int time) throws IllegalArgumentException{
        if(time < 0){throw new IllegalArgumentException();}
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
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException{
        if(start < 0 || end < 0 || interval < 0 || start > end){throw new IllegalArgumentException();}
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
    public int getTime(){
        return isRepeated()?start:time;
    }

    /**
     * Sets a new time for the task, if the task is repetitive
     * it becomes non-repetitive
     * @param time The new time
     * @throws IllegalArgumentException-if the time is negative
     */
    public void setTime(int time) throws IllegalArgumentException{
        if (time < 0){throw new IllegalArgumentException();}
        if(this.isRepeated()) {
            this.start = -1;
            this.end = -1;
            this.interval = -1;
        }
        this.time = time;
    }
    public int getStartTime(){
        return isRepeated()?time:start;
    }
    public int getEndTime(){
        return isRepeated()?time:end;
    }
    public int getRepeatInterval(){
        return isRepeated()?0:interval;
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
    public void setTime(int start, int end, int interval) throws IllegalArgumentException{
        if(start < 0 || end < 0 || interval < 0 || start > end)
            throw new IllegalArgumentException();
        this.time=-1;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }
    public boolean isRepeated(){
        return time == -1;
    }

    /**
     * Calculates the time for the next task
     * @param current The current time
     * @return Returns the next start time of the task execution after the current time.
     * If after the specified time the task is not executed
     * anymore, the method will return -1
     * @throws IllegalArgumentException-if the current time is negative
     */
    public int nextTimeAfter(int current) throws IllegalArgumentException{
        if (current < 0)throw new IllegalArgumentException();
        if (!isActive()){return -1;}
        if (isRepeated()){
            int i = start;
            while (i < end) {
                if (i > current) {
                    return i;
                }
                i += interval;
            }
            return -1;
        }
        else
            return (current < time)?time:-1;
    }
}
