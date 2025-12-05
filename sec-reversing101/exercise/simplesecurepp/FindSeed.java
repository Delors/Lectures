/*
 * Java Script to find the seed for decrypting simplececurepp files.
 *
 * (I.e. use: "cat <EncryptedFile> | java FindSeed.java".)
 */
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

private static final byte[] CC = "simplesecurepp".getBytes(Charset.forName("ascii"));

private static final long ADLER32_MAX = ((long) Integer.MAX_VALUE) * 2;

/**
 * Reads in an encrypted file from stdin and tries to find the seed that
 * was used for the SecureRandom generator. Given the seed, it is then
 * possible to easily decrypt the stream.
 *
 * If we are not lucky, it may take several hours on a multi-core machine.
 */
void main() throws Exception {
    /*
     * We don't need the password, because as soon as we have identified the
     * seed everyting is Ok and we can efficiently check if the seed is ok.
     */
    var encryptedCC = System.in.readNBytes(CC.length);
    var availableProcessors = Runtime.getRuntime().availableProcessors();
    System.out.println(
        "Trying to find the seed for decryption using " +
            availableProcessors +
            " threads (= #Processors)."
    );

    for (int p = 0; p < availableProcessors; p++) {
        final var fp = p;
        var t = new Thread() {
            final byte[] decryptedHeader = new byte[CC.length];

            public void run() {
                final var START_TIME = System.currentTimeMillis();
                final long SEG_SIZE = ADLER32_MAX / availableProcessors;
                final long START = fp * SEG_SIZE;
                final long END = (fp + 1) * SEG_SIZE;
                for (long s = START; s < END; s++) {
                    SecureRandom secureRandom;
                    try {
                        secureRandom = SecureRandom.getInstance("SHA1PRNG");
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    secureRandom.setSeed(BigInteger.valueOf(s).toByteArray());

                    for (int i = 0; i < CC.length; i++) {
                        decryptedHeader[i] = (byte) (encryptedCC[i] ^
                            secureRandom.nextInt());
                    }
                    if (Arrays.equals(CC, decryptedHeader)) {
                        System.out.println("The seed: " + s);
                        System.exit(1);
                    }

                    if (s % 5000000 == 0) {
                        var speed =
                            (s - START) / (System.currentTimeMillis() - START_TIME);
                        System.out.println(
                            "[" +
                                fp +
                                "] Tested " +
                                (100l * (s - START)) / SEG_SIZE +
                                "% (" +
                                speed +
                                "H/ms): " +
                                START +
                                "=>" +
                                s
                        );
                    }
                }
            }
        };
        t.start();
    }
}
