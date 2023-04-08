package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private  String telefone;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    private Boolean activeStatus;

    public Doctor(DataToRegisterADoctor data) {
        this.activeStatus = true;
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.telefone = data.telefone();
        this.address = new Address(data.address());
        this.specialty = data.specialty();
    }

    public void updateData(DataToUpdateADoctor data) {
        if(data.name() != null)
            this.name = data.name();

        if(data.telefone() != null)
            this.telefone = data.telefone();

        if(data.address() != null)
            this.address.update(data.address());
    }

    public void disable() {
        this.activeStatus = false;
    }
}
