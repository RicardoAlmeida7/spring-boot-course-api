package med.voll.api.domain.validation;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class OpeningHoursValidation implements AppointmentValidation {

    public void validation(DataScheduleAppointment data){
        var isSunday = data.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isSaturday = data.date().getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var isBeforeOpening = data.date().getHour() < 7;
        var isAfterClosed = data.date().getHour() > 18;
        if(isSunday || isSaturday || isBeforeOpening || isAfterClosed){
            throw new ValidateException("Appointment outside opening hours. " +
                    "Opening hours are from 07:00 am to 19:00 pm");
        }
    }
}
