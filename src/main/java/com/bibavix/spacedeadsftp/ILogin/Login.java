package com.bibavix.spacedeadsftp.ILogin;

import com.bibavix.spacedeadsftp.UserLogin.User;

public interface Login {
    void signIn(User user);
    void signOut();
}
