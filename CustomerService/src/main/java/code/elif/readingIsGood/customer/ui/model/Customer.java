package code.elif.readingIsGood.customer.ui.model;

import code.elif.readingIsGood.customer.ui.controller.validation.anotation.UniqueEmailAddress;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable {

    private String id;

    @NonNull
    @NotNull(message = "First name is required!")
    @Size(min = 2, message = "First name must be at least 2 characters!")
    private String firstName;

    @NonNull
    @NotNull(message = "Last name is required!")
    @Size(min = 2, message = "First name must be at least 2 characters!")
    private String lastName;

    @NonNull
    @NotNull(message = "Email is required!")
    @Email
    @NotEmpty
    @UniqueEmailAddress
    private String email;

    @NonNull
    @NotNull(message = "Password is required!")
    @Size(min = 6, max = 16, message = "Password must be at least 6 characters and maximum 16 characters!")
    private String password;

}
