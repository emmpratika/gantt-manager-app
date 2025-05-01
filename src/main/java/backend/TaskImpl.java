package backend;



public class TaskImpl implements TaskInterface {
    protected int taskId;
    protected String taskText;
    protected int mama_id;
    protected int start;
    protected int end;
    protected int duration;
    protected int cost;
    protected boolean topLevel;

    public TaskImpl(){};
    //Constructor
    public TaskImpl(int taskId){
        this.taskId = taskId;
    }

    //Mutators
    
    public void setTaskText(String text){
        this.taskText = text;
    }

    public void setMamaId(int m){
        this.mama_id = m;
    }

    public void setStart(int start_date){
        this.start = start_date;
    }

    public void setEnd(int end_date){
        this.end = end_date;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setTopLevel(boolean b){
        topLevel = b;
    }

    


    //Accessors
    public int getTaskId(){
        return taskId;
    }

    public String getTaskText(){
        return taskText;
    }

    public int getMamaId(){
        return mama_id;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    public int getCost(){
        return cost;
    }

    public int getDuration(){
        duration = end - start;
        return duration;
    }

    public boolean isTopLevel(){
        return topLevel;
    }


    
}
