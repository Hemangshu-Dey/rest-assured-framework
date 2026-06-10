package testData;

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
}
