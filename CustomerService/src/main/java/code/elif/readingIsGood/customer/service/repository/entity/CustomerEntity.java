package code.elif.readingIsGood.customer.service.repository.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {

    @Id
    @NonNull
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NonNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NonNull
    @Column(name = "EMAIL")
    private String email;

    @NonNull
    private String password;

}
