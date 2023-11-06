package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Size;

@Getter
@Setter
public class UserOutput {

    private String emp_id;

    private String username;

    private String password;

    private int position_id;

    private int dept_id;

    private int section_id;

    private int role_id;

    private String status_code;
    
    // private String emp_id;

    // @NotBlank(message = "Username should not be empty.")
    // @Size(max = 20, message = "The Username is invalid, it should be up to 20
    // characters long.")
    // private String username;

    // @NotBlank(message = "Password should not be empty.")
    // @Size(max = 255, message = "The Password is invalid, it should be up to 255
    // characters long.")
    // private String password;

    // private Long position_id;

    // private Long dept_id;

    // private Long section_id;

    // private String status_code;

    // // private Long role_id;

    // private String img_src;
}
