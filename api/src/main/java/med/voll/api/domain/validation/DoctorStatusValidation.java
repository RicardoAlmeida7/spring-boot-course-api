package med.voll.api.domain.validation;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorStatusValidation implements AppointmentValidation {

    @Autowired
    private DoctorRepository repository;

    public void validation(DataScheduleAppointment data) {
        if (data.idDoctor() == null)
            return;

        var doctorIsActive = repository.findActiveStatusbyId(data.idDoctor());
        if(!doctorIsActive){
            throw new ValidateException("Doctor is inactive!");
        }
    }
}
