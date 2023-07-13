package person.info.app.system.repositoryImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * DTO and DB fields for Person Info App.
 */
@Data
@Entity
@Table(name = "PERSON")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    /**
     * ID of Item stored in DB.
     */
    @Id
    @Column(name = "ID")
    @NotNull(message = "ID cannot be null")
    private String id;

    /**
     * Type of the item.
     */
    @Column(name = "PERSONAL_ID")
    @NotNull(message = "Personal ID cannot be null")
    private String personalId;

    /**
     * Person Name.
     */
    @Column(name = "NAME")
    @NotNull(message = "Name cannot be null")
    private String name;

    /**
     * Person Gender.
     */
    @Column(name = "GENDER")
    @NotNull(message = "Gender cannot be null")
    private String gender;

    /**
     * Date of Birth.
     */
    @Column(name = "DATE_OF_BIRTH")
    private String birthDate;

    /**
     * Phone number.
     */
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    /**
     * E-mail address.
     */
    @Column(name = "EMAIL_ADDRESS")
    private String email;

    @Override
    public String toString() {
        return "Person{" + "ID: " + id +
                ", Personal ID: '" + personalId + '\'' +
                ", Name: '" + name + '\'' +
                ", Gender: '" + gender + '\'' +
                ", Date of Birth: '" + birthDate + '\'' +
                ", Phone Nr.: ' " + phoneNumber + '\'' +
                ", E-mail: '" + email + '\'' +
                '}';
    }
}
