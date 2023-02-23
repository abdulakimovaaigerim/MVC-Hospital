package peaksoft.servies;

import peaksoft.entities.Department;
import peaksoft.entities.Doctor;

import java.util.List;

public interface DoctorService {

    void saveDoctor(Long id, Doctor doctor);

    Doctor getDoctorById(Long id);

    List<Doctor> getAllDoctor(Long id);

    void updateDoctor(Long id, Doctor doctor);

    void removeDoctorById(Long id);
    void assignDoctor(Long doctorId, Long departmentId);

}
