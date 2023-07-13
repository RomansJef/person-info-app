package person.info.app.system.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Response getting person by Personal Id")
public class GetPersonResponse {

    @ApiModelProperty(notes = "Id")
    String id;
    @ApiModelProperty(notes = "Personal ID")
    String personalId;
    @ApiModelProperty(notes = "Name")
    String name;
    @ApiModelProperty(notes = "Gender")
    String gender;
    @ApiModelProperty(notes = "Date of Birth")
    String birthDate;
    @ApiModelProperty(notes = "Phone number")
    String phoneNumber;
    @ApiModelProperty(notes = "E-mail address")
    String email;
}
