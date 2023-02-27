package peaksoft.servies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entities.Department;
import peaksoft.repositories.DepartmentRepository;
import peaksoft.servies.DepartmentService;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveDepartment(Long id, Department department) {
        departmentRepository.saveDepartment(id, department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public List<Department> getAllDepartment(Long id) {
        return departmentRepository.getAllDepartment(id);
    }

    @Override
    public void updateDepartment(Long id, Department department) {
        departmentRepository.updateDepartment(id, department);
    }

    @Override
    public void removeDepartmentById(Long id) {
        departmentRepository.removeDepartmentById(id);
    }

    @Override
    public void assignDoctor(Long doctorId, Long departmentId) {
        departmentRepository.assignDoctor(doctorId, departmentId);
    }

}
