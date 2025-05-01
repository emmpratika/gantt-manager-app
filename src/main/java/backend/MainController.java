package backend;

import dom2app.SimpleTableModel;
import java.util.*;

public class MainController implements IMainController {
    private String filename;
    private String del;
    private String prjName = "Gantt Chart Management System";
    private String[] columns = {"TaskId", "TaskText", "MamaId", "Start", "End", "Cost", "Duration"};
    private List<String[]> loaded_tasks;
    private String output_path;

    //public MainController(){};


    public String getFileName(){
        return filename;
    }

    public String getDelimeter(){
        return del;
    }

    public String[] getColumns(){
        return columns;
    }

    public List<String[]> getLoadedTasks(){
        return loaded_tasks;
    }

    public String getOutputPath(){
        return output_path;
    }





    public SimpleTableModel load(String fileName, String delimeter){
        this.filename = fileName;
        this.del = delimeter;
        LoadFileManager loader = new LoadFileManager(fileName, delimeter);
        loader.readFile();
        loader.takeBChilds();
        loader.setComplexTasksInfos();
        SortEngine sortEngine = new SortEngine(loader.getParentsMap());
        sortEngine.sortingList(sortEngine.getSortedTopLevelList());
        
        loaded_tasks = sortEngine.convertToList(sortEngine.getSortedList());

        String name = "LoadTasks";
        SimpleTableModel loadedTasksModel = new SimpleTableModel(name, prjName, columns, loaded_tasks);
        System.out.println(loadedTasksModel);
        return loadedTasksModel; 
    }




    public SimpleTableModel getTaskById(int id){
        LoadFileManager loader = new LoadFileManager(getFileName(), getDelimeter());
        loader.readFile();
        loader.takeBChilds();
        loader.setComplexTasksInfos();
        SortEngine sortEngine = new SortEngine(loader.getParentsMap());
        sortEngine.sortingList(sortEngine.getSortedTopLevelList());

        //RequestEngine requester = new RequestEngine(sortEngine.convertToList(sortEngine.getSortedList()));
        RequestEngine requester = new RequestEngine(loaded_tasks);
        String name = "GetTaskById";
        SimpleTableModel getTaskbyIdModel = new SimpleTableModel(name, prjName, columns, requester.requestTasksbyId(id));
        return getTaskbyIdModel;
    }


    public SimpleTableModel getTasksByPrefix(String prefix){
        LoadFileManager loader = new LoadFileManager(getFileName(), getDelimeter());
        loader.readFile();
        loader.takeBChilds();
        SortEngine sortEngine = new SortEngine(loader.getParentsMap());
        sortEngine.sortingList(sortEngine.getSortedTopLevelList());

        //RequestEngine requester = new RequestEngine(sortEngine.convertToList(sortEngine.getSortedList()));
        RequestEngine requester = new RequestEngine(loaded_tasks);
        String name = "GetTasksByPrefix";

        SimpleTableModel getTasksByPrefixModel = new SimpleTableModel(name, prjName, columns, requester.requestTasksByPrefix(prefix));
        return getTasksByPrefixModel;

    }

    public SimpleTableModel getTopLevelTasks(){
        LoadFileManager loader = new LoadFileManager(getFileName(), getDelimeter());
        loader.readFile();
        loader.takeBChilds();
        SortEngine sortEngine = new SortEngine(loader.getParentsMap());

        String name = "GetTopLevelTasks";
        SimpleTableModel getTopLevelTasksModel = new SimpleTableModel(name, prjName, columns, sortEngine.convertToList(sortEngine.getSortedTopLevelList()));
        return getTopLevelTasksModel;
    }


    public int createReport(String path, ReportType type){
        ReportEngine reportGenerator = new ReportEngine(loaded_tasks, columns, path);
        
        switch (type){
            case TEXT:
                reportGenerator.outputToTxt();
                break;

            case HTML:
                reportGenerator.outputToHtml();
                break;

            case MD:
                reportGenerator.outputToMarkdown();
                break;
        }

        return 1;
    }

    


}
