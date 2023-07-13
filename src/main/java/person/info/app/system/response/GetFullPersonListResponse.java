package person.info.app.system.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import person.info.app.system.repositoryImpl.PersonEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Response getting all Person entities from repository")
public class GetFullPersonListResponse {

    @ApiModelProperty(notes = "List of Person entities")
    private transient List<PersonEntity> personEntityList;
}
