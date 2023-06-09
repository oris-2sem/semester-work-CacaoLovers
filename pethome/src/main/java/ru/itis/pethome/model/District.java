package ru.itis.pethome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class District extends AbstractEntity{
    private Double posX;
    private Double posY;
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany
    private List<Account> volunteer;

    @OneToMany(mappedBy = "district")
    private List<Missing> missings;
}
