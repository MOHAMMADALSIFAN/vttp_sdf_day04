import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void receiveFile(Socket socket) throws IOException{
        
        //create input stream to receive data from client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        //create a file to store the received data
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("received_from_client.txt"));

        String line;
        while ((line = reader.readLine()) != null) {
            fileWriter.write(line);
            fileWriter.newline();
            
        }

    
    }








    public static void main(String[] args) throws IOException {
        
      // Creating a server socket and waiting or a client connection
      ServerSocket server = new ServerSocket(5000);
      System.out.println("Waiting for connection on port 5000 ");

      // Accept client connection
      Socket socket = server.accept();
      System.out.println("Client successfully connected to port 5000 ");

      //Receiving file 
      receiveFile(socket);
      

      // close 
      server.close();
       
    }
    
}
