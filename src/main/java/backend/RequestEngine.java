package backend;
import java.util.*;
import java.lang.String;

public class RequestEngine {
    //private int given_id;
    //private String given_prefix;
    private List<String[]> sortedList;

    public RequestEngine(List<String[]> sL){
        this.sortedList = sL;
    }

    public List<String[]> requestTasksbyId(int id){
        List<String[]> out_byID = new ArrayList<>();

        for (String[] a_task : sortedList){
            if (Integer.parseInt(a_task[0]) == id){
                out_byID.add(a_task);
            }
        }

        return out_byID;

    }


    public List<String[]> requestTasksByPrefix(String prefix){
        System.out.println("Check prefix");
        List<String[]> out_byPrefix = new ArrayList<>();
        String firstWord;

        for (String[] a_task : sortedList){
            String[] splittedStrings = a_task[1].split(" ", 1);
            firstWord = splittedStrings[0];
            if (firstWord.startsWith(prefix)){
                out_byPrefix.add(a_task);
            }
        }

        return out_byPrefix;
    }


    

}
