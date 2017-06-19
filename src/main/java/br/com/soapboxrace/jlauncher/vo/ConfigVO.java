package br.com.soapboxrace.jlauncher.vo;

public class ConfigVO {

    private String gameExePath;
    private String serverURL;
    private String email;
    private String passwordSHA1;
    private boolean saveCredentials = false;

    public String getGameExePath() {
        return gameExePath;
    }

    public void setGameExePath(String gameExePath) {
        this.gameExePath = gameExePath;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordSHA1() {
        return passwordSHA1;
    }

    public void setPasswordSHA1(String passwordSHA1) {
        this.passwordSHA1 = passwordSHA1;
    }

    public boolean isSaveCredentials() {
        return saveCredentials;
    }

    public void setSaveCredentials(boolean saveCredentials) {
        this.saveCredentials = saveCredentials;
    }

}
