import java.net.*;
import java.io.*;

class SyslogClient {
    public final static int DEFAULT_SERVER_PORT = 5678;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        final String hostname = "localhost";
        try (final var socket = new DatagramSocket();) {
            InetAddress host = InetAddress.getByName(hostname);
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("[INFO] SyslogClient: type message to send or <CTRL + d> for exit.");
            do {
                System.out.print("> "); // user prompt
                String s = userIn.readLine();
                if (s == null)
                    break; // CTRL+d has been pressed
                byte[] data = s.getBytes();
                if (data.length > MAX_PACKET_SIZE)
                    System.err.println("Message too large.");
                DatagramPacket dp = new DatagramPacket(data, data.length, host, DEFAULT_SERVER_PORT);
                socket.send(dp);
            } while (true);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}