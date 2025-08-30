package com.clinica.clinica_app.models;

import java.time.LocalDateTime;

public class Visit {
    private Long id;
    private Patient patient;
    private User nurse;
    private LocalDateTime visitDate = LocalDateTime.now();
    private String bloodPressure;
    private Double temperature;
    private Integer heartRate;
    private Integer respiratoryRate;
    private Double oxygenSaturation;
    private String medications;
    private String procedures;
    private String observations;

    // Constructores
    public Visit() {}
    
    public Visit(Patient patient, User nurse, String bloodPressure, 
                Double temperature, Integer heartRate, Integer respiratoryRate, 
                Double oxygenSaturation, String medications, String procedures, 
                String observations) {
        this.patient = patient;
        this.nurse = nurse;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.heartRate = heartRate;
        this.respiratoryRate = respiratoryRate;
        this.oxygenSaturation = oxygenSaturation;
        this.medications = medications;
        this.procedures = procedures;
        this.observations = observations;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public User getNurse() { return nurse; }
    public void setNurse(User nurse) { this.nurse = nurse; }
    public LocalDateTime getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDateTime visitDate) { this.visitDate = visitDate; }
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    public Integer getHeartRate() { return heartRate; }
    public void setHeartRate(Integer heartRate) { this.heartRate = heartRate; }
    public Integer getRespiratoryRate() { return respiratoryRate; }
    public void setRespiratoryRate(Integer respiratoryRate) { this.respiratoryRate = respiratoryRate; }
    public Double getOxygenSaturation() { return oxygenSaturation; }
    public void setOxygenSaturation(Double oxygenSaturation) { this.oxygenSaturation = oxygenSaturation; }
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    public String getProcedures() { return procedures; }
    public void setProcedures(String procedures) { this.procedures = procedures; }
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}