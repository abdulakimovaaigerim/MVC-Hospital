package peaksoft.servies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Patient;
import peaksoft.repositories.PatientRepository;
import peaksoft.servies.PatientService;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void savePatient(Patient patient) {
        patientRepository.savePatient(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.getPatientById(id);
    }

    @Override
    public List<Patient> getAllPatient(Long id) {
        return patientRepository.getAllPatient(id);
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        patientRepository.updatePatient(id, patient);
    }

    @Override
    public void removePatientById(Long id) {
        patientRepository.removePatientById(id);
    }

}
