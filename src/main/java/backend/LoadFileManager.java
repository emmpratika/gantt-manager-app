package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;
//import java.nio.Buffer;;

//import backend.tasks.TaskImpl;

///import backend.tasks.TaskImpl;


public class LoadFileManager {
    
    String filePath;
    String del;
    File loadedfile;
    private Map<Integer, TaskImpl> parentsMap = new HashMap<>();
    private Map<Integer, TaskImpl> illegitimates = new HashMap<>();


    public LoadFileManager(String fp, String del){
        this.filePath = fp;
        this.del = del;
        loadedfile = new File(filePath);
    }

    public void readFile(){
        String line;
        String seperator = del;
        //String seperator = "\\t";
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(loadedfile));
        } catch (FileNotFoundException fileex) {
            System.out.println(fileex.getMessage() + "This File Was Not Found");
            System.exit(0);
        }


        try {
            while ((line = reader.readLine()) != null){
            	System.out.println(line);
            	String[] splitLine = line.split(seperator);
                
                //for (String str : splitLine){
                //    System.out.println(str);
                //}
                TaskImpl t = createTasks(splitLine);
                
                /* //if (t == null){
                //System.out.println("null");
                //} */

                //System.out.println("Check creation");
            	loadTasks(t);
                //System.out.println("ReadCheck1");
            }
        } catch (IOException ioex){
            System.out.println(ioex.getMessage() + "Error Reading File");
        } finally {
            System.exit(0);
        }
    
    }


    public TaskImpl createTasks(String[] input){
    	//System.out.println("The mamaid is " + input[2]);
        
        //for (String str : input){
            //System.out.print("*"+str + " ");
        //}

        if (Integer.parseInt(input[2]) == 0){
            TopLevelTask task = new TopLevelTask(Integer.parseInt(input[0]));
            //System.out.println(input.length);
            
            /* if (task == null){
                System.out.println("null");
            } */
            
            if (input.length == 3){
                task.setTopLevel(true);
                ((TopLevelTask)task).setComplex(true);
                task.setTaskText(input[1]);
                task.setMamaId(Integer.parseInt(input[2]));
                //task.setStart(Integer.parseInt(input[3]));
                //task.setEnd(Integer.parseInt(input[4]));
                //task.setCost(Integer.parseInt(input[5]));
                //System.out.println("CreateCheck1");
            }else{
                task.setTopLevel(true);
                task.setTaskText(input[1]);
                task.setMamaId(Integer.parseInt(input[2]));
                task.setStart(Integer.parseInt(input[3]));
                task.setEnd(Integer.parseInt(input[4]));
                task.setCost(Integer.parseInt(input[5]));
                System.out.println("CreateCheck2");
            }
            //System.out.println("CreateCheck2");
            return task;
        }else{
            SubTask task = new SubTask(Integer.parseInt(input[0]));
            task.setTaskText(input[1]);
            task.setMamaId(Integer.parseInt(input[2]));
            task.setStart(Integer.parseInt(input[3]));
            task.setEnd(Integer.parseInt(input[4]));
            task.setCost(Integer.parseInt(input[5]));
            //System.out.println("CreateCheck3");
            return task;
        } 
        
        
    }


    public void loadTasks(TaskImpl t){
        if (t.isTopLevel()){
            if (!parentsMap.containsKey(t.getTaskId())){
                parentsMap.put(t.getTaskId(), t);
                //System.out.println(parentsMap);
            }
        }else{
            if (parentsMap.containsKey(t.getMamaId())){
                ((TopLevelTask)parentsMap.get(t.getMamaId())).addChilds(t);
            }else{
                illegitimates.put(t.getTaskId(), t);
            }
        //System.out.println("LoadCheck");
        //System.out.println(parentsMap);
        //System.out.println(illegitimates);
        }



    }

    /* public void setComplexTasksInfos(){
        for (Map.Entry<Integer, TaskImpl> entry : parentsMap.entrySet()){
            if (((TopLevelTask)entry.getValue()).isComplex()){
                //ArrayList<TaskImpl> entry_childs = ((TopLevelTask)entry.getValue()).getChilds();
                ((TopLevelTask)entry.getValue()).takeComplexTasksInfos();
            }
        }
    } */

    public void setComplexTasksInfos(){
        /* for (Map.Entry<Integer, TaskImpl> entry : parentsMap.entrySet()){
            ((TopLevelTask)entry.getValue()).calculateList();
        } */
        for (TaskImpl task : parentsMap.values()){
            ((TopLevelTask)task).calculateList();
        }
    }

    public void takeBChilds(){
        for (Map.Entry<Integer, TaskImpl> entry : illegitimates.entrySet()){
            if (parentsMap.containsKey(entry.getValue().getMamaId())){
                ((TopLevelTask)parentsMap.get(entry.getValue().getMamaId())).addChilds(entry.getValue());
            }
        }
        
        setComplexTasksInfos();
        
    }

    
    


    
    /*public void loadTasks(TaskImpl t){
        if (!parentsTaskId.containsKey(t.getTaskId())){
            parentsTaskId.put(t.getTaskId(), t);
        }else if (parentsTaskId.containsKey(t.getMamaId())){
            ((TopLevelTask)parentsTaskId.get(t.getMamaId())).addChilds(t);
        }
        
    }*/


    public File getFile(){
        return loadedfile;
    }

    public Map<Integer, TaskImpl> getParentsMap(){
        return parentsMap;
    }
    
}
