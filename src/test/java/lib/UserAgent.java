package lib;


public class UserAgent {
    private String user_agent;
    private String platform;
    private String browser;
    private String device;

    public UserAgent() {}

    public UserAgent(String user_agent, String platform, String browser, String device) {
        this.user_agent = user_agent;
        this.platform = platform;
        this.browser = browser;
        this.device = device;
    }

    @Override
    public String toString() {
        return String.format(
                "{\"user_agent\":\"%s\",\"platform\":\"%s\",\"browser\":\"%s\",\"device\":\"%s\"}",
                user_agent, platform, browser, device
        );
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
