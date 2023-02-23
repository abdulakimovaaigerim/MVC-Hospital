package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Department;
import peaksoft.entities.Hospital;
import peaksoft.repositories.DepartmentRepository;

import java.util.List;

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

}
