package peaksoft.servies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Doctor;
import peaksoft.repositories.DoctorRepository;
import peaksoft.servies.DoctorService;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void saveDoctor(Long id, Doctor doctor) {
        doctorRepository.saveDoctor(id,doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.getDoctorById(id);
    }

    @Override
    public List<Doctor> getAllDoctor(Long id) {
        return doctorRepository.getAllDoctor(id);
    }

    @Override
    public void updateDoctor(Long id, Doctor doctor) {
        doctorRepository.updateDoctor(id, doctor);
    }

    @Override
    public void removeDoctorById(Long id ) {
        doctorRepository.removeDoctorById(id);
    }

}
