package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.DataAddress;

public record DataToRegisterADoctor(
        @NotBlank(message = "{nome.required}}")
        String name,
        @NotBlank(message = "{email.required}}")
        @Email(message = "{email.invalid}}")
        String email,
        @NotBlank(message = "{telefone.required}}")
        String telefone,
        @NotBlank(message = "{crm.required}}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalid}")
        String crm,
        @NotNull(message = "{especialidade.required}}")
        Specialty specialty,
        @NotNull(message = "{endereco.required}}")
        @Valid
        DataAddress address
) {
}
