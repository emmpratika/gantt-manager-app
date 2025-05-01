package backend;

public interface TaskInterface {



    //Accessors
    public int getTaskId();

    public String getTaskText();

    public int getStart();

    public int getEnd();

    public int getCost();

    //Mutators
    public void setTaskText(String text);
    public void setStart(int start_date);
    public void setEnd(int end_date);
    public void setCost(int cost);


}
