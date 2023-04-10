/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package net.codejava.txtio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class TxtIO {

    public static void main(String[] args) {
        String folderpath = "C:/Users/ADMIN/Desktop/Assessment/Assessment/txtIO/read";
        String Date,CaseID,Image_Files,Date_test,CaseID_test,Image_test;
        int i =0;

        File folder = new File(folderpath);
        File[] files = folder.listFiles();
        if (files != null){
            for (File file: files){
                if(file.isFile() && file.getName().endsWith(".txt")){
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                        System.out.println(file.getName());
                        String FileName = file.getName();
                        BufferedWriter writer = new BufferedWriter(new FileWriter("output/"+FileName.substring(0,FileName.length() - 4)+".Failed.txt"));
    //                    BufferedWriter writer_SUCCESS = new BufferedWriter(new FileWriter("output/output_SUCCESS.txt"));
                        String line;
                        int lineCount = 0;

                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS a");
                        String formattedDateTime = now.format(formatter);

                        String imagePath = "path/to/image"; // replace with the path to your image

                        while((line = reader.readLine()) != null){
                            String[] data = line.split("\\|");
                            boolean endsWithDelimiter = line.endsWith("|");
                            if (line.length() == 0){

                            }
                            else{

                                if (data[0].length() == 0)
                                {
                                    Date = data[0];
                                    Date_test = "FAILED";
                                }
                                else {
                                    Date = data[0];
                                    Date_test = "";
                                }

                                if (data[1].length() == 0){
                                CaseID = "";
                                CaseID_test = "FAILED";
                                }
                                else{
                                CaseID = data[1];
                                CaseID_test = "";
                                }

                                if (endsWithDelimiter) {
                                    Image_Files = "";
                                    Image_test = "FAILED";
                                } else {
                                    Image_Files = data[2];
                                    Image_test = "";
                                }

                                File fileImage = new File(Image_Files);
                                long fileSizeInBytes = fileImage.length();
                                long fileSizeInKB = fileSizeInBytes / 1024;

                                if (fileSizeInKB > 10) {
                                    System.out.println("Image size is more than 10KB");
                                } else {
                                    System.out.println("Image size is 10KB or less");
                                }

                                if(Date_test != "FAILED" && CaseID_test != "FAILED" && Image_test != "FAILED"){
                                    writer.append(Date + "|" + CaseID + "|" + Image_Files + "|" + "PASSED" +"|" + formattedDateTime + "|" + "\n" + "\n");
                                    i = i + 1;
                                }
                                if(Date_test == "FAILED" && CaseID_test != "FAILED" && Image_test != "FAILED" ){
                                    writer.append(Date + "|" + CaseID + "|" + Image_Files + "|" + "FAILED" +"|" + formattedDateTime + "|" + "Date is missing" + "\n" + "\n");
                                }
                                if(Date_test != "FAILED" && CaseID_test == "FAILED" && Image_test != "FAILED" ){
                                    writer.append(Date + "|" + CaseID + "|" + Image_Files + "|" + "FAILED" +"|" + formattedDateTime + "|" + "Missing Case ID" + "\n" + "\n");
                                }
                                if(Date_test != "FAILED" && CaseID_test != "FAILED" && Image_test == "FAILED" ){
                                    writer.append(Date + "|" + CaseID + "|" + Image_Files + "|" + "FAILED" +"|" + formattedDateTime + "|" + "Missing image filename" + "\n" + "\n");
                                }
    //                            writer.append(Date + "|" + CaseID + "|" + Image_Files + "|" + java.time.LocalDateTime.now() + "|" + "\n" + "\n");
                                System.out.println(Date + "|" + CaseID + "|" + Image_Files + "|" + formattedDateTime + "|" + "\n"); //just for debugging



                            }
                        }
                        reader.close();
                        writer.close();

                        if(i==3){
                            File FileOld = new File("output/"+FileName.substring(0,FileName.length() - 4)+".Failed.txt");
                            File FileNew = new File("output/"+FileName.substring(0,FileName.length() - 4)+".Success.txt");
                            if (FileOld.exists() && !FileNew.exists()) {
                                boolean success = FileOld.renameTo(FileNew);
                                if (success) {
                                    System.out.println("File renamed successfully.");
                                } else {
                                    System.out.println("File rename failed.");
                                }
                            } 
                            else {
                                    System.out.println("File does not exist or new file name already exists.");
                            }
                        }
                    }catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }
}
