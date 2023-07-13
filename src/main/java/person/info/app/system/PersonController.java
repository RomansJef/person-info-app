package person.info.app.system;

import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import person.info.app.system.request.*;
import person.info.app.system.response.GetFullPersonListResponse;
import person.info.app.system.response.GetPersonResponse;
import person.info.app.system.response.PersonServiceResponse;
import person.info.app.system.service.*;

import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Person Info App.
 */
@Log4j2
@RestController
@Tag(name = "Person App Controller")
public class PersonController {
    private final SavePersonService savePersonService;
    private final GetFullPersonListService getFullPersonListService;
    private final GetPersonByPersonalIdService getPersonByPersonalIdService;
    private final GetPersonByBirthDateService getPersonByBirthDateService;
    private final DeletePersonService deletePersonService;
    private final UpdatePersonService updatePersonService;

    public PersonController(SavePersonService savePersonService,
                            GetFullPersonListService getFullPersonListService,
                            GetPersonByPersonalIdService getPersonByPersonalIdService,
                            GetPersonByBirthDateService getPersonByBirthDateService,
                            DeletePersonService deletePersonService,
                            UpdatePersonService updatePersonService) {
        this.savePersonService = savePersonService;
        this.getFullPersonListService = getFullPersonListService;
        this.getPersonByPersonalIdService = getPersonByPersonalIdService;
        this.getPersonByBirthDateService = getPersonByBirthDateService;
        this.deletePersonService = deletePersonService;
        this.updatePersonService = updatePersonService;
    }

    /**
     * Save new Person.
     */
    //
    @Operation(summary = "Save new Person", description = "Save new Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person saved successfully"),
            @ApiResponse(responseCode = "500", description = "Error: Person was not saved")
    })
    @PostMapping(value = "/newperson", consumes = "application/json")
    public PersonServiceResponse postNewPerson(
        @ApiParam(value = "New Person entity request data body")
        @RequestBody @Valid SavePersonRequest request) {
            return savePersonService.executeRequest(request);
    }

    /**
     * Get Full Person List from Repository.
     */
    @Operation(summary = "Get Full Person List", description = "Get Full Person List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person list retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Error retrieving Person list")
    })
    @GetMapping(value = "/fullpersonlist", produces = "application/json")
    public GetFullPersonListResponse getFullPersonList() {
        return getFullPersonListService.executeRequest();
    }

    /**
     * Get Person by Personal ID.
     */
    @Operation(summary = "Get Person By PersonalId", description = "Get Person By PersonalId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Error retrieving Person by PersonalId")
    })
    @PostMapping(value = "/personbypersonalid",consumes = "application/json", produces = "application/json")
    public GetPersonResponse getPersonById(
            @ApiParam(value = "Get Person entity by Id request data body")
            @RequestBody @Valid GetPersonByPersonalIdRequest requestById) {
        return getPersonByPersonalIdService.executeRequest(requestById);
    }

    /**
     * Get Person by Date of Birth.
     */
    @Operation(summary = "Get Person By Birth Date", description = "Get Person By Birth Date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Error retrieving Person by Birth date")
    })
    @PostMapping(value = "/personbybirthdate",consumes = "application/json", produces = "application/json")
    public GetPersonResponse getPersonByBirthDate(
            @ApiParam(value = "Get Person entity by Id request data body")
            @RequestBody @Valid GetPersonByBirthDateRequest requestByBD) {
        return getPersonByBirthDateService.executeRequest(requestByBD);
    }

    /**
     * Delete Person entity.
     */
    @Operation(summary = "Delete Person entity", description = "Delete Person entity by PersonalId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Error deleting Person by PersonalId")
    })
    @PostMapping(value = "/deleteperson",consumes = "application/json", produces = "application/json")
    public PersonServiceResponse deleteItemById(
            @ApiParam(value = "Item by Id request data body")
            @RequestBody @Valid DeletePersonByIdRequest deleteById) {
        return deletePersonService.executeRequest(deleteById);
    }

    /**
     * Update Person entity.
     */
    @Operation(summary = "Update Person entity", description = "Update Person entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated successfully"),
            @ApiResponse(responseCode = "500", description = "Error updating Person by PersonalId")
    })
    @PatchMapping(value = "/updateperson",consumes = "application/json", produces = "application/json")
    public PersonServiceResponse updatePersonEntity(
            @ApiParam(value = "Patch item request data body")
            @RequestBody @Valid UpdatePersonEntityRequest updatePersonRequest) {
        return updatePersonService.executeRequest(updatePersonRequest);
    }
}
