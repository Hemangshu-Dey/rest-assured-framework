package enums;

public enum AuthResources {
    registerUserApi("/api/auth/register"),
    loginUserApi("/api/auth/login"),
    logoutUserApi("/api/auth/logout"),
    validateAccessTokenApi("/api/auth/validation");;

    private final String resource;

    AuthResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
