package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
@NoArgsConstructor // Lombok аннотация для конструктора без аргументов
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 200)
    @Getter @Setter
    private String name;



    // Конструктор с параметром name
    public Client(String name) {
        this.name = name;
    }
    public Client(String name,Long id){
        this.name = name;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
