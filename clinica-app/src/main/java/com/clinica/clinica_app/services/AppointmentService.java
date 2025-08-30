package com.clinica.clinica_app.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.clinica_app.models.Appointment;
import com.clinica.clinica_app.models.Patient;
import com.clinica.clinica_app.models.User;

@Service
public class AppointmentService {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private UserService userService;
    
    private List<Appointment> appointments = new ArrayList<>();
    private Long nextId = 1L;

    public Appointment scheduleAppointment(Long patientId, Long doctorId, 
                                          LocalDate date, LocalTime time, 
                                          String reason) {
        Patient patient = patientService.getPatientById(patientId);
        User doctor = userService.getUserById(doctorId);
        
        if (patient == null || doctor == null) {
            return null;
        }
        
        Appointment appointment = new Appointment();
        appointment.setId(nextId++);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setReason(reason);
        appointment.setStatus("SCHEDULED");
        
        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointments.stream()
                                                     .filter(a -> a.getId().equals(id))
                                                     .findFirst();
        return appointment.orElse(null);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            return appointments.stream()
                             .filter(a -> a.getPatient().getId().equals(patientId))
                             .toList();
        }
        return List.of();
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        User doctor = userService.getUserById(doctorId);
        if (doctor != null) {
            return appointments.stream()
                             .filter(a -> a.getDoctor().getId().equals(doctorId))
                             .toList();
        }
        return List.of();
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointments.stream()
                         .filter(a -> a.getDate().equals(date))
                         .toList();
    }

    public boolean cancelAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment != null) {
            appointment.setStatus("CANCELLED");
            return true;
        }
        return false;
    }

    public boolean completeAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment != null) {
            appointment.setStatus("COMPLETED");
            return true;
        }
        return false;
    }
}