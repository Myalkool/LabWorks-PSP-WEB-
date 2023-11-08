import java.net.*;
import java.io.*;
public class UDPServer {
    public final static int DEFAULT_PORT = 8001;
    public final String QUIT = "QUIT";
    public final byte[] UNKNOWN_CMD = { 'U', 'n', 'k', 'n', 'o', 'w', 'n', ' ',
            'c', 'o', 'm', 'm', 'a', 'n', 'd' };
    public void runServer() throws IOException {
        DatagramSocket s = null;
        try {
            boolean stopFlag = false;
            byte[] buf = new byte[512];
            s = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UDPServer: Started on " + s.getLocalAddress() + ":"
                    + s.getLocalPort());
            while(!stopFlag) {
                DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);
                s.receive(recvPacket);
                String mess = new String(recvPacket.getData()).trim();
                System.out.println("UDPServer: values: " + mess);
                DatagramPacket sendPacket = new DatagramPacket(buf, 0, recvPacket.getAddress(), recvPacket.getPort());//формирование объекта                                                                  // дейтаграммы для отсылки данных
                int n = 0;//количество байт в ответе


                if (isNumeric(mess.replaceAll(" ",""))) {
                    String[] str = mess.split(" ");
                    int x = Integer.parseInt(str[0]);
                    int y =Integer.parseInt(str[1]);
                    int z =Integer.parseInt(str[2]);

                    double value = Math.pow((Math.abs(Math.cos(x) - Math.exp(y))),(1+(2*Math.sqrt(Math.log(y)))))*(1+z+(Math.sqrt(z)/2)+(Math.cbrt(z)/3));
                    String val = Double.toString(value);
                    buf = val.getBytes();
                    n = buf.length;

                    try(FileWriter writer = new FileWriter("notes3.txt", false))
                    {
                        // запись всей строки
                        String text = "x = "+x +"\ny = "+y+"\nz = "+z + "\nsolution = "+val;
                        writer.write(text);

                        writer.flush();
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                else if (mess.equals(QUIT)) {
                    stopFlag = true;//остановка сервера
                    continue;
                }
                else {
                    n = UNKNOWN_CMD.length;
                    System.arraycopy(UNKNOWN_CMD, 0, buf, 0, n);
                }
                sendPacket.setData(buf);//установить массив посылаемых данных
                sendPacket.setLength(n);//установить длину посылаемых данных

                s.send(sendPacket);//послать сами данные
            } // while(server is not stopped)
            System.out.println("UDPServer: Stopped");
        }
        finally {
            if (s != null) {
                s.close();//закрытие сокета сервера
            }
        }
    }

    public static boolean isNumeric(String string) {
        int intValue;
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {}
        return false;
    }

    public static void main(String[] args) {//метод main
        try {
            UDPServer udpSvr = new UDPServer();//создание объекта udpSvr
            udpSvr.runServer();//вызов метода объекта runServer
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
