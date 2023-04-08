package med.voll.api.domain.validation;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class SchedulingAdvanceValidation implements AppointmentValidation {

    public void validation(DataScheduleAppointment data) {
        var timeDifference = Duration.between(LocalDateTime.now(), data.date()).toMinutes();
        if (timeDifference < 30){
            throw new ValidateException("To schedule an appointment you need at least 30 minutes in advance.");
        }
    }
}
