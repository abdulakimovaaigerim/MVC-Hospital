package peaksoft.repositories;

import peaksoft.entities.Hospital;

import java.util.List;

public interface HospitalRepository {

    void saveHospital(Hospital hospital);

    Hospital getHospitalById(Long id);

    List<Hospital> getAllHospital();

    void updateHospital(Long hospitalId,Hospital newHospital);

    void removeHospitalById(Hospital hospital);
}
