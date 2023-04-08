package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DoctorDetailDataDto(
        Long id,
        String name,
        String email,
        String crm,
        String telefone,
        Specialty specialty,
        Address address
) {

    public  DoctorDetailDataDto(Doctor doctor){
        this(doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getCrm(),
                doctor.getSpecialty(),
                doctor.getAddress());
    }
}
