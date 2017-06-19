package br.com.soapboxrace.jlauncher.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFiles {

    public static void createDir(String dirPath) {
        new File(dirPath).mkdir();
    }

    public static void copyFile(String fileFrom, String pathTo) {
        File f = new File(pathTo);
        if (!f.exists()) {
            ClassLoader classLoader = CopyFiles.class.getClassLoader();
            InputStream originalFile = classLoader.getResourceAsStream(fileFrom);
            try (OutputStream outputStream = new FileOutputStream(new File(pathTo))) {
                int read;
                byte[] bytes = new byte[1024];
                while ((read = originalFile.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteFile(String path) {
        File file = new File(path);
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public static void copyAllFiles(String pathTo) {
        String lightFx = pathTo.concat("/lightfx.dll");
        deleteFile(lightFx);
        copyFile("lightfx.dll", lightFx);
        createDir(pathTo.concat("/modules"));
        String[] modules = new String[4];
        modules[0] = "udpcrc.soapbox.module";
        modules[1] = "udpcrypt1.soapbox.module";
        modules[2] = "udpcrypt2.soapbox.module";
        modules[3] = "xmppsubject.soapbox.module";
        for (String module : modules) {
            String fileDest = pathTo.concat("/modules/".concat(module));
            deleteFile(fileDest);
            copyFile(module, fileDest);
        }
    }
}
