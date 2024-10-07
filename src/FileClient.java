import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {

    public static void sendFile(Socket socket, String filename) throws IOException {
        
        // Create output stream to send data to the server
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // open the file to read its contents
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));

        // read and send the file by line to the server 
        String line;
        while ((line = fileReader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
            
        }

    
            
        
        fileReader.close();
        writer.flush();
        writer.close();
        socket.close();

        System.out.println(" File sent to server. ");
    }
        

    


    public static void main(String[] args) throws IOException{
        //connecting to the server
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to the server ");

        //ask the user to input the file name from console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the file to send: ");
        String fileName = scanner.nextLine();
        scanner.close();
        
        //send the file to the server
        sendFile(socket, fileName);

    }
}