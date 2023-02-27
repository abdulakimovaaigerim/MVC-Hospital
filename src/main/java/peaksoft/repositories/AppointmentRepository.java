package peaksoft.repositories;

import peaksoft.entities.Appointment;

import java.util.List;

public interface AppointmentRepository {

    void saveAppointment(Appointment appointment);

    Appointment getAppointmentById(Long id);

    List<Appointment> getAllAppointment(Long id);

    void updateAppointment(Long id, Appointment appointment);

    void removeAppointmentById(Long id);
}
