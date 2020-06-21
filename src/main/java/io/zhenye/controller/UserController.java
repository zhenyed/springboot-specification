package io.zhenye.controller;

import io.zhenye.entity.vo.UserVO;
import io.zhenye.enums.ResponseCodeEnum;
import io.zhenye.util.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/create")
    public ResponseResult create(@Valid UserVO user) {
        return ResponseResult.build(ResponseCodeEnum.SUCCESS);
    }
}
