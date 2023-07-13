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
@ApiModel(description = "Get Person by ID request")
public class GetPersonByPersonalIdRequest {

    @ApiModelProperty(notes = "Personal ID")
    @NotNull
    private String personalId;
}
