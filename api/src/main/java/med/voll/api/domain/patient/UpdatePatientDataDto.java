package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.Address;

public record UpdatePatientDataDto(
        @NotNull
        Long id,
        String name,
        String telefone,
        Address address) {
}
