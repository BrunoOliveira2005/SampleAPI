package br.edu.atitus.apisample.entities;

import jakarta.persistence.*;
import org.hibernate.Length;

import java.util.UUID;

@Entity
@Table(name = "register_tb")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private  UserEntity user;

    @Column(columnDefinition = "Decimal(11,8)", nullable = false)
    private double latitude;

    @Column(columnDefinition = "Decimal(11,8)", nullable = false)
    private double longitude;

    @Column(length = 500)
    private String description;


    //metodos gets and sets
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}



