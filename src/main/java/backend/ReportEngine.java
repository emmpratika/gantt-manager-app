package backend;

import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;

public class ReportEngine {
    private List<String[]> all_tasks;
    private String[] columns;
    private String output_path;

    public ReportEngine(List<String[]> a_t, String[] column_names, String output_path){
        this.all_tasks = a_t;
        this.columns = column_names;
        this.output_path = output_path;

    }


    public void outputToTxt(){
        try (PrintWriter pw = new PrintWriter(new File(output_path + "gantt.txt"))){
            pw.println("Gantt Charts");
            for (String str : columns){
                pw.print(str + "\t");
            }
            pw.println();
            for (String[] a_task : all_tasks){
                for (String str : a_task){
                    pw.print(str + "\t");
                }
                pw.println();
            }
        } catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public void outputToHtml(){
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("<tr><th>TaskId</th><th>TaskText</th><th>MamaId</th><th><Start></th><th><End></th><th>Cost</th><th>Duration</th>");

        for (String[] a_task : all_tasks){
            sb.append("<tr>");
            for (String str : a_task){
                
                sb.append("<td>" + str + "</td>");
            }
            sb.append("</tr\n");
        }

        sb.append("</table>");

        String html = sb.toString();


        try (PrintWriter pw = new PrintWriter(new File(output_path + "gantt.html"))){
            pw.println(html);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public void outputToMarkdown(){
        try (PrintWriter pw = new PrintWriter(new File(output_path + "gantt.md"))){
            pw.println("_Gantt Charts_");
            for (String str : columns){
                pw.print(str + "\t");
            }
            pw.println();
            for (String[] a_task : all_tasks){
                for (String str : a_task){
                    if (Integer.parseInt(a_task[2]) == 0){
                        pw.print("**" + str + "**" + "\t");
                    }else{
                        pw.print(str + "\t");
                    }
                    
                    
                }
                pw.println(); //change to print(\n)
            }
        } catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }




}
