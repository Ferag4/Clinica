package com.clinica.clinica_app.models;

import java.time.LocalDate;

public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentId;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String phoneNumber;
    private String emergencyContact;
    private String allergies;
    private String medicalConditions;

    // Constructores
    public Patient() {}
    
    public Patient(String firstName, String lastName, String documentId, 
                  LocalDate birthDate, String gender, String address, 
                  String phoneNumber, String emergencyContact, 
                  String allergies, String medicalConditions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentId = documentId;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emergencyContact = emergencyContact;
        this.allergies = allergies;
        this.medicalConditions = medicalConditions;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
    public String getMedicalConditions() { return medicalConditions; }
    public void setMedicalConditions(String medicalConditions) { this.medicalConditions = medicalConditions; }
}