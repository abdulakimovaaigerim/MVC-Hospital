package peaksoft.contoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Appointment;
import peaksoft.servies.AppointmentService;
import peaksoft.servies.DepartmentService;
import peaksoft.servies.DoctorService;
import peaksoft.servies.PatientService;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final DepartmentService departmentService;


    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, DepartmentService departmentService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.departmentService = departmentService;
    }

    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable Long hospitalId, Model model){
        model.addAttribute("appointments", appointmentService.getAllAppointment(hospitalId));
        return "appointment/getAllAppointment";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable("hospitalId") Long hospitalId, Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("newAppointment", appointment);
        model.addAttribute("doctors", doctorService.getAllDoctor(hospitalId));
        model.addAttribute("patients", patientService.getAllPatient(hospitalId));
        model.addAttribute("departments", departmentService.getAllDepartment(hospitalId));
        model.addAttribute("hospitalId",hospitalId);
        return "appointment/savePage";
    }

    @PostMapping("/{hospitalId}/save")
    public String save(@PathVariable("hospitalId") Long hospitalId, @ModelAttribute("newAppointment") Appointment appointment){
        appointmentService.saveAppointment(hospitalId, appointment);
        return "redirect:/appointments/"+hospitalId;
    }

    @GetMapping("/{hospitalId}/{appointmentId}/delete")
    public String delete(@PathVariable("hospitalId") Long hospitalId,@PathVariable("appointmentId") Long appointmentId){
        appointmentService.removeAppointmentById(appointmentId,hospitalId);
        return "redirect:/appointments/"+hospitalId;
    }

    @GetMapping("/{hospitalId}/{appointmentId}/edit")
    public String edit(@PathVariable("appointmentId")Long id, @PathVariable("hospitalId")Long hospitalId, Model model){
        model.addAttribute("appointment",appointmentService.getAppointmentById(id));
        model.addAttribute("hospitalId",hospitalId);
        return "appointment/update";
    }

    @PostMapping("/{hospitalId}/{appointmentId}/update")
    public String update(@PathVariable("hospitalId")Long hospitalId,@PathVariable("appointmentId")Long appointmentId,@ModelAttribute("appointment") Appointment appointment){
        appointmentService.updateAppointment(appointmentId,appointment);
        return "redirect:/appointments/"+hospitalId;
    }

}
