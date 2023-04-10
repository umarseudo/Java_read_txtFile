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
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
//            writer.write("hello appvalley");
//            writer.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("Input20220719_1.txt"));
//            System.out.println(reader.readLine());
//            reader.close();
//            
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
//            Scanner read = new Scanner (new File("Input20220719_1.txt"));
//            read.useDelimiter("\\|");
//            String Date, CaseID, Image_Files,Last;
//            int a,b,c,lineNr=1;
//            while(read.hasNext() && lineNr <= 100)
//                {
//                Date = read.next();
//                if(Date.equals(" ")){
//                    a=0;
//                    System.out.println("Missing Case ID"); //just for debugging
//                }
//                else {
//                    a=1;
//                }
//                CaseID = read.next();
//                if(CaseID.equals(" ")){
//                    System.out.println("CaseID IS EMPTY"); //just for debugging
//                    b=0;
//                    }
//                else {
//                    b=1;
//                }
//                Image_Files = read.next();
//                if(Image_Files.equals(" ")){
//                    c=0;
//                    System.out.println("Image_Files IS EMPTY"); //just for debugging
//                    }
//                else{
//                    c=1;
//                }
////                   String line = read.next();
////                    if(!line.isEmpty()) {
////                        System.out.println("line tiada"); //just for debugging
////                    }
//                lineNr++;
//                System.out.println(Date + "|" + CaseID + "|" + Image_Files + "|" +"\n"); //just for debugging
//                writer.append(Date + "|" + CaseID + "|" + Image_Files + "\n");
//                
//               }
//               read.close();
//               writer.close();
//        } catch (IOException e){
//            System.out.println("TEST FAILED LA");
//            e.printStackTrace();
//        }

//
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
                    BufferedWriter writer = new BufferedWriter(new FileWriter("output/"+FileName.substring(0,FileName.length() - 4)+"_Failed.txt"));
                    BufferedWriter writer_SUCCESS = new BufferedWriter(new FileWriter("output/output_SUCCESS.txt"));
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
                                i = i+1;
                                if(i==3){
                                    writer_SUCCESS.append(Date + "|" + CaseID + "|" + Image_Files + "|" + "PASSED" +"|" + formattedDateTime + "|" + "\n" + "\n");
                                }
//                                writer_SUCCESS.append(Date + "|" + CaseID + "|" + Image_Files + "|" + "PASSED" +"|" + formattedDateTime + "|" + "\n" + "\n");
//                                writer_SUCCESS.close();
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
                }catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
    
//    try{
//        BufferedReader reader = new BufferedReader(new FileReader("Input20220719_1.txt"));
//        String line;
//        int lineCount = 0;
//        while((line = reader.readLine()) != null){
//            String[] data = line.split("\\|");
//            boolean endsWithDelimiter = line.endsWith("|");
//            if (line.length() == 0){
//            
//            }
//            else{
//            
////            if (data.length > 0 && data[data.length - 1].isEmpty()) {
////                System.out.println("Last data after | is empty in file ");
////            }
//
//                if (data[0].length() == 0)
//                {
//                    Date = data[0];
//                }
//                else {
//                    Date = data[0];
//                }
//
//                if (data[1].length() == 0){
//                CaseID = "";
//                }
//                else{
//                CaseID = data[1];
//                }
//
//                if (endsWithDelimiter) {
//    //                System.out.println("The input ends with a delimiter '|'");
//                    Image_Files = "FAILED";
//                } else {
//    //                System.out.println("The input does not end with a delimiter '|'");
//                    Image_Files = data[2];
//                }
//
//                System.out.println(Date + "|" + CaseID + "|" + Image_Files + "|" + java.time.LocalDateTime.now() + "|" + "\n"); //just for debugging
//            }
//        }
//        reader.close();
//        
//    } catch (IOException e){
//        System.out.println("TEST FAILED LA");
//        e.printStackTrace();
//    }
    }
}
