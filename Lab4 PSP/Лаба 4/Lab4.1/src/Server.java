import java.io.*;
import java.net.*;

public class Server
{
    public static void main(String[] arg) {

        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;
        ObjectInputStream  sois   = null;
        ObjectOutputStream soos   = null;

        try {
            System.out.println("server starting....");

            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();

            System.out.println("connection established....");

            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            String clientMessageRecieved = (String)sois.readObject();





            while(!clientMessageRecieved.equals("quite"))
            {
                System.out.println("message recieved: '"+clientMessageRecieved+"'");

                float[][] mas = new float[3][3];
                char[] charArray = clientMessageRecieved.toCharArray();
                int rows = 0, col = 0;
                // Распечатать элементы массива
                for (char c : charArray) {
                    if (col < 2){
                        mas[rows][col] = Float.parseFloat(String.valueOf(c));
                        col += 1;
                    }
                    else {
                        mas[rows][col] = Float.parseFloat(String.valueOf(c));
                        col = 0;
                        rows += 1;
                    }
                }
                /*----------------------------*/
                double temp;

                float [][] E = new float [3][3];


                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                    {
                        E[i][j] = 0f;

                        if (i == j)
                            E[i][j] = 1f;
                    }

                for (int k = 0; k < 3; k++)
                {
                    temp = mas[k][k];

                    for (int j = 0; j < 3; j++)
                    {
                        mas[k][j] /= temp;
                        E[k][j] /= temp;
                    }

                    for (int i = k + 1; i < 3; i++)
                    {
                        temp = mas[i][k];

                        for (int j = 0; j < 3; j++)
                        {
                            mas[i][j] -= mas[k][j] * temp;
                            E[i][j] -= E[k][j] * temp;
                        }
                    }
                }

                for (int k = 3 - 1; k > 0; k--)
                {
                    for (int i = k - 1; i >= 0; i--)
                    {
                        temp = mas[i][k];

                        for (int j = 0; j < 3; j++)
                        {
                            mas[i][j] -= mas[k][j] * temp;
                            E[i][j] -= E[k][j] * temp;
                        }
                    }
                }

                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        mas[i][j] = E[i][j];
                /*----------------------------*/

                clientMessageRecieved = "";
                for (int r = 0; r < mas.length; r++) {
                    for (int c = 0; c < mas.length; c++) {
                        clientMessageRecieved += String.valueOf(mas[r][c]) + " ";
                    }
                    clientMessageRecieved += "\n";
                }
                soos.writeObject(clientMessageRecieved);

                clientMessageRecieved = (String)sois.readObject();
            }   }catch(Exception e)	{
        } finally {
            try {
                sois.close();
                soos.close();
                clientAccepted.close();
                serverSocket.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
