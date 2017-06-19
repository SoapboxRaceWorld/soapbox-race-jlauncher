package br.com.soapboxrace.jlauncher.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Md5Files {

    private static final byte[] md5ExeFile = { //
        (byte) 0x4c, (byte) 0x32, (byte) 0x93, (byte) 0x6d, //
        (byte) 0xeb, (byte) 0xff, (byte) 0xcb, (byte) 0xdc, //
        (byte) 0x20, (byte) 0x8d, (byte) 0x39, (byte) 0x50, //
        (byte) 0x2b, (byte) 0x79, (byte) 0xa5, (byte) 0x6f};

    private static HashMap<String, byte[]> filesToCheck = new HashMap<>();

    public static boolean checkExeFile(String filename) {
        Path p = Paths.get(filename);
        Path fileNameExe = p.getFileName();
        if (!"nfsw.exe".equals(fileNameExe.toString())) {
            return false;
        }
        try {
            byte[] createChecksum = createChecksum(filename);
            if (Arrays.equals(md5ExeFile, createChecksum)) {
                return true;
            }
            System.out.println(filename + " " + byteArrayToHexString(createChecksum));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static byte[] createChecksum(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    private static void setFiles() {
        byte[] lightfx = { //
            (byte) 0x66, (byte) 0xb3, (byte) 0xaf, (byte) 0x90, //
            (byte) 0xc1, (byte) 0x23, (byte) 0x59, (byte) 0xe4, //
            (byte) 0x0d, (byte) 0x0a, (byte) 0xee, (byte) 0x93, //
            (byte) 0xd2, (byte) 0xee, (byte) 0xde, (byte) 0x74};
        filesToCheck.put("lightfx.dll", lightfx);
        byte[] udpcrc = { //
            (byte) 0x9b, (byte) 0x6d, (byte) 0xab, (byte) 0xbc, //
            (byte) 0x9e, (byte) 0x35, (byte) 0x1e, (byte) 0x4c, //
            (byte) 0xe4, (byte) 0x29, (byte) 0x62, (byte) 0x84, //
            (byte) 0x42, (byte) 0xae, (byte) 0xca, (byte) 0xef};
        filesToCheck.put("modules/udpcrc.soapbox.module", udpcrc);

        byte[] udpcrypt1 = { //
            (byte) 0xd3, (byte) 0xd8, (byte) 0x05, (byte) 0x96, //
            (byte) 0x1e, (byte) 0xbf, (byte) 0x2b, (byte) 0xb5, //
            (byte) 0x78, (byte) 0x9a, (byte) 0x24, (byte) 0x00, //
            (byte) 0x68, (byte) 0x30, (byte) 0xd1, (byte) 0x70};
        filesToCheck.put("modules/udpcrypt1.soapbox.module", udpcrypt1);

        byte[] udpcrypt2 = { //
            (byte) 0xfd, (byte) 0x3c, (byte) 0x55, (byte) 0x47, //
            (byte) 0x97, (byte) 0xaa, (byte) 0x6e, (byte) 0xb6, //
            (byte) 0x3a, (byte) 0x8f, (byte) 0xd9, (byte) 0xbc, //
            (byte) 0xcf, (byte) 0x2e, (byte) 0x4b, (byte) 0x36};
        filesToCheck.put("modules/udpcrypt2.soapbox.module", udpcrypt2);

        byte[] xmppsubject = { //
            (byte) 0xbc, (byte) 0x1a, (byte) 0x32, (byte) 0x16, //
            (byte) 0xcb, (byte) 0xd5, (byte) 0x1f, (byte) 0x64, //
            (byte) 0x69, (byte) 0xf7, (byte) 0x13, (byte) 0x2d, //
            (byte) 0x71, (byte) 0x1a, (byte) 0xfa, (byte) 0x9f};
        filesToCheck.put("modules/xmppsubject.soapbox.module", xmppsubject);
    }

    public static boolean checkModules(String path) {
        setFiles();
        Iterator<Entry<String, byte[]>> iterator = filesToCheck.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, byte[]> next = iterator.next();
            String filename = path.concat("/".concat(next.getKey()));
            try {
                byte[] createChecksum = createChecksum(filename);
                if (!Arrays.equals(next.getValue(), createChecksum)) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static String byteArrayToHexString(byte[] b) {
        int len = b.length;
        String data = new String();
        for (int i = 0; i < len; i++) {
            data += Integer.toHexString((b[i] >> 4) & 0xf);
            data += Integer.toHexString(b[i] & 0xf);
            // data += ":";
        }
        return data;
    }
}
