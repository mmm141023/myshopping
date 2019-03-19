package com.fendou.service.impl;

import com.fendou.commen.ServerResponse;
import com.fendou.dao.UserInfoMapper;
import com.fendou.pojo.UserInfo;
import com.fendou.service.IUserService;
import com.fendou.utils.MD5Utils;
import com.fendou.utils.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public ServerResponse login(String username, String password) {

        if(username == null || username.equals("")){
            return ServerResponse.ResponseWhenError("用户名不能为空");
        }
        if(password == null || password.equals("")){
            return ServerResponse.ResponseWhenError("密码不能为空");
        }

        //检查用户名是否存在
        int result = userInfoMapper.checkUsername(username);
        if (result==0){
            return ServerResponse.ResponseWhenError("用户名不存在");
        }




        UserInfo userInfo = userInfoMapper.selectByNameWithPwd(username, password);


        if(userInfo == null){
            return ServerResponse.ResponseWhenError("密码输入错误");
        }
        //为使前台看不到密码，所以将密码至空
        userInfo.setPassword("");
        return ServerResponse.ResponseWhenSuccess(userInfo);
    }




    @Override
    public ServerResponse getQuestionByUsername(String username) {
        if (username==null || username.equals("")){
            return ServerResponse.ResponseWhenError("用户名不能为空");
        }

        int result = userInfoMapper.checkUsername(username);
        if (result==0){
            return ServerResponse.ResponseWhenError("用户名不存在");
        }
        String question = userInfoMapper.getQuestionByUsername(username);
        if (question == null || question.equals("")){
            return ServerResponse.ResponseWhenError("密保问题为空");
        }


        return ServerResponse.ResponseWhenSuccess(question);
    }

    @Override
    public ServerResponse checkAnswerAndPassword(String username, String question, String answer) {
        if (username==null || username.equals("")){
            return ServerResponse.ResponseWhenError("用户名不能为空");
        }
        if (question==null || question.equals("")){
            return ServerResponse.ResponseWhenError("密保不能为空");
        }
        if (answer==null || answer.equals("")){
            return ServerResponse.ResponseWhenError("答案不能为空");
        }


        //通过用户名和问题查answer
        String result = userInfoMapper.selectAnswerByUsernameAndQuestion(username,question);
        if (result.equals(answer)){
            //问题和答案正确
            //将token放入缓存中，防止横向越权 guava缓存
            String token_random = UUID.randomUUID().toString();
            TokenCache.set(username, token_random);
            return ServerResponse.ResponseWhenSuccess(token_random);
        }

        return ServerResponse.ResponseWhenError("密保答案不正确");
    }



    //注册用户
    @Override
    public ServerResponse registerUser(UserInfo userInfo) {

        int result = userInfoMapper.checkUsername(userInfo.getUsername());
        if (result>0){
            return ServerResponse.ResponseWhenError("用户名已经存在");
        }

        int result_email = userInfoMapper.checkEmail(userInfo.getEmail());
        if (result_email>0){
            return ServerResponse.ResponseWhenError("邮箱已经存在");
        }

        int insert = userInfoMapper.insert(userInfo);
        if(insert >0){
            return ServerResponse.ResponseWhenSuccess("注册成功");
        }
        return ServerResponse.ResponseWhenError("注册失败");
    }

    @Override
    public ServerResponse updatePassword(String username, String newPassword, String token) {


        if (username==null || username.equals("")){
            return ServerResponse.ResponseWhenError("用户名不能为空");
        }
        if (newPassword==null || newPassword.equals("")){
            return ServerResponse.ResponseWhenError("新密码不能为空");
        }
        if (token==null || token.equals("")){
            return ServerResponse.ResponseWhenError("令牌不能为空");
        }

        String token_random = TokenCache.get(username);

        if (token_random.equals(token)){
            int result = userInfoMapper.updatePassword(username,newPassword,token);

            if(result>0){
                return ServerResponse.ResponseWhenSuccess("修改密码成功");
            }else {
                return ServerResponse.ResponseWhenError("修改密码失败");
            }
        }else
        {
            return ServerResponse.ResponseWhenError("token有误，请重新输入");
        }
    }

}
