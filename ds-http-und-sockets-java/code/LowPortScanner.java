import java.net.*;
import java.io.*;

public class LowPortScanner {
  public static void main(String [] args) {
    String host = "localhost";
    if (args.length > 0) { host = args [0]; }
    for (int i = 1; i < 1024; i++) {
      try {
        Socket s = new Socket(host, i);
        System.out.println("There is a server on port "+ i + " at "+host);
        s.close();
      } catch (UnknownHostException e) {
        System.err.println(e);
        break ;
      }
      catch (IOException e) {/* probably no server waiting at this port */ }
} } }