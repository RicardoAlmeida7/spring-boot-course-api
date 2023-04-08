package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Address address;
    private Boolean activeStatus;

    public Patient(RegisterPatientDataDto data){
        this.activeStatus = true;
        this.name = data.name();
        this.email = data.email();
        this.telefone = data.telefone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void updatePatient(UpdatePatientDataDto data){
        if (data.name() != null){
            this.name = data.name();
        }
        if(data.telefone() != null){
            this.telefone = data.telefone();
        }
        if(data.address() != null){
            this.address = data.address();
        }
    }

    public void disable(){
        this.activeStatus = false;
    }

}
