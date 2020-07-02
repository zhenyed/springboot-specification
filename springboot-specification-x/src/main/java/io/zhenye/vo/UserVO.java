package io.zhenye.vo;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserVO {
    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 6, max = 11, message = "昵称必须是6-11个字符")
    private String nickName;

    @NotNull(message = "年龄不能为空")
    @Max(message = "年龄不能超过120岁", value = 120)
    @Min(message = "年龄不能小于1岁", value = 1)
    private Integer age;

    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
