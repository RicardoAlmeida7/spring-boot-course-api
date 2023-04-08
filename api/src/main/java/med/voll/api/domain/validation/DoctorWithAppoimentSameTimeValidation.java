package med.voll.api.domain.validation;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAppoimentSameTimeValidation implements AppointmentValidation{

    @Autowired
    private AppointmentRepository repository;

    public void validation(DataScheduleAppointment data){
        var doctorHasAppointmentThisTime = repository.existsByDoctorIdAndDateTime(data.idDoctor(), data.date());
        if(doctorHasAppointmentThisTime){
            throw new ValidateException("This doctor already has an appointment this hour.");
        }
    }
}
