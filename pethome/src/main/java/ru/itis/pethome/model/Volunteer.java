package ru.itis.pethome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;


    private Integer rating;
    private Integer summaryFound;


}
