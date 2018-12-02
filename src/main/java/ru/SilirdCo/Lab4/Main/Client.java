package ru.SilirdCo.Lab4.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab4.Util.ExceptionHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private final static int PORT = 6666;
    private final static String ADDRESS = "127.0.0.1";
    private final static String CONNECTION_SUCCESSFUL = "Connection to the server was successful";
    private final static String FIRST_NUMBER = "Type the first number: ";
    private final static String SECOND_NUMBER = "Type the second number:";
    private final static String RESULT = "Результат: ";
    private final static String CONNECTION_CLOSE = "Connection with the server is closed";

    private static Scanner scanner;
    private static DataInputStream in;
    private static DataOutputStream out;

    private static boolean process(int id) {
        try {
            out.writeInt(id);
            switch (id) {
                case 1:
                    double a1;

                    double result1;

                    System.out.print("Введите сторону квадрата: ");
                    a1 = scanner.nextDouble();

                    System.out.println();
                    out.writeDouble(a1);
                    out.flush();

                    result1 = in.readDouble();
                    System.out.println(RESULT + result1);
                    System.out.println();
                    return true;
                case 2:
                    double r2;

                    double result2;

                    System.out.print("Введите радиус: ");
                    r2 = scanner.nextDouble();

                    System.out.println();
                    out.writeDouble(r2);
                    out.flush();

                    result2 = in.readDouble();
                    System.out.println(RESULT + result2);
                    System.out.println();
                    return true;
                case 3:
                    double a3;
                    double b3;
                    double c3;
                    double d3;

                    double result3;

                    System.out.print("Введите первую сторону: ");
                    a3 = scanner.nextDouble();
                    System.out.print("Введите вторую сторону: ");
                    b3 = scanner.nextDouble();
                    System.out.print("Введите третью сторону: ");
                    c3 = scanner.nextDouble();
                    System.out.print("Введите четвёртую сторону: ");
                    d3 = scanner.nextDouble();

                    System.out.println();
                    out.writeDouble(a3);
                    out.writeDouble(b3);
                    out.writeDouble(c3);
                    out.writeDouble(d3);
                    out.flush();

                    result3 = in.readDouble();
                    System.out.println(RESULT + result3);
                    System.out.println();
                    return true;
                case 4:
                    double r4;
                    double h4;

                    double result4;

                    System.out.print("Введите радиус: ");
                    r4 = scanner.nextDouble();
                    System.out.print("Введите высоту: ");
                    h4 = scanner.nextDouble();

                    System.out.println();
                    out.writeDouble(r4);
                    out.writeDouble(h4);
                    out.flush();

                    result4 = in.readDouble();
                    System.out.println(RESULT + result4);
                    System.out.println();
                    return true;
                default:
                    return false;
            }
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return true;
    }

    public static void main(String[] ar) {
        int serverPort = PORT;
        String address = ADDRESS;
        try {
            InetAddress ipAddress = InetAddress.getByName(address);

            scanner = new Scanner(System.in);

            boolean cont = true;
            while(cont) {
                System.out.print("Введите идентификатор операции\n" +
                        " [1] Расчёт площади квадрата по стороне\n" +
                        " [2] Расчёт площади круга по радиусу\n" +
                        " [3] Расчёт периметра трапеции по сторонам\n" +
                        " [4] Расчёт объёма цилиндра по радиусу и высоте\n");
                int id;

                System.out.print("Введите id: ");
                id = scanner.nextInt();

                Socket socket = new Socket(ipAddress, serverPort);
                System.out.println(CONNECTION_SUCCESSFUL);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                cont = process(id);

                in.close();
                out.close();
                socket.close();
                System.out.println(CONNECTION_CLOSE);
            }

        }
        catch (Exception x) {
            x.printStackTrace();
        }
    }
}
