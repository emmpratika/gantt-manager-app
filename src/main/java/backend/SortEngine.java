package backend;


import java.util.*;
//import java.util.function.UnaryOperator;

public class SortEngine {
    //private String filePath;
    private Map<Integer, TaskImpl> unsortedMap;
    //private LoadFileManager loadFileManager;
    private ArrayList<TaskImpl> sortedTopLevelList;
    private ArrayList<TaskImpl> sortedList;

    public SortEngine(Map<Integer, TaskImpl> map){
        this.unsortedMap = map;
        //this.filePath = fp;
        //loadFileManager = new LoadFileManager(fp, del);
        sortedTopLevelList = new ArrayList<>();
        sortedList = new ArrayList<>();
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

    /* public void calculateInSortEngine(){
        int temp_start_date = 1000;
        int temp_end_date = 0;
        int total_cost = 0;
        int duration = 0;

        /////CalculationEngine calculator = new CalculationEngine(sortedTopLevelList);
        //List<Integer> calc_output = calculator.calculateList();

        for (TaskImpl t : sortedTopLevelList){
            for (TaskImpl childs : ((TopLevelTask)t).getChilds()){
                if (childs.getStart() < temp_start_date){
                    temp_start_date = childs.getStart();
                }
                if (childs.getEnd() > temp_end_date){
                    temp_end_date = childs.getEnd();
                }
                total_cost += childs.getCost();
                
            }
            t.setStart(temp_start_date);
            t.setEnd(temp_end_date);
            t.setEnd(temp_end_date);
            t.setDuration(temp_end_date - temp_start_date);
        }
    }
     */

    

    public void sortingTopLevelTasks(){
        
        //setComplexTasksInfos(); 

        for (TaskImpl t : unsortedMap.values()){
            //((TopLevelTask)t).calculateList();
            sortedTopLevelList.add(t);
            
        }
        //calculateInSortEngine();



        Collections.sort(sortedTopLevelList, comparator);
        //System.out.println(sortedTopLevelList.toString());

        


    }


    

    public ArrayList<TaskImpl> getSortedTopLevelList(){
        sortingTopLevelTasks();
        return sortedTopLevelList;
    }


    public void sortingList(ArrayList<TaskImpl> top_level_List){
        ArrayList<TaskImpl> tempSortedTopLevel = top_level_List;
        for (TaskImpl t : tempSortedTopLevel){
            sortedList.add(t);
            if (((TopLevelTask)t).isComplex()){
                ((TopLevelTask)t).sortChilds();
                
                for (TaskImpl t_childs : ((TopLevelTask)t).getChilds()){
                    sortedList.add(t_childs);
                }
            }
        }

    }

    public ArrayList<TaskImpl> getSortedList(){
        //sortingList();
        return sortedList;
    }

    public List<String[]> convertToList(ArrayList<TaskImpl> arraylist){
        String[] a_task;
        List<String[]> list = new ArrayList<>();
        for (TaskImpl t : arraylist){
            a_task = new String[6];
            a_task[0] = Integer.toString(t.getTaskId());
            a_task[1] = t.getTaskText();
            a_task[2] = Integer.toString(t.getMamaId());
            a_task[3] = Integer.toString(t.getStart());
            a_task[4] = Integer.toString(t.getEnd());
            a_task[5] = Integer.toString(t.getCost());
            a_task[6] = Integer.toString(t.getDuration());
            list.add(a_task);
        }
        return list;
    }



}
