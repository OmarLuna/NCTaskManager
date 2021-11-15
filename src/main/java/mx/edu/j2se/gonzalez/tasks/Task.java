package mx.edu.j2se.gonzalez.tasks;

public class Task {
    String title;
    int time=-1;
    int start=-1;
    int end=-1;
    int interval=-1;
    boolean active=false;
    public Task(String title, int time){
        this.title=title;
        this.time=time;
    }
    public Task(String title, int start, int end, int interval){
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
        return time==-1?start:time;
    }
    public void setTime(int time){
        if(this.time ==-1)
            this.start=time;
        else
            this.time=time;
    }
    public int getStartTime(){
        return start==-1?time:start;
    }
    public int getEndTime(){
        return end==-1?time:end;
    }
    public int getRepeatInterval(){
        return interval==-1?0:interval;
    }
    public void setTime(int start, int end, int interval){
        this.time=-1;
        this.start=start;
        this.end=end;
        this.interval=interval;
    }
    public boolean isRepeated(){
        return time == -1;
    }
    public int nextTimeAfter(int current){
        int i=start;
        while(i<end){
            i += interval;
            if(i>current){
                return i;
            }
        }
        return -1;
    }
}
