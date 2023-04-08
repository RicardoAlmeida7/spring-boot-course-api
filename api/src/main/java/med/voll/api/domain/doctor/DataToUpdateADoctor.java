package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.DataAddress;

public record DataToUpdateADoctor(
        @NotNull
        Long id,
        String name,
        String telefone,
        DataAddress address
) {
}
