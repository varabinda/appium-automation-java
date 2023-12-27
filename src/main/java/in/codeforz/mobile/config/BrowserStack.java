package in.codeforz.mobile.config;

public class BrowserStack {
    private String userName;
    private String accessKey;
    private String uploadApi;

    public String getUploadApi() {
        return uploadApi;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setUploadApi(String uploadApi) {
        this.uploadApi = uploadApi;
    }
}
