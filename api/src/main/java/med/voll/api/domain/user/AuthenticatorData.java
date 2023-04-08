package med.voll.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticatorData(
        @Email
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
