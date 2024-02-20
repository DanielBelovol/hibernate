package org.example.entities;

import jakarta.persistence.*;
import lombok.*;


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
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
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

    public Ticket(Long id, Planet fromPlanet, Planet toPlanet) {
        this.id = id;
        this.client = client;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
    }

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
