package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDateTime(Long idDoctor, LocalDateTime date);

    Boolean existsByPatientIdAndDateTimeBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);
}
