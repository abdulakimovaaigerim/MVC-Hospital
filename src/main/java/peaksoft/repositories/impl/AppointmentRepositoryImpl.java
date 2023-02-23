package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Appointment;
import peaksoft.repositories.AppointmentRepository;

import java.util.List;


@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAppointment(Long hospitalId, Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return entityManager.find(Appointment.class, id);
    }

    @Override
    public List<Appointment> getAllAppointment(Long id) {
        return entityManager.createQuery("select a from Hospital h  join  h.appointments a  where  h.id=:id", Appointment.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        Appointment ollAppointment = entityManager.find(Appointment.class, id);
        ollAppointment.setDate(appointment.getDate());
        entityManager.merge(ollAppointment);
    }

    @Override
    public void removeAppointmentById(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        entityManager.remove(appointment);
    }

}
