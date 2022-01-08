package mx.edu.j2se.gonzalez.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;

public class TaskIO {
    /**
     * Writes the tasks from the
     * list to the stream in a binary format.
     * @param tasks List of tasks
     * @param out The OutputStream
     */
    public static void writeBinary(AbstractTaskList tasks, OutputStream out){
        try {
            DataOutputStream outputStream = new DataOutputStream(out);
            outputStream.writeInt(tasks.size());
            Iterator<Task> it = tasks.getStream().iterator();
            it.forEachRemaining(task ->
            {
                try {
                    outputStream.writeInt(task.getTitle().length());
                    outputStream.writeUTF(task.getTitle());
                    outputStream.writeInt(task.isActive()?1:0);
                    outputStream.writeInt(task.getRepeatInterval());
                    outputStream.writeInt(task.isRepeated()?1:0);
                    if (task.isRepeated()){
                        outputStream.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                        outputStream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                    }
                    else{
                        outputStream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the binary
     * stream to the current task list.
     * @param tasks The list of tasks
     * @param in The inputStream
     */
    public static void readBinary(AbstractTaskList tasks, InputStream in){
        try {
            DataInputStream inputStream = new DataInputStream(in);
            int size = inputStream.readInt();
            for(int i=0; i<size;i++){
                int length = inputStream.readInt();
                String title = inputStream.readUTF();
                Boolean isActive = inputStream.readInt()==1;
                int interval = inputStream.readInt();
                Boolean isRepeated = inputStream.readInt()==1;
                if(isRepeated){
                    LocalDateTime start = LocalDateTime.ofEpochSecond(inputStream.readLong(),0,ZoneOffset.UTC);
                    LocalDateTime end = LocalDateTime.ofEpochSecond(inputStream.readLong(),0,ZoneOffset.UTC);
                    Task t = new Task(title,start,end,interval);
                    t.setActive(isActive);
                    tasks.add(t);
                }
                else{
                    LocalDateTime time = LocalDateTime.ofEpochSecond(inputStream.readLong(),0,ZoneOffset.UTC);
                    Task t = new Task(title,time);
                    t.setActive(isActive);
                    tasks.add(t);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks from the list to the binary file.
     * @param tasks The list of tasks
     * @param file The file output
     */
    public static void write(AbstractTaskList tasks, File file){
        try{
            OutputStream outputStream = new FileOutputStream(file);
            TaskIO.writeBinary(tasks,outputStream);
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the binary file to the task
     * list.
     * @param tasks The list of tasks
     * @param file The file input
     */
    public static void read(AbstractTaskList tasks, File file){
        try{
            InputStream inputStream = new FileInputStream(file);
            TaskIO.readBinary(tasks,inputStream);
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * writes tasks from the list to the stream in
     * the JSON format.
     * @param tasks The list of tasks
     * @param out The writer
     */
    public static void write(AbstractTaskList tasks, Writer out){
        try {
            Gson gson = new Gson();
            gson.toJson(tasks, out);
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the JSON stream to the list
     * @param tasks The list of tasks
     * @param in The reader
     */
    public static void read(AbstractTaskList tasks, Reader in){
        Gson gson = new Gson();
        ArrayTaskList taskList = gson.fromJson(in,ArrayTaskList.class);
        taskList.getStream().forEach(tasks::add);
    }

    /**
     * Writes tasks to the file in JSON format.
     * @param tasks The list of tasks
     * @param file The file output
     */
    public static void writeText(AbstractTaskList tasks, File file){
        try{
            Writer writer = new FileWriter(file);
            TaskIO.write(tasks,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the JSON file.
     * @param tasks The list of tasks
     * @param file The file input
     */
    public static void readText(AbstractTaskList tasks, File file){
        try{
            Reader reader = new FileReader(file);
            TaskIO.read(tasks, file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
