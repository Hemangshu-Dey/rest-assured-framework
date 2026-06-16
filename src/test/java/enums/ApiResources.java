package enums;

public enum ApiResources {
    registerUserApi("/api/auth/register"),
    loginUserApi("/api/auth/login"),
    logoutUserApi("/api/auth/logout"),
    validateAccessTokenApi("/api/auth/validation"),
    refreshAccessTokenApi("/api/token/newToken"),
    getTodoCategoryApi("/api/todo/getToDoCategory"),
    createTodoCategoryApi("/api/todo/createToDoCategory"),
    deleteTodoCategoryApi("/api/todo/deleteToDoCategory");

    private final String resource;

    ApiResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
