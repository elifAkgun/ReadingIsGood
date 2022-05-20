package code.elif.readingIsGood.customer.service.dto;

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
public class CustomerDTO implements Serializable {

    private Integer id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String password;

}
