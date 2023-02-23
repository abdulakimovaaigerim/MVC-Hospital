package peaksoft.repositories;

import peaksoft.entities.Patient;

import java.util.List;

public interface PatientRepository {

    void savePatient(Patient patient);

    Patient getPatientById(Long id);

    List<Patient> getAllPatient(Long id);

    void updatePatient(Long id, Patient patient);

    void removePatientById(Long id);
}
