package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record DetailsPatientDataDto(Long id, String name, String email, String cpf, String telefone, Address address) {

    public DetailsPatientDataDto(Patient patient){
        this(patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getCpf(),
                patient.getTelefone(),
                patient.getAddress());
    }
}
