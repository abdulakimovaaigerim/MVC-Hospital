package peaksoft.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Patient;
import peaksoft.servies.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable("hospitalId")Long hospitalId , Model model) {
        System.out.println("patientService.getAllPatient(hospitalId) = " + patientService.getAllPatient(hospitalId));
        model.addAttribute("patientsByHospitalId", patientService.getAllPatient(hospitalId));
        return "patient/getAllPatient";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newPatient", new Patient());
        model.addAttribute(hospitalId);
        return "patient/savePage";
    }

    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable("hospitalId") Long hospitalId,@ModelAttribute("newPatient") Patient patient) {
        patientService.savePatient(hospitalId, patient);
        return "redirect:/patients/{hospitalId}";
    }

    @PutMapping("/{hospitalId}/{patientId}/delete")
    public String delete( @PathVariable("hospitalId") Long id,@PathVariable("patientId")Long patientId) {
        patientService.removePatientById(patientId);
        return "redirect:/patients/"+id;
    }

    @PostMapping("/{hospitalId}/{patientId}/edit")
    public String edit(Model model, @PathVariable("patientId") Long id,@PathVariable("hospitalId")Long hospitalId) {
        model.addAttribute("patients",patientService.getPatientById(id));
        model.addAttribute("hospitalId", hospitalId);
        return "patient/update";
    }

    @PostMapping("/{hospitalId}/{patientId}/update")
    public String update(@PathVariable("hospitalId") Long hospitalId,@PathVariable("patientId")Long id,@ModelAttribute("patients") Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/patients/" + hospitalId;

    }

}
