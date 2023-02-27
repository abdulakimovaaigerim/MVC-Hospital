package peaksoft.servies.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Hospital;
import peaksoft.entities.Patient;
import peaksoft.repositories.HospitalRepository;
import peaksoft.repositories.PatientRepository;
import peaksoft.servies.PatientService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;


    @Override
    public void savePatient(Long hospitalId, Patient patient) {
        Hospital existHospital = hospitalRepository.getHospitalById(hospitalId);
        existHospital.addPatient(patient);
        patient.setHospital(existHospital);
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
