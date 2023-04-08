package med.voll.api.domain.doctor;

public record DoctorDataList(Long id, String nome, String email, String crm, Specialty especialidade) {
    public DoctorDataList(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
