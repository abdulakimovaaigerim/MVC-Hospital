package peaksoft.servies;

import peaksoft.entities.Hospital;

import java.util.List;

public interface HospitalService {

    void saveHospital(Hospital hospital);

    Hospital getHospitalById(Long id);

    List<Hospital> getAllHospital();

    void updateHospital(Long id,Hospital hospital);

    void removeHospitalById(Hospital hospital);
}
