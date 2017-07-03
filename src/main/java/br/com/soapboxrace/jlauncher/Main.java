package br.com.soapboxrace.jlauncher;

import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.soapboxrace.jlauncher.swing.MainWindow;
import br.com.soapboxrace.jlauncher.util.ConfigDao;
import br.com.soapboxrace.jlauncher.util.CopyFiles;
import br.com.soapboxrace.jlauncher.util.EmailValidator;
import br.com.soapboxrace.jlauncher.util.Md5Files;
import br.com.soapboxrace.jlauncher.vo.ConfigVO;
import br.com.soapboxrace.jlauncher.vo.UserVO;

public class Main {

	private static ConfigDao configDao = new ConfigDao();

	public static void main(String[] args) {
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

	public static boolean checkEmail(String email) {
		EmailValidator emailValidator = new EmailValidator(email);
		return emailValidator.isValid();
	}
}
