package med.voll.api.domain.doctor;

import med.voll.api.domain.address.DataAddress;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.RegisterPatientDataDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;
    @Test
    @DisplayName("Choose doctor with scheduled appointment, return must be null")
    void chooseFreeRandomDoctorOnDateIsNull() {
        //Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = saveDoctor("medico", "medico@vollmed.com","123456", Specialty.CARDIOLOGIA);
        var patient = savePatient("patient", "patient@gmail.com", "111.111.111-11");
        registerAppointment(doctor, patient, nextMondayAt10);

        //Act
        var freeDoctor = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);

        //Assert
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Choose available doctor")
    void chooseFreeRandomDoctorOnDateSuccess() {
        //Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = saveDoctor("medico", "medico@vollmed.com","123456", Specialty.CARDIOLOGIA);

        //Act
        var freeDoctor = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);

        //Assert
        assertThat(freeDoctor).isEqualTo(doctor);
    }
    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor saveDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(dataSaveDoctor(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient savePatient(String name, String email, String cpf) {
        var patient = new Patient(dataSavePatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DataToRegisterADoctor dataSaveDoctor(String name, String email, String crm, Specialty specialty) {
        return new DataToRegisterADoctor(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                dataAddress()
        );
    }

    private RegisterPatientDataDto dataSavePatient(String name, String email, String cpf) {
        return new RegisterPatientDataDto(
                name,
                email,
                "61999999999",
                cpf,
                dataAddress()
        );
    }

    private DataAddress dataAddress() {
        return new DataAddress(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}