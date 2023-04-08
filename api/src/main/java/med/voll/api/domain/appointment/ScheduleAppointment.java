package med.voll.api.domain.appointment;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.validation.AppointmentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointment {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private List<AppointmentValidation> validations;

    public DataDetailsAppointment schedule(DataScheduleAppointment data) {

        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidateException("Patient ID does not exist.");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidateException("Dooctor ID does not exist.");
        }

        validations.forEach(v -> v.validation(data));

        var patient = patientRepository.findById(data.idPatient()).get();
        var doctor = chooseDoctor(data);
        if (doctor == null){
            throw new ValidateException("Doctor unavailable.");
        }
        var appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);

        return new DataDetailsAppointment(appointment);
    }

    private Doctor chooseDoctor(DataScheduleAppointment data) {
        if (data.idDoctor() != null) {
            return doctorRepository.findById(data.idDoctor()).get();
        }

        if(data.specialty() == null){
            throw new ValidateException("When the doctor is not selected, the specialty must be informed.");
        }
        return doctorRepository.chooseFreeRandomDoctorOnDate(data.specialty(), data.date());
    }
}
