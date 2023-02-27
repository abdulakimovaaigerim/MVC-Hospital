package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Department;
import peaksoft.entities.Doctor;
import peaksoft.entities.Hospital;
import peaksoft.repositories.DepartmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveDepartment(Long id, Department department) {
        Hospital hospital = entityManager.find(Hospital.class, id);
        hospital.setDepartment(department);
        department.setHospital(hospital);
        entityManager.merge(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> getAllDepartment(Long id) {
        return entityManager
                .createQuery("select d from Department d join d.hospital h where h.id = :id", Department.class)
                .setParameter("id", id)
                .getResultList();
    }


    @Override
    public void updateDepartment(Long id, Department newDepartment) {
        Department department = entityManager.find(Department.class, id);
        department.setName(newDepartment.getName());
        entityManager.merge(department);

    }

    @Override
    public void removeDepartmentById(Long id) {
        Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);
    }


    @Override
    public void assignDoctor(Long doctorId, Long departmentId) {
        try {
            Doctor doctor = entityManager.find(Doctor.class, doctorId);
            Department department = entityManager.find(Department.class, departmentId);

            if (department.getId() != null){
                for (Doctor d : department.getDoctors()) {
                    if (Objects.equals(d.getId(), departmentId));
                    throw new IOException("This doctor already exists!");

                }
            }
            doctor.addDepartment(department);
            department.addDoctor(doctor);
            entityManager.merge(department);
            entityManager.merge(doctor);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
