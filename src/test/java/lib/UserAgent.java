package lib;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAgent {
//    @JsonProperty("user-agent")
//    private String userAgent;
private String user_agent;
    private String platform;
    private String browser;
    private String device;


    public UserAgent(String user_agent, String platform, String browser, String device) {
        setUserAgent(user_agent);
        setPlatform(platform);
        setBrowser(browser);
        setDevice(device);
    }

    public String getUserAgent() {
        return user_agent;
    }

    public void setUserAgent(String user_agent) {
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
