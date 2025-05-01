

//import backend.tasks.TaskImpl;
package backend;

import java.util.*;

public class CalculationEngine {
    private ArrayList<TaskImpl> the_list = new ArrayList<TaskImpl>();

    public CalculationEngine(ArrayList<TaskImpl> l){
        this.the_list = l;

    }
    
    public ArrayList<TaskImpl> getTheList(){
    	return the_list;
    }

    public List<Integer> calculateList(){
        int temp_start_date = getTheList().get(0).getStart();
        //int temp_start_date = 1000;
        int temp_end_date = getTheList().get(0).getEnd();
        //int temp_end_date = 0;
        int total_cost = 0;
        int duration = 0;
        for (TaskImpl t : getTheList()){
            if (t.getStart() < temp_start_date){
                temp_start_date = t.getStart();
            }
            if (t.getEnd() > temp_end_date){
                temp_end_date = t.getEnd();
            }
            total_cost += t.getCost();
        }

        duration = temp_end_date - temp_start_date;
        List<Integer> output = Arrays.asList(new Integer[4]);
        output.set(0, temp_start_date);
        output.set(1, temp_end_date);
        output.set(2, total_cost);
        output.set(3, duration);

        return output;

    }

}
