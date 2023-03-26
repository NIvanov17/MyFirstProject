package com.example.myfirstproject.model;


import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first-name", nullable = false)
    private String firstName;

    @Column(name = "last-name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "telephone-number", nullable = false)
    private String telephoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<OfferEntity> likedOffers;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;


    public UserEntity() {
        this.roles = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<OfferEntity> getLikedOffers() {
        return likedOffers;
    }

    public void setLikedOffers(List<OfferEntity> likedOffers) {
        this.likedOffers = likedOffers;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "Provided" : "No Information") + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", likedOffers=" + likedOffers +
                ", roles=" + roles +
                '}';
    }
}
