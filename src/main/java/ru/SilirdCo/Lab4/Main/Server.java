package ru.SilirdCo.Lab4.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static String SERVER_WAIT = "Server is waiting for connec-tion...";
    private final static String CLIENT_CONNECT = "Client connected to the server";
    private final static String AND = " and ";
    private final static String CLIENT_SEND = "Client sent these numbers: ";
    private final static String SERVER_SEND = "Server is sending the maximum number back...";
    private final static String SERVER_WAIT_NEW_DATA = "Server is wait-ing for new data...";
    private final static String CLOSE = "Connection with the client is closed";
    private final static int PORT = 6666;

    private static double square(double a) {
        return a*a;
    }

    private static double circle(double r) {
        return Math.PI*r*r;
    }

    private static double perimeter(double a, double b, double c, double d) {
        return a + b + c + d;
    }

    private static double volume(double r, double h) {
        return h*circle(r);
    }

    public static void main(String[] ar) {
        Server s = new Server();
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println(SERVER_WAIT);

            boolean cont = true;

            while (cont) {
                Socket socket = ss.accept();
                System.out.println(CLIENT_CONNECT);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                int id = in.readInt();

                switch (id) {
                    case 1:
                        double a1;

                        double result1;

                        a1 = in.readDouble();

                        result1 = square(a1);
                        out.writeDouble(result1);
                        out.flush();

                        cont = true;
                        break;
                    case 2:
                        double r2;

                        double result2;

                        r2 = in.readDouble();

                        result2 = circle(r2);
                        out.writeDouble(result2);
                        out.flush();

                        cont = true;
                        break;
                    case 3:
                        double a3;
                        double b3;
                        double c3;
                        double d3;

                        double result3;

                        a3 = in.readDouble();
                        b3 = in.readDouble();
                        c3 = in.readDouble();
                        d3 = in.readDouble();

                        result3 = perimeter(a3, b3, c3, d3);
                        out.writeDouble(result3);
                        out.flush();

                        cont = true;
                        break;
                    case 4:
                        double r4;
                        double h4;

                        double result4;

                        r4 = in.readDouble();
                        h4 = in.readDouble();

                        result4 = volume(r4, h4);
                        out.writeDouble(result4);
                        out.flush();

                        cont = true;
                        break;
                    default:
                        cont = false;
                        break;
                }

                in.close();
                out.close();
                socket.close();
            }

            ss.close();
            System.out.println(CLOSE);
        }
        catch (Exception x) {
            x.printStackTrace();
        }
    }
}
