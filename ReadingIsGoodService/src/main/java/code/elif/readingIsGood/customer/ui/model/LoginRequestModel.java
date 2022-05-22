package code.elif.readingIsGood.customer.ui.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestModel {

    @NonNull
    @NotNull(message = "Email is required!")
    @Email
    private String email;

    @NonNull
    @NotNull(message = "Password is required!")
    @Size(min = 6, max = 16, message = "Password must be at least 6 characters and maximum 16 characters!")
    private String password;
}
