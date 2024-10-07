import java.io.*;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void receiveFile(Socket socket) throws IOException{
        
        //create input stream to receive data from client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        
        String fileName = reader.readLine();
        long fileSize = Long.parseLong(reader.readLine()); 
        

        System.out.println("Received File " + fileName + "----(size " + fileSize + " bytes ) ");

        //create a file to store the received data
        
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));

        String line;
        while ((line = reader.readLine()) != null) {
            fileWriter.write(line);
            fileWriter.newLine();

            
        }
        fileWriter.close();
        reader.close();
        socket.close();
        System.out.println("File received and saves as " + fileName + "'.");

    
    }



    public static void main(String[] args) throws IOException {
        
      // Creating a server socket and waiting or a client connection
      ServerSocket server = new ServerSocket(5000);
      System.out.println("Waiting for connection on port 5000... ");

      // Accept client connection
      Socket socket = server.accept();
      System.out.println("Client successfully connected to port 5000 ");

      //Receiving file 
      receiveFile(socket);
      

      // close 
      server.close();
       
    }
    
}
