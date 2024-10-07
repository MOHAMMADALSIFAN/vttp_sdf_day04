import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {

    
    public static void main(String[] args) throws IOException {
        int port = 5000;
        if (args.length > 0) 
        {
            port = Integer.parseInt(args[0]);
            
            System.out.printf(">> Listening on port %d " , port);

        } 

         ServerSocket server = new ServerSocket(port);

         //create the server

         System.out.println("\nWaiting for connection ");






        }
    }
