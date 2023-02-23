package peaksoft.configuration.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "hospitals")
@NoArgsConstructor
@Getter
@Setter
public class Hospital {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "department_id_gen")
    @SequenceGenerator(name = "department_id_gen", sequenceName = "department_id_seq", allocationSize = 10)
    private Long id;
    private String name;
    private String address;
    @OneToMany(cascade = {ALL}, fetch = LAZY)
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "hospital", fetch = LAZY)
    private List<Patient> patients;
    @OneToMany(cascade = {ALL}, fetch = LAZY)
    private List<Department> departments;
    @OneToMany(cascade = {ALL}, fetch = LAZY)
    private List<Appointment> appointments;

}
