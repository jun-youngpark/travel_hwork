package com.triple.travel.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/* 로그인는 간단하게 별도 인증 없이 간단하게 구현하였습니다.
*  로그인 처리가 없을 경우 session에서 id:JYPARK 값이 기본으로 설정됩니다.
* */
@RestController
@RequestMapping("/v1/api/user")
@ApiIgnore
public class UserController {

    @Resource
    private UserInfo userInfo;

    @PostMapping("login")
    public void login(HttpServletRequest request, @RequestBody UserInfo dto) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", dto.getUserId());
        session.setAttribute("userName", dto.getUserName());
    }



}
