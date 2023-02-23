package peaksoft.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Doctor;
import peaksoft.servies.DepartmentService;
import peaksoft.servies.DoctorService;
import peaksoft.servies.HospitalService;


@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    public DoctorController(DoctorService doctorService, HospitalService hospitalService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
    }

    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable Long hospitalId,Model model){
        model.addAttribute("doctors",doctorService.getAllDoctor(hospitalId));
        return "doctor/getAllDoctors";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable("hospitalId")Long hospitalId,Model model){
        model.addAttribute("newDoctor",new Doctor());
        model.addAttribute(hospitalId);
        model.addAttribute("departments",hospitalService.getHospitalById(hospitalId));
        return "doctor/newDoctor";
    }

    @PostMapping("/{hospitalId}/save")
    public String save(@PathVariable Long hospitalId,@ModelAttribute("newDoctor") Doctor doctor ){
        doctorService.saveDoctor(hospitalId,doctor);
        return"redirect:/doctors/"+hospitalId;
    }

    @PostMapping("/{hospitalId}/{doctorId}/delete")
    public String delete(@PathVariable("hospitalId")Long hospitalId,@PathVariable("doctorId")Long doctorId){
        doctorService.removeDoctorById(doctorId);
        return "redirect:/doctors/"+hospitalId;
    }

    @GetMapping("/{hospitalId}/{doctorId}/edit")
    public String edit(@PathVariable("doctorId")Long id,@PathVariable("hospitalId")Long hospitalId, Model model){
        model.addAttribute("doctor",doctorService.getDoctorById(id));
        model.addAttribute("hospitalId",hospitalId);
        return "doctor/update";
    }

    @PostMapping("/{hospitalId}/{doctorId}/update")
    public String update(@PathVariable("hospitalId")Long hospitalId,@PathVariable("doctorId")Long doctorId,@ModelAttribute("doctor")Doctor doctor){
        doctorService.updateDoctor(doctorId,doctor);
        return "redirect:/doctors/"+hospitalId;
    }

}