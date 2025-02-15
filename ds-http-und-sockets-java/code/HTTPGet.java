import java.net.*;
import java.io.*;

public class HTTPGet {
    public static void getFile(URL url) {
        int c;
        FileOutputStream f = null;
        System.err.println("Connecting to " + url.getHost());
        try (Socket s = new Socket(url.getHost(), 80); // connect to server
                var in = new BufferedInputStream(s.getInputStream());
                var out = new PrintWriter(s.getOutputStream());) {
            int pos = url.getFile().lastIndexOf("/");
            System.err.println("-> new file: " + url.getFile().substring(pos + 1));
            f = new FileOutputStream(url.getFile().substring(pos + 1));
            System.err.print("[TRACE] get " + url);
            out.print("GET " + url.getFile() + " HTTP/1.1\r\n");
            out.print("HOST: " + url.getHost()+ "\r\n");
            out.print("Connection: close"+ "\r\n");
            out.print("\r\n");
            out.flush();
            // skip HTTP/1.x header data up to ’CR LF CR LF’
            while (true) {
                if (in.read() == 13) // CR
                    if (in.read() == 10) // LF
                        if (in.read() == 13) // CR
                            if (in.read() == 10) { // LF
                                System.err.println("... removing meta data ");
                                break; // CRLF CRLF found; content follows
                            }
            }
            while ((c = in.read()) != -1) {
                f.write(c); // store data into local file
                System.err.print((char) c);
            }
            f.close();
            System.err.println(" ... done.");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Downloads a file from a given URL. (Example: "java HTTPGet.java http://www.google.de/index.html")
     * 
     * @param args URL of the file to be downloaded. E.g.,
     *             "http://archive.org/web/web.php".
     *              
     */
    public static void main(String args[]) {
        try {
            if (args.length < 1) {
                System.err.println("[ERROR] URL missing.");
                System.out.println("java HttpGet.java <url>");
                System.exit(-1);
            } else {
                URL myUrl = URI.create(args[0]).toURL();
                getFile(myUrl);
            }
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e);
            System.exit(-2);
        }
    }
}
