import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] arg) {
        try {
            System.out.println("server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",2525);
            System.out.println("connection established....");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream  cois = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Enter 9 numbers of matrix(like 123456789) \n\t('quite' − programme terminate)");
            String clientMessage = stdin.readLine();
            System.out.println("you've entered: "+clientMessage); while(!clientMessage.equals("quite")) {
                coos.writeObject(clientMessage);
                System.out.println("~reversed matrix from server~:\n "+cois.readObject());
                System.out.println("---------------------------");
                clientMessage = stdin.readLine();
                System.out.println("you've entered: "+clientMessage);
            }
            coos.close();
            cois.close();
            clientSocket.close();
        }catch(Exception e)	{
            e.printStackTrace();
        }
    }
}
