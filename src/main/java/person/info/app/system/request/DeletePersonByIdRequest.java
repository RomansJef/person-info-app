package person.info.app.system.request;

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
@ApiModel(description = "Request deleting item")
public class DeletePersonByIdRequest {

    @ApiModelProperty(notes = "Personal ID")
    @NotNull
    private String personalId;
}
