package com.example.restaurantmailservice.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column
    private String subject;
    @Column
    private String body;


    public NotificationType() {
    }

    public NotificationType(String name, String subject, String body) {
        this.name = name;
        this.subject = subject;
        this.body = body;
    }

    @JsonCreator
    public NotificationType(@JsonProperty("name") String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "NotificationType{name='" + name + "'}";
    }
}
