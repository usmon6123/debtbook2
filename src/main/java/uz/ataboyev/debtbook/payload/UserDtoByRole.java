package uz.ataboyev.debtbook.payload;

import uz.ataboyev.debtbook.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDtoByRole {
    private UUID id;
    private String username;

    public UserDtoByRole(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
