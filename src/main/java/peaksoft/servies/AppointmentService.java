package peaksoft.servies;

import peaksoft.entities.Appointment;

import java.util.List;

public interface AppointmentService {

    void saveAppointment(Long hospitalId, Appointment appointment);

    Appointment getAppointmentById(Long id);

    List<Appointment> getAllAppointment(Long id);

    void updateAppointment(Long id, Appointment appointment);

    void removeAppointmentById(Long id,Long hospitalId);
}
