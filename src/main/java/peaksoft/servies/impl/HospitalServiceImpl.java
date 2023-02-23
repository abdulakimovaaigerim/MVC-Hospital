package peaksoft.servies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Hospital;
import peaksoft.repositories.HospitalRepository;
import peaksoft.servies.HospitalService;

import java.util.List;

@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepository.saveHospital(hospital);
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.getHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalRepository.getAllHospital();
    }

    @Override
    public void updateHospital(Long id,Hospital hospital) {
        hospitalRepository.updateHospital(id,hospital);
    }

    @Override
    public void removeHospitalById(Hospital hospital) {
        hospitalRepository.removeHospitalById(hospital);
    }
}
