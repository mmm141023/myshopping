package com.fendou.service;

import com.fendou.commen.ServerResponse;
import com.fendou.pojo.UserInfo;

public interface IUserService {
    ServerResponse login(String username, String password);

    ServerResponse getQuestionByUsername(String username);

    ServerResponse checkAnswerAndPassword(String username, String question, String answer);

    ServerResponse registerUser(UserInfo userInfo);

    ServerResponse updatePassword(String username, String newPassword, String token);
}
