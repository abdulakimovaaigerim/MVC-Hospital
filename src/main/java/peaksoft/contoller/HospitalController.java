package peaksoft.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Hospital;
import peaksoft.servies.HospitalService;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String getAllHospitals(Model model){
        model.addAttribute("allHospitals", hospitalService.getAllHospital());
        return "hospital/getAllHospitals";
    }


    @GetMapping("/new")
    public String createHospital(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "hospital/newHospital";
    }

    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital")Hospital hospital){
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @RequestMapping("/delete")
    public String deleteById(@RequestParam("hospitalId") Long id) {
        hospitalService.removeHospitalById(hospitalService.getHospitalById(id));
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}/edit")
    public String editHospital(Model model, @PathVariable("id") Long id){
        model.addAttribute("odlHospital", hospitalService.getHospitalById(id));
        return "hospital/updateHospital";
    }

    @PostMapping("/{id}/update")
    public String updateHospital(@PathVariable("id") Long id, @ModelAttribute("hospital") Hospital newHospital){
        hospitalService.updateHospital(id, newHospital);
        return "redirect:/hospitals";
    }

}
