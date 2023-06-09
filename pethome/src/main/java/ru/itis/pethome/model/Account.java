package ru.itis.pethome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractEntity{

    private String username;
    private String email;
    private String hashPassword;
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "volunteer")
    List<District> districts;

    private String image;

    public enum Role{
        ADMIN, USER
    }

    public enum Status{
        CONFIRMED, UNCONFIRMED, BANNED, DELETED
    }

}
