package br.com.soapboxrace.jlauncher;

import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.soapboxrace.jlauncher.swing.MainWindow;
import br.com.soapboxrace.jlauncher.util.ConfigDao;
import br.com.soapboxrace.jlauncher.util.CopyFiles;
import br.com.soapboxrace.jlauncher.util.Md5Files;
import br.com.soapboxrace.jlauncher.vo.ConfigVO;
import br.com.soapboxrace.jlauncher.vo.UserVO;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    
    private static ResourceBundle Text = ResourceBundle.getBundle("locales.locale", Locale.getDefault());

    private static ConfigDao configDao = new ConfigDao();

    public static void main(String[] args) {
        ConfigVO configVO = loadConfig();
        int p = configVO.getLocale();
        try{
        if (p == 1){
            Locale.setDefault(new Locale("ar"));
        } else if(p == 2){
            Locale.setDefault(new Locale("bn","IN"));
        } else if(p == 3){
            Locale.setDefault(new Locale("bg","BG"));
        } else if(p == 4){
            Locale.setDefault(new Locale("cs","CZ"));
        } else if(p == 5){
            Locale.setDefault(new Locale("de"));
        } else if(p == 6){
            Locale.setDefault(new Locale("en"));
        } else if(p == 7){
            Locale.setDefault(new Locale("es","ES"));
        } else if(p == 8){
            Locale.setDefault(new Locale("es","PY"));
        } else if(p == 9){
            Locale.setDefault(new Locale("fr"));
        } else if(p == 10){
            Locale.setDefault(new Locale("in","ID"));
        } else if(p == 11){
            Locale.setDefault(new Locale("it","IT"));
        } else if(p == 12){
            Locale.setDefault(new Locale("ja","JP"));
        } else if(p == 13){
            Locale.setDefault(new Locale("pt","BR"));
        } else if(p == 14){
            Locale.setDefault(new Locale("pt","PT"));
        } else if(p == 15){
            Locale.setDefault(new Locale("ro","RO"));
        } else if(p == 16){
            Locale.setDefault(new Locale("sl","SI"));
        } else if(p == 17){
            Locale.setDefault(new Locale("tr","TR"));
        }
        }catch(NullPointerException e){
        
        }
        new MainWindow().setVisible(true);
    }

    public static ConfigVO loadConfig() {
        return configDao.getConfig();
    }

    public static LoginCreate login(String url, String email, String password, boolean saveCredentials) {
        ConfigVO configVO = loadConfig();
        configVO.setServerURL("Latest server;" + url);
        configDao.saveConfig(configVO);
        UserVO userVO = new UserVO(email, password);
        if ("********************".equals(password)) {
            userVO.setShaPassword(configVO.getPasswordSHA1());
        }
        if (saveCredentials) {
            saveCredentials(email, userVO.getShaPassword());
        } else {
            emptyCredentials();
        }
        LoginCreate login = new LoginCreate(url, userVO);
        return login;
    }

    public static void saveGamePath(String gameExePath) {
        ConfigVO configVO = loadConfig();
        configVO.setGameExePath(gameExePath);
        configDao.saveConfig(configVO);
    }

    public static void saveCredentials(String email, String passwordSHA1) {
        ConfigVO configVO = loadConfig();
        configVO.setEmail(email);
        configVO.setPasswordSHA1(passwordSHA1);
        configVO.setSaveCredentials(true);
        configDao.saveConfig(configVO);
    }

    public static void emptyCredentials() {
        ConfigVO configVO = loadConfig();
        configVO.setEmail("");
        configVO.setPasswordSHA1("");
        configVO.setSaveCredentials(false);
        configDao.saveConfig(configVO);
    }

    public static void setSaveCredentials(boolean saveCredentials) {
        ConfigVO configVO = loadConfig();
        configVO.setSaveCredentials(saveCredentials);
        configDao.saveConfig(configVO);
    }

    public static LoginCreate create(String url, String email, String password) {
        ConfigVO configVO = loadConfig();
        configVO.setServerURL("Latest server;" + url);
        configDao.saveConfig(configVO);
        UserVO userVO = new UserVO(email, password);
        LoginCreate login = new LoginCreate(url, userVO);
        return login;
    }

    public static boolean checkGameMd5(String filename) {
        return Md5Files.checkExeFile(filename);
    }

    public static boolean copyModules(String path) {
        Path p = Paths.get(path);
        Path folder = p.getParent();
        String pathTo = folder.toString();
        CopyFiles.copyAllFiles(pathTo);
        return Md5Files.checkModules(pathTo);
    }

}
