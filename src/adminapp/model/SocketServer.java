package adminapp.model;

/**
 *
 * @author Emilia
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializable.model.Competition;
import serializable.model.Message;

public class SocketServer implements Runnable {

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

    public static void runServer() throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while (true) {
            System.out.println("Server listening");

            //creating socket and waiting for client connection
            Socket socket = server.accept();

            //read message from socket
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            System.out.println("Message Received");

            //prepare answer
            Message answer = new Message();
            if (message.getTopic().equals(Message.Topic.AskForTournament)) {
                System.out.println("asking for tournament. sending tournament.");
                answer.sendTournament(CurrentTournament.getTournament());
            } else if (message.getTopic().equals(Message.Topic.SendCompetition)) {
                Competition c = (Competition) message.getObject();
                CurrentTournament.updateCompetition(c);
                CurrentTournament.getTournament().saveToFile();
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
            if (1 == 0) {
                break;
            }
        }
    }
  
}
