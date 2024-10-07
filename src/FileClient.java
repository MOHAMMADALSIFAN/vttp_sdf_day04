import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {

    public static void sendFile(Socket socket, String filename) throws IOException {
        
        // Create output stream to send data to the server
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        writer.write(filename);
        writer.newLine();
        
        File file = new File(filename);
        long fileSize = file.length();

         // Send the file size
         writer.write(String.valueOf(fileSize));
         writer.newLine();  // Send a new line to indicate end of file size

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

        createFile(fileName);
        
        //send the file to the server
        sendFile(socket, fileName);

        scanner.close();

    }

       // Method to create a file and write sample content
    private static void createFile(String fileName) {
        try {
        new File(fileName).createNewFile();
            System.out.println("File '" + fileName + "' created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating/writing to the file: " + e.getMessage());
        }
    }

}