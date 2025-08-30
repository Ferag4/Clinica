package com.clinica.clinica_app.models;

import java.time.LocalDateTime;

public class MedicalRecord {
    private Long id;
    private Patient patient;
    private User doctor;
    private LocalDateTime recordDate = LocalDateTime.now();
    private String diagnosis;
    private String treatment;
    private String testResults;
    private String prescriptions;
    private String relevantInfo;

    // Constructores
    public MedicalRecord() {}
    
    public MedicalRecord(Patient patient, User doctor, String diagnosis, 
                        String treatment, String testResults, String prescriptions, 
                        String relevantInfo) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.testResults = testResults;
        this.prescriptions = prescriptions;
        this.relevantInfo = relevantInfo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }
    public LocalDateTime getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDateTime recordDate) { this.recordDate = recordDate; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public String getTestResults() { return testResults; }
    public void setTestResults(String testResults) { this.testResults = testResults; }
    public String getPrescriptions() { return prescriptions; }
    public void setPrescriptions(String prescriptions) { this.prescriptions = prescriptions; }
    public String getRelevantInfo() { return relevantInfo; }
    public void setRelevantInfo(String relevantInfo) { this.relevantInfo = relevantInfo; }
}