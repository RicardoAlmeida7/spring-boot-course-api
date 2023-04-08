package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveStatusTrue(Pageable pageable);

    @Query("""
            SELECT p.activeStatus
            FROM Patient p
            WHERE p.id = :idDoctor
            """)
    Boolean findActiveStatusbyId(Long idDoctor);
}
