package de.dhbw.simplesecurepp;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.zip.Adler32;

public class Main {
   private static final byte[] CC = "simplesecurepp".getBytes(Charset.forName("ascii"));

   public static void main(String[] args) throws Exception {
      if (args.length != 2) {
         System.err.println("SimpleSecure++ - simple data encryption and decryption.");
         System.err.println("En/decrypts the data read from stdin and writes the result to stdout.");
         System.err.println(" 1 - the password ");
         System.err.println(" 2 - mode: encrypt or decrypt");
         System.err.println("Example usage:");
         System.err.println("\tjava de.dhbw.simplesecurepp.Main \"Password\" encrypt");
         System.err.println("DISCLAIMER:");
         System.err.println("\tSimpleSecure++ IS FOR TEACHING PURPOSES ONLY. IT HAS SIGNIFICANT SECURITY ISSUES!");
         System.exit(1);
      }

      SecureRandom secureRandom;
      byte[] buffer;
      int bytesRead;
      label58: {
         Adler32 adler32 = new Adler32();
         adler32.update(args[0].getBytes(Charset.forName("utf8")));
         secureRandom = SecureRandom.getInstance("SHA1PRNG");
         secureRandom.setSeed(BigInteger.valueOf(adler32.getValue()).toByteArray());
         String var3;
         switch((var3 = args[1]).hashCode()) {
         case -1607257499:
            if (var3.equals("encrypt")) {
               for(int i = 0; i < CC.length; ++i) {
                  byte[] var10000 = CC;
                  var10000[i] = (byte)(var10000[i] ^ secureRandom.nextInt());
               }

               System.out.write(CC);
               break label58;
            }
            break;
         case 1542543757:
            if (var3.equals("decrypt")) {
               buffer = System.in.readNBytes(CC.length);

               for(bytesRead = 0; bytesRead < CC.length; ++bytesRead) {
                  buffer[bytesRead] = (byte)(buffer[bytesRead] ^ secureRandom.nextInt());
               }

               if (!Arrays.equals(CC, buffer)) {
                  System.err.println("invalid password");
                  System.exit(1);
               }
               break label58;
            }
         }

         System.err.println("unknown modus: " + args[1]);
         System.exit(1);
      }

      buffer = new byte[1024];
      boolean var9 = false;

      while((bytesRead = System.in.read(buffer)) != -1) {
         byte[] randomBytes = new byte[bytesRead];
         secureRandom.nextBytes(randomBytes);

         for(int i = 0; i < bytesRead; ++i) {
            buffer[i] ^= randomBytes[i];
         }

         System.out.write(buffer, 0, bytesRead);
      }

   }
}
