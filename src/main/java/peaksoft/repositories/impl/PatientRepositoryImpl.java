package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Patient;
import peaksoft.repositories.PatientRepository;

import java.util.List;

@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void savePatient(Patient patient) {
        entityManager.persist(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public List<Patient> getAllPatient(Long id) {
        return entityManager.createQuery("select d from Patient d join d.hospital h where h.id=:id",Patient.class).setParameter("id",id).getResultList();
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        Patient oldPatient = entityManager.find(Patient.class, id);
        oldPatient.setFirstName(patient.getFirstName());
        oldPatient.setLastName(patient.getLastName());
        oldPatient.setPhoneNumber(patient.getPhoneNumber());
        oldPatient.setGender(patient.getGender());
        entityManager.merge(oldPatient);
    }

    @Override
    public void removePatientById(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        entityManager.remove(patient);
    }

}
