package peaksoft.servies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Appointment;
import peaksoft.entities.Hospital;
import peaksoft.repositories.*;
import peaksoft.servies.AppointmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, DepartmentRepository departmentRepository, HospitalRepository hospitalRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void saveAppointment(Long hospitalId, Appointment appointment) {
        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
        Appointment newAppointment = new Appointment();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(appointment.getDate1(), formatter);
        newAppointment.setDate(localDate);
        newAppointment.setId(appointment.getId());
        newAppointment.setPatient(patientRepository.getPatientById(appointment.getPatient().getId()));
        newAppointment.setDoctor(doctorRepository.getDoctorById(appointment.getDoctor().getId()));
        newAppointment.setDepartment(departmentRepository.getDepartmentById(appointment.getDepartment().getId()));
        hospital.addAppointment(newAppointment);
        appointmentRepository.saveAppointment(hospitalId, newAppointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }

    @Override
    public List<Appointment> getAllAppointment(Long id) {
        return appointmentRepository.getAllAppointment(id);
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        appointmentRepository.updateAppointment(id, appointment);
    }

    @Override
    public void removeAppointmentById(Long id) {
        appointmentRepository.removeAppointmentById(id);
    }

}
