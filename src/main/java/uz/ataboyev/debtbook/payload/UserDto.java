package uz.ataboyev.debtbook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data
public class UserDto {

    private String username;

    private RoleResDto roleResDto;


    //userni fieldlari proektga qarab har hil bo'ladi
}
