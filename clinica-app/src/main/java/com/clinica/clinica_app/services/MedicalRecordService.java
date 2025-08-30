package com.clinica.clinica_app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.clinica_app.models.MedicalRecord;
import com.clinica.clinica_app.models.Patient;
import com.clinica.clinica_app.models.User;

@Service
public class MedicalRecordService {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private UserService userService;
    
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private Long nextId = 1L;

    public MedicalRecord createMedicalRecord(Long patientId, Long doctorId, 
                                            String diagnosis, String treatment, 
                                            String testResults, String prescriptions, 
                                            String relevantInfo) {
        Patient patient = patientService.getPatientById(patientId);
        User doctor = userService.getUserById(doctorId);
        
        if (patient == null || doctor == null) {
            return null;
        }
        
        MedicalRecord record = new MedicalRecord();
        record.setId(nextId++);
        record.setPatient(patient);
        record.setDoctor(doctor);
        record.setDiagnosis(diagnosis);
        record.setTreatment(treatment);
        record.setTestResults(testResults);
        record.setPrescriptions(prescriptions);
        record.setRelevantInfo(relevantInfo);
        
        medicalRecords.add(record);
        return record;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return new ArrayList<>(medicalRecords);
    }

    public MedicalRecord getMedicalRecordById(Long id) {
        Optional<MedicalRecord> record = medicalRecords.stream()
                                                    .filter(m -> m.getId().equals(id))
                                                    .findFirst();
        return record.orElse(null);
    }

    public List<MedicalRecord> getRecordsByPatientId(Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            return medicalRecords.stream()
                               .filter(m -> m.getPatient().getId().equals(patientId))
                               .toList();
        }
        return List.of();
    }

    public List<MedicalRecord> getRecordsByDoctorId(Long doctorId) {
        User doctor = userService.getUserById(doctorId);
        if (doctor != null) {
            return medicalRecords.stream()
                               .filter(m -> m.getDoctor().getId().equals(doctorId))
                               .toList();
        }
        return List.of();
    }
}