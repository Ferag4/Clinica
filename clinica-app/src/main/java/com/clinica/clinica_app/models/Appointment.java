package com.clinica.clinica_app.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Long id;
    private Patient patient;
    private User doctor;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private String status = "SCHEDULED";

    // Constructores
    public Appointment() {}
    
    public Appointment(Patient patient, User doctor, LocalDate date, 
                      LocalTime time, String reason) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}