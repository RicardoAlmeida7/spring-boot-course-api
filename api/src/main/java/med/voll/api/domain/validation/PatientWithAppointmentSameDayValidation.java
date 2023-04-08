package med.voll.api.domain.validation;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithAppointmentSameDayValidation implements AppointmentValidation {

    @Autowired
    private AppointmentRepository repository;

    public void validation(DataScheduleAppointment data){
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var patientAlreadyHasAppointmentSameDay = repository.existsByPatientIdAndDateTimeBetween(data.idPatient(), firstHour, lastHour);
        if (patientAlreadyHasAppointmentSameDay){
            throw new ValidateException("Patient already has appointment this day.");
        }
    }

}
