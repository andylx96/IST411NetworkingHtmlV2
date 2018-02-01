/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ajl5735
 */
public class ClientHandler {

    private final Socket socket;
    HelloWorldView helloView = new HelloWorldView();
    AddressView addressView = new AddressView();
    AddressModel user;
    ThankYouView tyView = new ThankYouView();
    HashMap mapPath = null;
    String[] parts;
    AddressListView listView = new AddressListView();
    AddressListModel addressListModel;
    PublicView publicView = new PublicView();
    
    public ClientHandler(Socket socket) {
        this.socket = socket;
        System.out.println("\nClientHandler Started for "
                + this.socket);
        this.addressListModel = AddressListModel.makeAddressListFromFile("fileName.csv");
        handleRequest(this.socket);
        System.out.println("ClientHandler Terminated for "
                + this.socket + "\n");

    }

//    @Override
//    public void run() {
//        System.out.println("\nClientHandler Started for "
//                + this.socket);
//        handleRequest(this.socket);
//        System.out.println("ClientHandler Terminated for "
//                + this.socket + "\n");
//    }
    public void handleRequest(Socket socket) {

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
            String headerLine = in.readLine();
            StringTokenizer tokenizer
                    = new StringTokenizer(headerLine);
            String httpMethod = tokenizer.nextToken();

            if (httpMethod.equals("GET")) {
                System.out.println("Get method processed");
                String path = tokenizer.nextToken();
                System.out.println("Path is: " + path);

                if (path.equals("/hello")) {
//                        helloView.sendResponse(socket, 200, helloView.getHtml());

                    System.out.println("Path received");
                    out.write("HTTP/1.1 200 OK\n\n");
                    out.write(helloView.getHtml());
                    out.write("\n");

//                        System.out.println(helloView.getHtml());
//                sendResponse(socket, 405, "Method Not Allowed");
                } //                System.out.println("Get method processed");
                //                String httpQueryString = tokenizer.nextToken();
                ////                String pagecode = tokenizer.nextToken();
                //W
                //                StringBuilder responseBuffer = new StringBuilder();
                //                responseBuffer
                //                        .append("hello world");
                //                sendResponse(socket, 200, responseBuffer.toString());
                //
                //                System.out.println("Http Method: " + httpMethod);
                //                System.out.println("Http Query:  " + httpQueryString);
                //
                //
                //                while(in.ready()){
                //                System.out.println(in.readLine());  
                //                        
                //                }
                ////                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                //////
                ////                String inputLine;
                ////                while ((inputLine = in.readLine()) != null) {
                ////                    System.out.println("Server: " + inputLine);
                ////                    out.println(inputLine.toUpperCase());
                ////
                ////                }
                else if (path.equals("/address")) {
//                        addressView.sendResponse(socket, 200, addressView.getHtml());

                    System.out.println("Path received");
                    out.write("HTTP/1.1 200 OK\n\n");
                    out.write(addressView.getHtml());
                    out.write("\n");

//                sendResponse(socket, 405, "Method Not Allowed");
                } //   
                else if (path.equals("/list")) {
//                        addressView.sendResponse(socket, 200, addressView.getHtml());

//addressListModel.saveToFile("fileName");
                    System.out.println("Path received");
                    out.write("HTTP/1.1 200 OK\n\n");
                    out.write(listView.getHtml("fileName.csv"));
                    out.write("\n");

//                sendResponse(socket, 405, "Method Not Allowed");
                } //   
                else if (path.contains("/submit")) {

                    System.out.println("Submit Recv");
                    if (path.indexOf("?") >= 0) {
                        String params = path.substring(path.indexOf("?") + 1);
                        mapPath = new HashMap<String, String>();
                        for (String pair : params.split("&")) {
                            String[] keyVal = pair.split("=");
                            if (keyVal.length == 2) {
                                mapPath.put(keyVal[0], keyVal[1]);
                            }
                        }
                    }

                    System.out.println(mapPath.toString());

                    user = new AddressModel();
                    if (mapPath.get("name") != null) {

                        if (mapPath.get("name").toString().contains("+")) {
                            user.setName(mapPath.get("name").toString().replaceAll("\\+", " "));
                        } else {
                            user.setName(mapPath.get("name").toString());
                        }
                    }
                    if (mapPath.get("state") != null) {

                        user.setState(mapPath.get("state").toString());
                    }

                    if (mapPath.get("zip") != null) {
                        user.setZip(Integer.parseInt(mapPath.get("zip").toString()));
                    }

                    if (mapPath.get("street") != null) {

                        if (mapPath.get("street").toString().contains("+")) {
                            user.setStreet(mapPath.get("street").toString().replaceAll("\\+", " "));
                        } else {
                            user.setStreet(mapPath.get("street").toString());
//
                        }

                    }
//                        user = new AddressModel();
//                        if (mapPath.get("name")!= null) {
//                            user.setName(mapPath.get("name").toString());
//                        }if (mapPath.get("state") != null) {
//                            user.setState(mapPath.get("state").toString());
//                        }if (mapPath.get("zip") != null) {
//                            user.setZip(Integer.parseInt(mapPath.get("zip").toString()));
//                        }if (mapPath.get("street")!= null) {
//                            user.setStreetName(mapPath.get("street").toString());
//                        }

                    if (user.isValidate() == true) {
                        System.out.println("User is valid");
                        out.write("HTTP/1.1 200 OK\n\n");
                        out.write(tyView.getHtml());

                        out.write("\n");
                        addressListModel.getAddressArrayList().add(user);
                        System.out.println(user.serializeToString());
                        System.out.println(addressListModel.getAddressArrayList().size());
                        addressListModel.saveToFile("fileName");

                    } else {
                        System.out.println("User is NOT valid");

                        System.out.println("Path received");
                        out.write("HTTP/1.1 200 OK\n\n");

                        out.write(addressView.getHtml(mapPath
                        ));

                        out.write("\n");

                    }

                    user.printInfo();

                } else {
                    System.out.println("404 Error");
//                    out.write("HTTP/1.1 404 Not Found\n\n");
           
                    
                    if(checkFile(path)==true){
                                   out.write("HTTP/1.1 200 OK\n\n");
                    out.write(publicView.getHtml(path));
                    out.write("\n");
                    }else{
                    
                    
                    out.write("HTTP/1.1 404 Not Found\n\n");
                        System.out.println("FileNotFoundEither");
                    }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String fileName) {

        FileWriter fout;
        try {
            fout = new FileWriter("fileName.csv", false);

            for (int i = 0; i < addressListModel.getAddressArrayList().size(); i++) {

                fout.write(user.getName() + "," + user.getStreet() + "," + user.getState() + "," + user.getZip() + "\n");
                fout.close();
            }

        } catch (IOException ex) {
            System.out.println("Unable To Create File");
        }
    }

    static public boolean checkFile(String path) {

        File tmpDir = new File( path.replaceFirst("/", ""));
        boolean exists = tmpDir.exists();
        if (exists == true) {
            System.out.println("File Check is true at: "+path.replaceFirst("/", ""));

            return true;
        } else {
            System.out.println("FileCheck is False at: " + path.replaceFirst("/", ""));
            return false;
        }

    }
}
