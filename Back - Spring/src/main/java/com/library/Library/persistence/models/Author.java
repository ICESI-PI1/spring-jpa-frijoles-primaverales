package com.library.Library.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Author {

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
