import java.net.*;

public class SyslogServer {
    public final static int DEFAULT_PORT = 5678;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        try (
                var socket = new DatagramSocket(DEFAULT_PORT);) {
            System.out.println("∗∗∗ SyslogServer ***");
            while (true) {
                try {
                    byte[] buffer = new byte[MAX_PACKET_SIZE];
                    DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                    socket.receive(dp); // wait for new message
                    String s = new String(dp.getData(), 0, dp.getLength());
                    System.out.println("[" + dp.getAddress() +
                            ":" + dp.getPort() + "] " + s);
                } catch (Exception e) {
                    System.err.println(e);
                }
            } // while
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}