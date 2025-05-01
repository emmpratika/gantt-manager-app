package backend;

import java.util.*;

//import backend.tasks.TaskImpl;

public class TopLevelTask extends TaskImpl{
    private boolean topLevel;
    private boolean complex;
    private List<Integer> task_values;
    private ArrayList<TaskImpl> childs;

    public TopLevelTask(int taskId){
        super(taskId);
        topLevel = true;
        childs = new ArrayList<TaskImpl>();
    }

    Comparator<TaskImpl> comparator = new Comparator<TaskImpl>() {
        @Override
        public int compare(TaskImpl o1, TaskImpl o2){
            if (o1.getStart() == o2.getStart()){
                return o1.getTaskId() - o2.getTaskId(); //ascending
            }else{
                return o1.getStart() - o2.getStart();
            }
        }
    };


    
    public boolean isTopLevel(){
        return topLevel;
    }

    public void setTopLevel(boolean b){
        topLevel = b;
    }


    public void setComplex(boolean t){
        complex = t;
    }
    
    public boolean isComplex(){
        return complex;
    }
    
    //ArrayList<TaskImpl> childs = new ArrayList<TaskImpl>();

    public void addChilds(TaskImpl task){
        childs.add(task);
    }

    public ArrayList<TaskImpl> getChilds(){
        return childs;
    }

    public void sortChilds(){
        Collections.sort(childs, comparator);
    }

    public void calculateList() {
        ArrayList<TaskImpl> temp_childs = getChilds();
        int temp_start_date = temp_childs.get(0).getStart();
        
        int temp_end_date = temp_childs.get(0).getEnd();
        int total_cost = 0;
        int duration = 0;
        for (TaskImpl t : temp_childs){
            if (t.getStart() < temp_start_date){
                temp_start_date = t.getStart();
            }
            if (t.getEnd() > temp_end_date){
                temp_end_date = t.getEnd();
            }
            total_cost += t.getCost();
        }
        
        setStart(temp_start_date);
        setEnd(temp_end_date);
        setCost(total_cost);
        setDuration(temp_end_date - temp_start_date);

    }

    
    
    /* public void takeComplexTasksInfos(getChilds()){
        sortChilds();
        CalculationEngine calcEngine = new CalculationEngine(getChilds());
        task_values = calcEngine.calculateList();
        setStart(task_values.get(0));
        setEnd(task_values.get(1));
        setCost(task_values.get(2));
        setDuration(task_values.get(3));
    } */
    
    

    

}
