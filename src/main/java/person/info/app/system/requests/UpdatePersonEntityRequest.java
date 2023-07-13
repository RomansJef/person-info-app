package person.info.app.system.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Update Person entity request")
public class UpdatePersonEntityRequest {

    @ApiModelProperty(notes = "Personal ID")
    @NotNull
    private String personalId;

    String name;
    String gender;
    String birthDate;
    String phoneNumber;
    String email;
}

