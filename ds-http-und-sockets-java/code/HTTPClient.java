import java.net.*;
import java.io.*;
public class HTTPClient {
  public static void main(String [] args){
    BufferedReader in = null ;
    PrintWriter out = null ;
    //String hostname = "archive.org";
    String hostname ="www.michael-eichberg.de";
    //String filename = "/index.html";
    String filename = "/acm.svg";
    try(Socket s = new Socket(hostname ,80) ;){
      
      in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      out = new PrintWriter(s.getOutputStream());
      out.print("GET "+ filename + " HTTP/1.1\r\n");
      out.print("Host: " + hostname+ "\r\n");
      //out.print("Connection: close"+ "\r\n");
      out.print("\r\n");
      out.flush();
      String line = null;
      while ((line = in.readLine()) != null){
        System.out.println (line);
      }
      
    } catch(Exception e){e.printStackTrace();}
  }
}