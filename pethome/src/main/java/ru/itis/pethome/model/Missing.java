package ru.itis.pethome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Missing extends AbstractEntity{
    private Double posX;
    private Double posY;
    private String description;
    private String name;
    private String kind;
    private String gender;

    private String imagePath;


    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Account owner;


    public enum Status{
        ACTIVE, CLOSE, BANNED, VERIFICATION
    }

    public enum Type{
        FOUND, LOST
    }
}


