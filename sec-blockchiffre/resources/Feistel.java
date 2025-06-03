import static java.lang.System.arraycopy;
import static java.lang.System.out;

final int BLOCK_SIZE_IN_BYTE = 16; // 128Bit

byte[] swap(byte[] data) {
    var newData = data.clone();
    arraycopy(data, BLOCK_SIZE_IN_BYTE / 2, newData, 0, BLOCK_SIZE_IN_BYTE / 2);
    arraycopy(data, 0, newData, BLOCK_SIZE_IN_BYTE / 2, BLOCK_SIZE_IN_BYTE / 2);
    return newData;
}

void printAsHex(byte[] data) {
    for (int i = 0; i < data.length; i++) {
        out.printf("%02x ", data[i]);
        if (i == 7) out.print(" ");
    }
    out.println(new String(data));
}

void f(byte[] r) {
    // a very simple in-place rotation!
    byte b = r[0];
    arraycopy(r, 1, r, 0, BLOCK_SIZE_IN_BYTE / 2 - 1);
    r[BLOCK_SIZE_IN_BYTE / 2 - 1] = b;
}

byte[] round(byte[] data) {
    var l = Arrays.copyOfRange(data, 0, BLOCK_SIZE_IN_BYTE / 2);
    var r = Arrays.copyOfRange(data, BLOCK_SIZE_IN_BYTE / 2, BLOCK_SIZE_IN_BYTE);
    var newData = Arrays.copyOf(r, BLOCK_SIZE_IN_BYTE);

    f(r);

    for (int i = 0; i < BLOCK_SIZE_IN_BYTE / 2; i++) {
        r[i] = (byte) (r[i] ^ l[i]);
    }

    arraycopy(r, 0, newData, BLOCK_SIZE_IN_BYTE / 2, BLOCK_SIZE_IN_BYTE / 2);
    return newData;
}

byte[] encrypt(byte[] data) {
    assert data.length == BLOCK_SIZE_IN_BYTE;

    for (int i = 0; i < 16; i++) {
        data = round(data);
        out.printf("%02d: ", i);
        printAsHex(data);
    }
    return swap(data);
}

void main() throws Exception {
    var data = "hello world!!!!!".getBytes("utf-8");

    println("Encryption");
    print("    ");
    printAsHex(data);
    data = encrypt(data);
    print("    ");
    printAsHex(data);

    // So far we have no key schedule so we can simply
    // call the encrypt function again to decrypt the
    // message!
    println("Decryption");
    data = encrypt(data);
    print("    ");
    printAsHex(data);
}
