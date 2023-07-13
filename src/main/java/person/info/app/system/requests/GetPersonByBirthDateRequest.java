package person.info.app.system.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Get Person by Birth Date request")
public class GetPersonByBirthDateRequest {

    @ApiModelProperty(notes = "Birth Date")
    @NotBlank
    private String birthDate;
}
