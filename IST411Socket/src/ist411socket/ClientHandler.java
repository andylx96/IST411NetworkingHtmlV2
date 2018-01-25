/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author ajl5735
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    HelloWorldView helloView = new HelloWorldView();
    AddressView addressView = new AddressView();
    AddressModel user;
    ThankYouView tyView = new ThankYouView();
    HashMap mapPath = null;
    String[] parts;
    ListView listView = new ListView();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("\nClientHandler Started for "
                + this.socket);
        handleRequest(this.socket);
        System.out.println("ClientHandler Terminated for "
                + this.socket + "\n");
    }

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

                    System.out.println("Path received");
                    out.write("HTTP/1.1 200 OK\n\n");
                    out.write(listView.getHtml("test.csv"));
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

//                    System.out.println("M A P " + mapPath.get("name").toString());
//                     System.out.println("M A P " + mapPath.get("street").toString());
//                     System.out.println("M A P " + mapPath.get("zip").toString());
//                     System.out.println("M A P " + mapPath.get("state").toString());
//                     System.out.println("MAP split Attemp "+ mapPath.get("street").toString());
                    user = new AddressModel();
                    if (mapPath.get("name") != null) {

                        if (mapPath.get("name").toString().contains("+")) {
                            user.setName(mapPath.get("name").toString().replaceAll("\\+", ","));
                        } 
                        else if (mapPath.get("name").toString().contains("\\%A0")) {
                            user.setName(mapPath.get("name").toString().replaceAll("\\%A0", ","));
                        }
                        else {
                            user.setName(mapPath.get("name").toString());

                        }

//                        user.setName(mapPath.get("name").toString());
                    }
                    if (mapPath.get("state") != null) {
//                         
//
//                        System.out.println("parts 1 " + parts[0]);
//                        System.out.println("parts 2 " + parts[1]);
//                   
                        user.setState(mapPath.get("state").toString());
                    }
                    if (mapPath.get("zip") != null) {
                        user.setZip(Integer.parseInt(mapPath.get("zip").toString()));
                    }
                    if (mapPath.get("street") != null) {

//                        parts = mapPath.get("street").toString().split("\\+");
                        if (mapPath.get("street").toString().contains("+")) {
                            user.setStreet(mapPath.get("street").toString().replaceAll("\\+", ","));
                        }else if (mapPath.get("street").toString().contains("%A0")) {
                            user.setStreet(mapPath.get("street").toString().replaceAll("\\%A0", ","));
                        } else {
                            user.setStreet(mapPath.get("street").toString());

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

//
                        FileWriter fout = new FileWriter("test.csv", true);
//fout.write("Name, Street, State, ZIP\n");
                        fout.write(user.getName() + "," + user.getStreet() + "," + user.getState() + "," + user.getZip() + "\n");
                        fout.close();
//fout.flush();

//                         PrintWriter pw = new PrintWriter(new File("test.csv"));
//        StringBuilder sb = new StringBuilder();
//        sb.append("Name");
//        sb.append(',');       
//        sb.append("Street");
//        sb.append(',');      
//        sb.append("State");
//        sb.append(',');      
//        sb.append("ZIP");
//        sb.append('\n');
//
//            sb.append(user.getName());
//        sb.append(',');       
//        sb.append(user.getStreet());
//        sb.append(',');      
//        sb.append(user.getState());
//        sb.append(',');      
//        sb.append(user.getZip());
//        sb.append('\n');
//        pw.write(sb.toString());
//        pw.close();
//        System.out.println("done!");
//                        
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
                    out.write("HTTP/1.1 404 Not Found\n\n");
//                System.out.println("The HTTP method is not recognized");
//                        helloView.sendResponse(socket, 404, "404 not Found---");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//    public void handleRequest(Socket socket) {
//        try (BufferedReader in = new BufferedReader(
//                new InputStreamReader(socket.getInputStream()));
//                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
//            String headerLine = in.readLine();
//            StringTokenizer tokenizer
//                    = new StringTokenizer(headerLine);
//            String httpMethod = tokenizer.nextToken();
//
//            
//            
//                if (httpMethod.equals("GET")) {
//                    System.out.println("Get method processed");
//                    String path = tokenizer.nextToken();
//                    System.out.println("Path is: " + path);
//
//                    if (path.equals("/hello")) {
////                        helloView.sendResponse(socket, 200, helloView.getHtml());
//
//                        System.out.println("Path received");
//                        out.write("HTTP/1.1 200 OK\n\n");
//                        out.write(helloView.getHtml());
//                        out.write("\n");
////                        System.out.println(helloView.getHtml());
////                sendResponse(socket, 405, "Method Not Allowed");
//                    } //                System.out.println("Get method processed");
//                    //                String httpQueryString = tokenizer.nextToken();
//                    ////                String pagecode = tokenizer.nextToken();
//                    //W
//                    //                StringBuilder responseBuffer = new StringBuilder();
//                    //                responseBuffer
//                    //                        .append("hello world");
//                    //                sendResponse(socket, 200, responseBuffer.toString());
//                    //
//                    //                System.out.println("Http Method: " + httpMethod);
//                    //                System.out.println("Http Query:  " + httpQueryString);
//                    //
//                    //
//                    //                while(in.ready()){
//                    //                System.out.println(in.readLine());  
//                    //                        
//                    //                }
//                    ////                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//                    //////
//                    ////                String inputLine;
//                    ////                while ((inputLine = in.readLine()) != null) {
//                    ////                    System.out.println("Server: " + inputLine);
//                    ////                    out.println(inputLine.toUpperCase());
//                    ////
//                    ////                }
//                    else if (path.equals("/address")) {
////                        addressView.sendResponse(socket, 200, addressView.getHtml());
//
//                        System.out.println("Path received");
//                        out.write("HTTP/1.1 200 OK\n\n");
//                        out.write(addressView.getHtml());
//                        out.write("\n");
//
////                sendResponse(socket, 405, "Method Not Allowed");
//                    } //   
//                    else if (path.contains("/submit")) {
//
//                        System.out.println("Submit Recv");
//                        HashMap mapPath = null;
//                        if (path.indexOf("?") >= 0) {
//                            String params = path.substring(path.indexOf("?") + 1);
//                            mapPath = new HashMap<String, String>();
//                            for (String pair : params.split("&")) {
//                                String[] keyVal = pair.split("=");
//                                if (keyVal.length == 2) {
//                                    mapPath.put(keyVal[0], keyVal[1]);
//                                }
//                            }
//                        }
//
//                        System.out.println(mapPath.toString());
//
////                    System.out.println("M A P " + mapPath.get("name").toString());
////                     System.out.println("M A P " + mapPath.get("street").toString());
////                     System.out.println("M A P " + mapPath.get("zip").toString());
////                     System.out.println("M A P " + mapPath.get("state").toString());
////                     System.out.println("MAP split Attemp "+ mapPath.get("street").toString());
//                        if (mapPath.get("street").toString().contains("+")) {
//                            String[] parts = mapPath.get("street").toString().split("\\+");
//
////                            if(parts[0].getClass().isI)
//                            System.out.println("parts 1 " + parts[0]);
//                            System.out.println("parts 2 " + parts[1]);
//                            user = new AddressModel(mapPath.get("name").toString(), Integer.parseInt(parts[0]), parts[1], Integer.parseInt(mapPath.get("zip").toString()), mapPath.get("state").toString());
//
//                        } else {
//                            user = new AddressModel();
//                        }
//
//                        if (user.validate() == true) {
//                            System.out.println("User is valid");
//
////                            tyView.sendResponse(socket, 200, tyView.getHtml());
//                            out.write("HTTP/1.1 200 OK\n\n");
//                            out.write(tyView.getHtml());
//                            out.write("\n");
//                        } else {
//                            System.out.println("User is NOT valid");
//                        }
//
//                        user.printInfo();
//
//                    } else {
//                        System.out.println("404 Error");
//                        out.write("HTTP/1.1 404 Not Found\n\n");
////                System.out.println("The HTTP method is not recognized");
////                        helloView.sendResponse(socket, 404, "404 not Found---");
//                    }
//
//                }
//            }
//    }
////            out.close();
////            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        out.
//    }
//
////    public void sendResponse(Socket socket,
////            int statusCode, String responseString) {
////        String statusLine;
////        String serverHeader = "Server: WebServer\r\n";
////        String contentTypeHeader = "Content-Type: text/html\r\n";
////
////        try (DataOutputStream out
////                = new DataOutputStream(socket.getOutputStream());) {
////            System.out.println("Status Code :" + statusCode);
////            if (statusCode == 200) {
////                statusLine = "HTTP/1.0 200 OK" + "\r\n";
////                String contentLengthHeader = "Content-Length: "
////                        + responseString.length() + "\r\n";
////
//////                out.writeBytes("HTTP/1.0 200 OK\n\n");
//////                out.writeBytes("HTML");
//////                out.writeBytes("\n");
////                out.writeBytes(statusLine);
////                out.writeBytes(serverHeader);
////                out.writeBytes(contentTypeHeader);
////                out.writeBytes(contentLengthHeader);
////                out.writeBytes("\r\n");
////                out.writeBytes(responseString);
////
////            } else if (statusCode == 405) {
////                statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
////                out.writeBytes(statusLine);
////                out.writeBytes("\r\n");
////            } else {
////                statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
////                out.writeBytes(statusLine);
////                out.writeBytes("\r\n");
////            }
////
////            out.close();
////        } catch (IOException ex) {
////            // Handle exception
////        }
////    }
//}
