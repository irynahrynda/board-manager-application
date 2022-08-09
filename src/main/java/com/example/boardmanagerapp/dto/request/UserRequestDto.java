package com.example.boardmanagerapp.dto.request;

import com.example.boardmanagerapp.lib.FieldsValueMatch;
import com.example.boardmanagerapp.lib.ValidEmail;
import lombok.Data;
import javax.validation.constraints.Size;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
}
