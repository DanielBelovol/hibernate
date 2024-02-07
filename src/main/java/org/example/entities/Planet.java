package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "planets")
@NoArgsConstructor
public class Planet {
    @Id
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Getter@Setter
    private String id;

    @Column(name = "name")
    @Size(min = 1, max = 500)
    @Getter@Setter
    private String name;

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planets{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
