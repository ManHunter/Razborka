package com.razborka.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 08.04.2015.
 */
@Entity
@Table(name="user")
public class User implements Serializable {

    private int id;
    private String fio;
    private String name;
    private String email;
    private String password;
    private String role;
    private String photo1;
    private String photo2;
    private String photo3;
    private String skype;
    private String site;
    private String description;
    private LocalDateTime date;

    private List<Car> cars;
    private List<Service> services;
    private List<Message> inboxMessages;
    private List<Message> outboxMessages;
    private List<Review> reviews;
    private List<Review> myReviews;
    private List<Address> addresses;
    private List<Phone> phones;
    private List<Comment> comments;
    private List<Request> userRequests;
    private List<Request> requestsFromUsers;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty()
    @Size(min = 5, max = 30)
    @Column(name = "fio", nullable = false, length = 45)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

//    @Size(min = 5, max = 45)
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Email
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty
    @Size(min = 5)
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role", nullable = false, length = 20)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "photo1", nullable = true, unique = false)
    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    @Column(name = "photo2", nullable = true, unique = false)
    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    @Column(name = "photo3", nullable = true, unique = false)
    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    @Column(name = "skype")
    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Column(name = "site")
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public List<Message> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<Message> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_from")
    public List<Message> getOutboxMessages() {
        return outboxMessages;
    }

    public void setOutboxMessages(List<Message> outboxMessages) {
        this.outboxMessages = outboxMessages;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_from")
    public List<Review> getMyReviews() {
        return myReviews;
    }

    public void setMyReviews(List<Review> myReviews) {
        this.myReviews = myReviews;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    //@Transient
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public List<Request> getUserRequests() {
        return userRequests;
    }

    public void setUserRequests(List<Request> userRequests) {
        this.userRequests = userRequests;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_from")
    public List<Request> getRequestsFromUsers() {
        return requestsFromUsers;
    }

    public void setRequestsFromUsers(List<Request> requestsFromUsers) {
        this.requestsFromUsers = requestsFromUsers;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
