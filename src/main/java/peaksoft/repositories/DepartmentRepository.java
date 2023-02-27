package peaksoft.repositories;

import peaksoft.entities.Department;

import java.util.List;

public interface DepartmentRepository {

    void saveDepartment(Long id, Department department);

    Department getDepartmentById(Long id);

    List<Department> getAllDepartment(Long id);

    void updateDepartment(Long id, Department department);

    void removeDepartmentById(Long id);

    void assignDoctor(Long doctorId, Long departmentId);

}
