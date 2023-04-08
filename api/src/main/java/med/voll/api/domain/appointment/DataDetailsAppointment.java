package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DataDetailsAppointment(
        Long id,
        Long idDoctor,
        Long idPatient,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date
) {
    public DataDetailsAppointment(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(),appointment.getPatient().getId(),appointment.getDateTime());
    }
}
