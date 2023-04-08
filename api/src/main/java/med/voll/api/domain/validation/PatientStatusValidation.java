package med.voll.api.domain.validation;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientStatusValidation implements AppointmentValidation {

    @Autowired
    private PatientRepository repository;

    public void validation(DataScheduleAppointment data){
        if (data.idDoctor() == null)
            return;

        var patientIsActive = repository.findActiveStatusbyId(data.idPatient());
        if(!patientIsActive){
            throw new ValidateException("Doctor is inactive!");
        }
    }
}
