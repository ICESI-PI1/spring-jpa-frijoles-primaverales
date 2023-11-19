package com.library.Library.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String nationality;


    public Author (Author author){
        this(author.getId(), author.getName(), author.getNationality());
    }

    @Override
    public String toString(){
        return ("id: " + this.id + ", name: " + this.name + ", nationality: " + this.nationality );
    }
}
