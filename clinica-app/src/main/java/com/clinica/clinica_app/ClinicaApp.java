package com.clinica.clinica_app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clinica.clinica_app.models.*;
import com.clinica.clinica_app.services.*;

@SpringBootApplication
public class ClinicaApp implements CommandLineRunner {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private MedicalRecordService medicalRecordService;
    
    @Autowired
    private VisitService visitService;

    public static void main(String[] args) {
        SpringApplication.run(ClinicaApp.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("🏥 BIENVENIDO AL SISTEMA DE CLÍNICA");
        
        // Autenticación simulada
        System.out.print("Ingrese su ID de usuario: ");
        Long userId = scanner.nextLong();
        scanner.nextLine();
        
        User currentUser = userService.getUserById(userId);
        if (currentUser == null) {
            System.out.println("❌ Usuario no encontrado. Usando modo demo...");
            // Crear usuario demo
            currentUser = userService.createUser("Admin", "Demo", "admin@clinica.com", 
                "password", Role.HUMAN_RESOURCES, "12345678", LocalDate.now(), 
                "Masculino", "Calle Demo 123", "555-1234");
            System.out.println("✅ Usuario demo creado con ID: " + currentUser.getId());
        }
        
        System.out.println("Bienvenido/a: " + currentUser.getFirstName() + " " + currentUser.getLastName());
        System.out.println("Rol: " + currentUser.getRole());
        
        while (running) {
            switch (currentUser.getRole()) {
                case HUMAN_RESOURCES -> showHRMenu(scanner);
                case ADMINISTRATIVE -> showAdminMenu(scanner);
                case NURSE -> showNurseMenu(scanner);
                case DOCTOR -> showDoctorMenu(scanner);
                default -> {
                    System.out.println("Rol no reconocido.");
                    running = false;
                }
            }
            
            if (running) {
                System.out.print("¿Desea realizar otra operación? (s/n): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("n")) {
                    running = false;
                    System.out.println("👋 Saliendo del sistema...");
                }
            }
        }
        scanner.close();
    }
    
    private void showHRMenu(Scanner scanner) {
        System.out.println("\n📋 Menú de Recursos Humanos");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Eliminar usuario");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> createUser(scanner);
            case 2 -> listUsers();
            case 3 -> deleteUser(scanner);
            case 4 -> { return; }
            default -> System.out.println("❌ Opción inválida.");
        }
    }
    
