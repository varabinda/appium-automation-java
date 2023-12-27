package in.codeforz.mobile.config;

public enum ExecPlatform {
    CLOUD("Cloud"),
    LOCAL("Local"),
    INVALID("Invalid");

    private String execPlatform;

    ExecPlatform(String execPlatform) {
        this.execPlatform = execPlatform;
    }

    public String toString() {
        return execPlatform;
    }
}
