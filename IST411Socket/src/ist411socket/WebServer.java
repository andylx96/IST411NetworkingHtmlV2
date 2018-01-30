/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ajl5735
 */
public class WebServer {

    public static void main(String args[]) {
        new WebServer();
    }

    public WebServer() {
        ClientHandler ch = null;
        System.out.println("Webserver Started");
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                System.out.println("Waiting for client request");
                Socket remote = serverSocket.accept();
                System.out.println("Connection made");
//                new Thread(new ClientHandler(remote)).start();
//                if (ch == null) {                    
                ch = new ClientHandler(remote);
//                    System.out.println("Creating CH");
//                }
//                ch.handleRequest(remote);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ist411socket;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.ByteBuffer;
//import java.nio.channels.Channels;
//import java.nio.channels.ReadableByteChannel;
//
///**
// *
// * @author ajl5735
// */
//public class WebServer {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
////////        WebServer webserver = new WebServer();
////////        // TODO code application logic here
////////        System.out.println("Simple Echo Server");
//////        System.out.println("Waiting for connection.....");
//////
//////        try (ServerSocket serverSocket = new ServerSocket(12345)) {
//////            while (true) {
//////                
//////            
////////                Socket clent = 
//////                Socket clientSocket = serverSocket.accept();
//////                System.out.println(serverSocket.accept());
//////                System.out.println("Connected to client2");
//////
//////                 new Thread(new ClientHandler(clientSocket)).start();
//////                 
//////                 
////////                 ClientHandler clientHandler = new ClientHandler(clientSocket);
////////                         System.out.println("\nClientHandler Started for "
////////                + clientSocket);
////////        clientHandler.handleRequest(clientSocket);
////////        System.out.println("ClientHandler Terminated for "
////////                + clientSocket + "\n");
//////                
////////                InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
////////                BufferedReader br = new BufferedReader(isr);
////////
////////                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
////////
////////                String inputLine;
////////                while ((inputLine = br.readLine()) != null) {
////////                    System.out.println("Server: " + inputLine);
////////                    out.println(inputLine.toUpperCase());
////////
////////                }
////////                System.out.println("Waiting for connection...");
////
////
////            }
////        } catch (IOException ex) {
////            // Handle exceptions
////        }
//new WebServer();
//    }
//
//    public WebServer(){
//    
////        WebServer webserver = new WebServer();
////        // TODO code application logic here
////        System.out.println("Simple Echo Server");
//        System.out.println("Waiting for connection.....");
//
//        try (ServerSocket serverSocket = new ServerSocket(12345)) {
//            while (true) {
//                
//            
////                Socket clent = 
//                Socket clientSocket = serverSocket.accept();
//                System.out.println(serverSocket.accept());
//                System.out.println("Connected to client2");
//
//                 new Thread(new ClientHandler(clientSocket)).start();
//                 
//                 
////                 ClientHandler clientHandler = new ClientHandler(clientSocket);
////                         System.out.println("\nClientHandler Started for "
////                + clientSocket);
////        clientHandler.handleRequest(clientSocket);
////        System.out.println("ClientHandler Terminated for "
////                + clientSocket + "\n");
//                
////                InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
////                BufferedReader br = new BufferedReader(isr);
////
////                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
////
////                String inputLine;
////                while ((inputLine = br.readLine()) != null) {
////                    System.out.println("Server: " + inputLine);
////                    out.println(inputLine.toUpperCase());
////
////                }
////                System.out.println("Waiting for connection...");
//
//
//            }
//        } catch (IOException ex) {
//            // Handle exceptions
//        }
//
//    }
//}
//
//
