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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerDTO implements Serializable {

    private String id;


    private String firstName;


    private String lastName;


    private String email;


    private String password;

}
