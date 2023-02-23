package peaksoft.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "department_id_gen")
    @SequenceGenerator(name = "department_id_gen", sequenceName = "department_id_seq", allocationSize = 1)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Doctor> doctors;
    public void addDoctor(Doctor doctor){
        if (doctors == null){
            doctors = new ArrayList<>();
        }else {
            doctors.add(doctor);
        }

    }

    @ManyToOne(cascade = {DETACH,REFRESH,MERGE,PERSIST}, fetch = FetchType.LAZY)
    private Hospital hospital;

    public Department(String name) {
        this.name = name;

    }

    public void setDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }

}
