package testData;

import models.request.loginUser;
import models.request.logoutUser;
import models.request.registerUser;

public class testDataBuilder {
        public registerUser registerUserPayload(
                String username,
                String email,
                String password) {

            registerUser user = new registerUser();

            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            return user;
        }

        public loginUser loginUserPayload(String identifier, String password){
            loginUser user = new loginUser();

            user.setIdentifier(identifier);
            user.setPassword(password);

            return user;
    }

    public logoutUser logoutUserPayload(String userId) {

        logoutUser payload = new logoutUser();

        payload.setUserid(userId);

        return payload;
    }
}
