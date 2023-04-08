package med.voll.api.controller;

import med.voll.api.domain.appointment.DataDetailsAppointment;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.appointment.ScheduleAppointment;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataScheduleAppointment> dataScheduleAppointmentJson;
    @Autowired
    private JacksonTester<DataDetailsAppointment> dataDetailsAppointmentJson;

    @MockBean
    private ScheduleAppointment scheduleAppointment;

    @Test
    @DisplayName("Invalid information. Return status code 400")
    @WithMockUser
    void scheduleStatusCode400() throws Exception {
        //Act
        var response = mvc.perform(post("/appointments")).andReturn().getResponse();

        //Assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Valid information. Return status code 200")
    @WithMockUser
    void scheduleStatusCode200() throws Exception {
        //Arrange
        var date = LocalDateTime.now().plusHours(1);
        var detailsData = new DataDetailsAppointment(null, 1l, 2l, date);
        when(scheduleAppointment.schedule(any())).thenReturn(detailsData);
        var responseJson = dataDetailsAppointmentJson.write(detailsData).getJson();

        //Act
        var response = mvc.perform(
                post("/appointments").contentType(MediaType.APPLICATION_JSON)
                        .content(dataScheduleAppointmentJson.write(
                                new DataScheduleAppointment(
                                        1l,
                                        2l,
                                        date,
                                        Specialty.DERMATOLOGIA))
                                .getJson()))
                .andReturn()
                .getResponse();

        //Assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(responseJson);
    }
}
