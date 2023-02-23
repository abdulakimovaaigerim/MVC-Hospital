package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Hospital;
import peaksoft.repositories.HospitalRepository;

import java.util.List;

@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveHospital(Hospital hospital) {
        entityManager.persist(hospital);
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return entityManager.find(Hospital.class, id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return entityManager.createQuery("select  h from Hospital  h", Hospital.class).getResultList();
    }

    @Override
    public void updateHospital(Long hospitalId, Hospital newHospital) {
       Hospital ollHospital= entityManager.find(Hospital.class,hospitalId);
       ollHospital.setName(newHospital.getName());
       ollHospital.setAddress(newHospital.getAddress());
       entityManager.merge(ollHospital);
    }


    @Override
    public void removeHospitalById(Hospital hospital) {
        entityManager.remove(entityManager.contains(hospital) ? hospital : entityManager.merge(hospital));
    }

}
