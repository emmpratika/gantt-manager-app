package backend;

//import backend.tasks.TaskImpl;

public class SubTask extends TaskImpl{
    //public int taskId;
    public SubTask(int taskId){
        super(taskId);
        topLevel = false;
    }
}