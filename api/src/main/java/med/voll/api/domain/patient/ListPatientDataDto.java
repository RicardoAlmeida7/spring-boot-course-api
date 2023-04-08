package med.voll.api.domain.patient;

public record ListPatientDataDto(Long id, String name, String email, String cpf) {

    public ListPatientDataDto(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
