package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveStatusTrue(Pageable pageable);

    @Query("""
                select m from Doctor m
                where
                m.activeStatus = 1
                and
                m.specialty = :specialty
                and
                m.id not in(
                        select c.doctor.id from Appointment c
                        where
                        c.dateTime = :date
                )
                order by rand()
                limit 1
                """)
    Doctor chooseFreeRandomDoctorOnDate(Specialty specialty, LocalDateTime date);

    @Query("""
            SELECT d.activeStatus
            FROM Doctor d
            WHERE d.id = :idDoctor
            """)
    Boolean findActiveStatusbyId(Long idDoctor);
}
