package peaksoft.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Department;
import peaksoft.servies.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private  Long hospitalId;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{hospitalId}")
    public String getAllDepartment(Model model, @PathVariable("hospitalId") Long id) {
        model.addAttribute("departments", departmentService.getAllDepartment(id));
        hospitalId=id;
        return "department/getAllDepartments";
    }

    @GetMapping("/{id}/new")
    String newDepartment(Model model, @PathVariable("id") Long id) {
        model.addAttribute("newDepartment", new Department());
        model.addAttribute("hospitalId", id);
        return "department/newDepartments";
    }

    @PostMapping("/{id}/save")
    String saveDepartment(@PathVariable("id") Long id,
                          @ModelAttribute("department") Department department) {
        departmentService.saveDepartment(id, department);
        return "redirect:/departments/"+id;
    }

    @PostMapping("/deleteDepartment/{departmentId}")
    String deleteDepartment(@PathVariable("departmentId") Long id) {
        departmentService.removeDepartmentById(id);
        return "redirect:/departments/"+hospitalId;
    }

    @GetMapping("/{id}/edit")
    public String editDepartment(Model model, @PathVariable("id") Long id){
        model.addAttribute("odlDepartment", departmentService.getDepartmentById(id));
        return "department/update";
    }

    @PostMapping("/{id}/update")
    public String updateDepartment(@PathVariable("id") Long id,  Department newDepartment){
        departmentService.updateDepartment(id, newDepartment);
        return "redirect:/departments/"+hospitalId;
    }

}