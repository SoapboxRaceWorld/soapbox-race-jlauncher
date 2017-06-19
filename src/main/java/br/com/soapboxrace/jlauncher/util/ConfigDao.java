package br.com.soapboxrace.jlauncher.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.soapboxrace.jlauncher.vo.ConfigVO;

public class ConfigDao {

    public ConfigDao() {
        CopyFiles.copyFile("config.properties", "config.properties");
    }

    public ConfigVO getConfig() {
        ConfigVO configVO = new ConfigVO();
        Properties prop = new Properties();
        try (InputStream arquivoConfig = new FileInputStream(new File("config.properties"))) {
            prop.load(arquivoConfig);
            configVO.setEmail(prop.getProperty("email"));
            configVO.setGameExePath(prop.getProperty("gameExePath"));
            configVO.setPasswordSHA1(prop.getProperty("passwordSHA1"));
            Boolean saveCredentials = Boolean.valueOf(prop.getProperty("saveCredentials"));
            configVO.setServerURL(prop.getProperty("serverURL"));
            configVO.setSaveCredentials(saveCredentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configVO;
    }

    public void saveConfig(ConfigVO configVO) {
        Properties prop = new Properties();
        try (InputStream arquivoConfig = new FileInputStream(new File("config.properties"))) {
            prop.load(arquivoConfig);
            prop.setProperty("email", configVO.getEmail());
            prop.setProperty("gameExePath", configVO.getGameExePath());
            prop.setProperty("passwordSHA1", configVO.getPasswordSHA1());
            String saveCredentials = String.valueOf(configVO.isSaveCredentials());
            prop.setProperty("saveCredentials", saveCredentials);
            prop.setProperty("serverURL", configVO.getServerURL());
            prop.store(new FileOutputStream("config.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
