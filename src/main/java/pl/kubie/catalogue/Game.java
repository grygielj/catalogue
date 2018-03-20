package pl.kubie.catalogue;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double rate;
    private String type;
    private String comment;
    private LocalDate date;


    public Game() {
    }

    public Game(String name, String type, String comment, double rate) {
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.date = LocalDate.now();
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public LocalDate getDate() {
        return date;
    }

}
