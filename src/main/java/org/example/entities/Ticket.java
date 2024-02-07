package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter@Getter
    private Long id;

    @Setter@Getter
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @Setter@Getter
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    @Setter@Getter
    private Planet fromPlanet;

    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    @Setter@Getter
    private Planet toPlanet;

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", created_at=" + createdAt +
                ", client_id=" + client +
                ", from_planet_id=" + fromPlanet +
                ", to_planet_id=" + toPlanet +
                '}';
    }

}
