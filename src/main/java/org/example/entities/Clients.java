package org.example.entities;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Clients {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "name")
    @Size(min = 3,max = 200)
    private String name;
    public Clients(String name){
        this.name = name;
    }
    public Clients(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public Clients(){

    }
    @Override
    public String toString() {
        return "Client{"
                + "id='" + id + '\''
                + ", name=" + name
                + '}';
    }
}
