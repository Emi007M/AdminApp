/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.model;

/**
 *
 * @author Emilia
 */

import java.io.BufferedReader;
 import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializable.model.Competition;
import serializable.model.Message;
import serializable.model.Tournament;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class SocketServer implements Runnable{
    
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;
    

    
    
    @Override
    public void run() {
        try {
            this.runServer();
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void runServer() throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Server started");
            
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            
            //read message from socket
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            System.out.println("Message Received");
            
       
            //prepare answer
            Message answer = new Message();
            if(message.getTopic().equals(Message.Topic.AskForTournament)){
                System.out.println("asking for tournament. sending tournament.");
                answer.sendTournament(CurrentTournament.getTournament()); 
            }
            else if(message.getTopic().equals(Message.Topic.SendCompetition)){
                Competition c =(Competition) message.getObject();               
                CurrentTournament.updateCompetition(c);
                System.out.println("received competition. sending tournament.");
                answer.sendTournament(CurrentTournament.getTournament()); 
            }          
            
            //send answer
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(answer);
            
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request
            if(1==0) break;
        }
    }


    
//    private static class Connection extends Thread {
//        private Socket socket;
//        private int clientNumber;
//
//        public Connection(Socket socket, int clientNumber) {
//            this.socket = socket;
//            this.clientNumber = clientNumber;
//          //  log("New connection with client# " + clientNumber + " at " + socket);
//        }
//
//        /**
//         * Services this thread's client by first sending the client a welcome
//         * message then repeatedly reading strings and sending back the
//         * capitalized version of the string.
//         */
//        public void run() {
//            InputStream is = null;
//            ObjectInputStream ois = null;
//            
//            OutputStream os = null;
//            ObjectOutputStream oos = null;
//            try {
//
//                // Decorate the streams so we can send characters
//                // and not just bytes.  Ensure output is flushed
//                // after every newline.
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(socket.getInputStream()));
//                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//                // Send a welcome message to the client.
//                out.println("Hello, you are client #" + clientNumber + ".");
//                out.println("Enter a line with only a period to quit\n");
//
//                // Get messages from the client, line by line; return them
//                // capitalized
//                while (true) {
//
//                    is = socket.getInputStream();
//                    ois = new ObjectInputStream(is);
//                    Message received = (Message) ois.readObject();
//                    if (received == null) {
//                        break;
//                    }
//                    System.out.println("client asked: ");
//
////                    os = socket.getOutputStream();
////                    oos = new ObjectOutputStream(os);
////                    Message toSend = new Message("object from client");
////                    oos.writeObject(toSend);
//                    //oos.flush();
//                    
//                }
//            } catch (IOException e) {
//          //      log("Error handling client# " + clientNumber + ": " + e);
//            } catch (ClassNotFoundException ex) {
//          //      Logger.getLogger(ContentsServer.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    oos.close();
//                    os.close();
//                    socket.close();
//                } catch (IOException e) {
//         //           log("Couldn't close a socket, what's going on?");
//                }
//        //        log("Connection with client# " + clientNumber + " closed");
//            }
//        }
//    }
//    

}
    