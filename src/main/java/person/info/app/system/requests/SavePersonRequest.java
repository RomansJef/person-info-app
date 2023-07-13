package person.info.app.system.requests;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavePersonRequest {

    @ApiModelProperty(notes = "Personal ID")
    @NotBlank
    private String personalId;

    @ApiModelProperty(notes = "Name")
    @NotBlank
    private String name;

    @ApiModelProperty(notes = "Gender")
    @NotNull
    private String gender;

    @ApiModelProperty(notes = "Date of birth")
    private String birthDate;

    @ApiModelProperty(notes = "Phone number")
    private String phoneNumber;

    @ApiModelProperty(notes = "E-mail address")
    private String email;
}

