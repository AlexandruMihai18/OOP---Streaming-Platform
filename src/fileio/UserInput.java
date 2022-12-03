package fileio;

public final class UserInput {
    private CredentialsInput credentials;

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "credentials=" + credentials +
                '}';
    }
}
