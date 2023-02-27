package peaksoft.servies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Appointment;
import peaksoft.entities.Hospital;
import peaksoft.repositories.*;
import peaksoft.servies.AppointmentService;

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
        newAppointment.setDate(appointment.getDate());
        newAppointment.setPatient(patientRepository.getPatientById(appointment.getPatientId()));
        newAppointment.setDoctor(doctorRepository.getDoctorById(appointment.getDoctorId()));
        newAppointment.setDepartment(departmentRepository.getDepartmentById(appointment.getDepartmentId()));
        hospital.addAppointment(newAppointment);
        appointmentRepository.saveAppointment(newAppointment);
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
    public void removeAppointmentById(Long id,Long hospitalId) {
        Appointment appointment = appointmentRepository.getAppointmentById(id);
        hospitalRepository.getHospitalById(hospitalId).getAppointments().remove(appointment);
        appointment.getDoctor().getAppointments().remove(appointment);
        appointment.getPatient().getAppointments().remove(appointment);
        appointmentRepository.removeAppointmentById(id);
    }

}