    private void showAdminMenu(Scanner scanner) {
        System.out.println("\n📋 Menú Administrativo");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Programar cita");
        System.out.println("3. Ver citas programadas");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> registerPatient(scanner);
            case 2 -> scheduleAppointment(scanner);
            case 3 -> viewScheduledAppointments();
            case 4 -> { return; }
            default -> System.out.println("❌ Opción inválida.");
        }
    }
    
    private void showNurseMenu(Scanner scanner) {
        System.out.println("\n📋 Menú de Enfermería");
        System.out.println("1. Registro de visitas de pacientes");
        System.out.println("2. Historial de visitas de pacientes");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> registerPatientVisit(scanner);
            case 2 -> viewVisitHistory(scanner);
            case 3 -> { return; }
            default -> System.out.println("❌ Opción inválida.");
        }
    }
    
    private void showDoctorMenu(Scanner scanner) {
        System.out.println("\n📋 Menú Médico");
        System.out.println("1. Ver historia clínica del paciente");
        System.out.println("2. Crear registro médico");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> viewMedicalHistory(scanner);
            case 2 -> createMedicalRecord(scanner);
            case 3 -> { return; }
            default -> System.out.println("❌ Opción inválida.");
        }
    }
    
    private void createUser(Scanner scanner) {
        System.out.print("Ingrese nombre completo: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine();
        System.out.print("Ingrese correo: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Ingrese rol (HUMAN_RESOURCES, ADMINISTRATIVE, NURSE, DOCTOR): ");
        String roleInput = scanner.nextLine();
        Role role = Role.valueOf(roleInput.toUpperCase());

        System.out.print("Ingrese cédula: ");
        String documentId = scanner.nextLine();
        System.out.print("Ingrese fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Ingrese género: ");
        String gender = scanner.nextLine();
        System.out.print("Ingrese dirección: ");
        String address = scanner.nextLine();
        System.out.print("Ingrese teléfono: ");
        String phoneNumber = scanner.nextLine();

        User user = userService.createUser(firstName, lastName, email, password, role, documentId,
                birthDate, gender, address, phoneNumber);
        System.out.println("✅ Usuario creado con ID: " + user.getId());
    }
    
    private void listUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("📄 Lista de usuarios registrados:");

        for (User u : users) {
            System.out.println("──────────────────────────────");
            System.out.println("ID: " + u.getId());
            System.out.println("Nombre: " + u.getFirstName() + " " + u.getLastName());
            System.out.println("Correo: " + u.getEmail());
            System.out.println("Rol: " + u.getRole());
            System.out.println("Cédula: " + u.getDocumentId());
            System.out.println("Fecha de nacimiento: " + u.getBirthDate());
            System.out.println("Género: " + u.getGender());
            System.out.println("Dirección: " + u.getAddress());
            System.out.println("Teléfono: " + u.getPhoneNumber());
        }

        if (users.isEmpty()) {
            System.out.println("⚠️ No hay usuarios registrados.");
        }
    }
    
    private void deleteUser(Scanner scanner) {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            System.out.println("🗑️ Usuario eliminado.");
        } else {
            System.out.println("⚠️ Usuario no encontrado.");
        }
    }
    
    private void registerPatient(Scanner scanner) {
        System.out.print("Ingrese nombre del paciente: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese apellido del paciente: ");
        String lastName = scanner.nextLine();
        System.out.print("Ingrese cédula: ");
        String documentId = scanner.nextLine();
        System.out.print("Ingrese fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Ingrese género: ");
        String gender = scanner.nextLine();
        System.out.print("Ingrese dirección: ");
        String address = scanner.nextLine();
        System.out.print("Ingrese teléfono: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Ingrese contacto de emergencia: ");
        String emergencyContact = scanner.nextLine();
        System.out.print("Ingrese alergias (si no tiene, escriba 'Ninguna'): ");
        String allergies = scanner.nextLine();
        System.out.print("Ingrese condiciones médicas preexistentes: ");
        String medicalConditions = scanner.nextLine();

        Patient patient = patientService.createPatient(firstName, lastName, documentId, 
                birthDate, gender, address, phoneNumber, emergencyContact, allergies, medicalConditions);
        System.out.println("✅ Paciente registrado con ID: " + patient.getId());
    }
    
    private void scheduleAppointment(Scanner scanner) {
        System.out.print("Ingrese ID del paciente: ");
        Long patientId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Ingrese ID del médico: ");
        Long doctorId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Ingrese fecha de la cita (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Ingrese hora de la cita (HH:MM): ");
        LocalTime time = LocalTime.parse(scanner.nextLine());
        System.out.print("Ingrese motivo de la cita: ");
        String reason = scanner.nextLine();

        Appointment appointment = appointmentService.scheduleAppointment(
                patientId, doctorId, date, time, reason);
        if (appointment != null) {
            System.out.println("✅ Cita programada con ID: " + appointment.getId());
        } else {
            System.out.println("❌ Error al programar la cita. Verifique los IDs.");
        }
    }
    
    private void viewScheduledAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        System.out.println("📅 Citas programadas:");

        for (Appointment a : appointments) {
            System.out.println("──────────────────────────────");
            System.out.println("ID: " + a.getId());
            System.out.println("Paciente: " + a.getPatient().getFirstName() + " " + a.getPatient().getLastName());
            System.out.println("Médico: " + a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName());
            System.out.println("Fecha: " + a.getDate());
            System.out.println("Hora: " + a.getTime());
            System.out.println("Motivo: " + a.getReason());
            System.out.println("Estado: " + a.getStatus());
        }

        if (appointments.isEmpty()) {
            System.out.println("⚠️ No hay citas programadas.");
        }
    }
    
    private void registerPatientVisit(Scanner scanner) {
        System.out.print("Ingrese ID del paciente: ");
        Long patientId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Ingrese ID de la enfermera: ");
        Long nurseId = scanner.nextLong();
        scanner.nextLine();
        
        System.out.println("📊 Registro de signos vitales:");
        System.out.print("Presión arterial: ");
        String bloodPressure = scanner.nextLine();
        System.out.print("Temperatura: ");
        double temperature = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Frecuencia cardíaca: ");
        int heartRate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Frecuencia respiratoria: ");
        int respiratoryRate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Saturación de oxígeno: ");
        double oxygenSaturation = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("💊 Medicamentos administrados:");
        System.out.print("Ingrese medicamentos (separados por coma): ");
        String medications = scanner.nextLine();
        
        System.out.println("🩺 Procedimientos realizados:");
        System.out.print("Ingrese procedimientos (separados por coma): ");
        String procedures = scanner.nextLine();
        
        System.out.println("📝 Observaciones relevantes:");
        System.out.print("Ingrese observaciones: ");
        String observations = scanner.nextLine();
        
        Visit visit = visitService.registerVisit(patientId, nurseId, bloodPressure, 
                temperature, heartRate, respiratoryRate, oxygenSaturation, 
                medications, procedures, observations);
        
        if (visit != null) {
            System.out.println("✅ Visita registrada con ID: " + visit.getId());
        } else {
            System.out.println("❌ Error al registrar la visita. Verifique los IDs.");
        }
    }
    
    private void viewVisitHistory(Scanner scanner) {
        System.out.print("Ingrese ID del paciente: ");
        Long patientId = scanner.nextLong();
        scanner.nextLine();
        
        List<Visit> visits = visitService.getVisitsByPatientId(patientId);
        Patient patient = patientService.getPatientById(patientId);
        
        if (patient == null) {
            System.out.println("❌ Paciente no encontrado.");
            return;
        }
        
        System.out.println("📋 Historial de visitas de: " + patient.getFirstName() + " " + patient.getLastName());

        for (Visit v : visits) {
            System.out.println("──────────────────────────────");
            System.out.println("Fecha: " + v.getVisitDate());
            System.out.println("Enfermera: " + v.getNurse().getFirstName() + " " + v.getNurse().getLastName());
            System.out.println("Presión arterial: " + v.getBloodPressure());
            System.out.println("Temperatura: " + v.getTemperature());
            System.out.println("Frecuencia cardíaca: " + v.getHeartRate());
            System.out.println("Frecuencia respiratoria: " + v.getRespiratoryRate());
            System.out.println("Saturación de oxígeno: " + v.getOxygenSaturation());
            System.out.println("Medicamentos: " + v.getMedications());
            System.out.println("Procedimientos: " + v.getProcedures());
            System.out.println("Observaciones: " + v.getObservations());
        }

        if (visits.isEmpty()) {
            System.out.println("⚠️ No hay visitas registradas para este paciente.");
        }
    }
    
    private void viewMedicalHistory(Scanner scanner) {
        System.out.print("Ingrese ID del paciente: ");
        Long patientId = scanner.nextLong();
        scanner.nextLine();
        
        List<MedicalRecord> records = medicalRecordService.getRecordsByPatientId(patientId);
        Patient patient = patientService.getPatientById(patientId);
        
        if (patient == null) {
            System.out.println("❌ Paciente no encontrado.");
            return;
        }
        
        System.out.println("📋 Historia clínica de: " + patient.getFirstName() + " " + patient.getLastName());

        for (MedicalRecord r : records) {
            System.out.println("──────────────────────────────");
            System.out.println("Fecha: " + r.getRecordDate());
            System.out.println("Médico: " + r.getDoctor().getFirstName() + " " + r.getDoctor().getLastName());
            System.out.println("Diagnóstico: " + r.getDiagnosis());
            System.out.println("Tratamiento: " + r.getTreatment());
            System.out.println("Resultados de pruebas: " + r.getTestResults());
            System.out.println("Prescripciones: " + r.getPrescriptions());
            System.out.println("Información relevante: " + r.getRelevantInfo());
        }

        if (records.isEmpty()) {
            System.out.println("⚠️ No hay registros médicos para este paciente.");
        }
    }
    
    private void createMedicalRecord(Scanner scanner) {
        System.out.print("Ingrese ID del paciente: ");
        Long patientId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Ingrese ID del médico: ");
        Long doctorId = scanner.nextLong();
        scanner.nextLine();
        
        System.out.println("🩺 Creación de registro médico:");
        System.out.print("Diagnóstico: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Tratamiento: ");
        String treatment = scanner.nextLine();
        System.out.print("Resultados de pruebas: ");
        String testResults = scanner.nextLine();
        System.out.print("Prescripciones: ");
        String prescriptions = scanner.nextLine();
        System.out.print("Información relevante: ");
        String relevantInfo = scanner.nextLine();
        
        MedicalRecord record = medicalRecordService.createMedicalRecord(
                patientId, doctorId, diagnosis, treatment, testResults, prescriptions, relevantInfo);
        
        if (record != null) {
            System.out.println("✅ Registro médico creado con ID: " + record.getId());
        } else {
            System.out.println("❌ Error al crear el registro médico. Verifique los IDs.");
        }
    }
}