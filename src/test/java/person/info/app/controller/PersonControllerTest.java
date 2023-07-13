package person.info.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import person.info.app.system.PersonController;
import person.info.app.system.repositoryImpl.PersonEntity;
import person.info.app.system.request.*;
import person.info.app.system.response.GetFullPersonListResponse;
import person.info.app.system.response.GetPersonResponse;
import person.info.app.system.response.PersonServiceResponse;
import person.info.app.system.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {
    private final SavePersonService savePersonService = mock(SavePersonService.class);
    private final GetFullPersonListService getFullPersonListService = mock(GetFullPersonListService.class);
    private final GetPersonByPersonalIdService getPersonByPersonalIdService = mock(GetPersonByPersonalIdService.class);
    private final GetPersonByBirthDateService getPersonByBirthDateService = mock(GetPersonByBirthDateService.class);
    private final DeletePersonService deletePersonService = mock(DeletePersonService.class);
    private final UpdatePersonService updatePersonService = mock(UpdatePersonService.class);

    public final PersonController personController =
            new PersonController(savePersonService, getFullPersonListService, getPersonByPersonalIdService, getPersonByBirthDateService, deletePersonService, updatePersonService);
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new Exception(), personController).build();
    }

    @Test
    public void saveNewPersonControllerTest() throws Exception {
        // request
        SavePersonRequest savePersonRequest =
                SavePersonRequest.builder()
                        .personalId("1234567-1122")
                        .name("Xi Xo")
                        .gender("Male")
                        .birthDate("1990-01-01")
                        .phoneNumber("+6824080143")
                        .email("testx@test.com")
                        .build();

        // response
        PersonServiceResponse personServiceResponse = PersonServiceResponse.builder()
                .status("Person " + savePersonRequest.getName() + " saved successfully")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        //when
        when(savePersonService.executeRequest(savePersonRequest)).thenReturn(personServiceResponse);

        // then
        mockMvc
                .perform(
                        post("/newperson")
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(savePersonRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").exists())
                .andDo(print());
    }

    @Test
    public void deletePersonControllerTest() throws Exception {
        // request
        DeletePersonByIdRequest deletePersonRequest =
                DeletePersonByIdRequest.builder()
                        .personalId("1234567-1122")
                        .build();

        // response
        PersonServiceResponse personServiceResponse = PersonServiceResponse.builder()
                .status("Person " + deletePersonRequest.getPersonalId() + " deleted")
                .build();
        ObjectMapper mapper = new ObjectMapper();

        //when
        when(deletePersonService.executeRequest(deletePersonRequest)).thenReturn(personServiceResponse);

        // then
        mockMvc
                .perform(
                        post("/deleteperson")
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(deletePersonRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").exists())
                .andDo(print());
    }

    @Test
    public void getFullPersonListControllerTest() throws Exception {
        // response
        PersonEntity personEntity = PersonEntity.builder()
                .id("580425d2-wuyt-4b56-b244-5fec900a8741")
                .personalId("1234567-1122")
                .name("Xi Xo")
                .gender("Male")
                .birthDate("1990-01-01")
                .phoneNumber("+6824080143")
                .email("testx@test.com")
                .build();
        List<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity);

        GetFullPersonListResponse getFullPersonListResponse = GetFullPersonListResponse.builder()
                .personEntityList(personEntityList)
                .build();

        //when
        when(getFullPersonListService.executeRequest())
                .thenReturn(getFullPersonListResponse);

        // then
        mockMvc
                .perform(
                        get("/fullpersonlist")
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.personEntityList").exists())
                .andDo(print());
    }

    @Test
    public void getPersonByPIDControllerTest() throws Exception {
        // request
        GetPersonByPersonalIdRequest getPersonByPersonalIdRequest =
                GetPersonByPersonalIdRequest.builder()
                        .personalId("1234567-1122")
                        .build();

        // response
        GetPersonResponse getPersonResponse = GetPersonResponse.builder()
                .id("580425d2-wuyt-4b56-b244-5fec900a8741")
                .personalId("1234567-1122")
                .name("Xi Xo")
                .gender("Male")
                .birthDate("1990-01-01")
                .phoneNumber("+6824080143")
                .email("testx@test.com")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        //when
        when(getPersonByPersonalIdService.executeRequest(getPersonByPersonalIdRequest))
                .thenReturn(getPersonResponse);

        // then
        mockMvc
                .perform(
                        post("/personbypersonalid")
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getPersonByPersonalIdRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void getPersonByBDControllerTest() throws Exception {
        // request
        GetPersonByBirthDateRequest getPersonByBirthDateRequest =
                GetPersonByBirthDateRequest.builder()
                        .birthDate("1990-01-01")
                        .build();

        // response
        GetPersonResponse getPersonResponse = GetPersonResponse.builder()
                .id("580425d2-wuyt-4b56-b244-5fec900a8741")
                .personalId("1234567-1122")
                .name("Xi Xo")
                .gender("Male")
                .birthDate("1990-01-01")
                .phoneNumber("+6824080143")
                .email("testx@test.com")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        //when
        when(getPersonByBirthDateService.executeRequest(getPersonByBirthDateRequest))
                .thenReturn(getPersonResponse);

        // then
        mockMvc
                .perform(
                        post("/personbybirthdate")
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getPersonByBirthDateRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void updatePersonControllerTest() throws Exception {
        // request
        UpdatePersonEntityRequest updatePersonRequest =
                UpdatePersonEntityRequest.builder()
                        .personalId("1234567-1122")
                        .name("Xing Xo")
                        .build();
        // response
        PersonServiceResponse personServiceResponse = PersonServiceResponse.builder()
                .status("Person " + updatePersonRequest.getPersonalId() + " updated successfully")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        //when
        when(updatePersonService.executeRequest(updatePersonRequest))
                .thenReturn(personServiceResponse);

        // then
        mockMvc
                .perform(
                        patch("/updateperson")
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(updatePersonRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").exists())
                .andDo(print());
    }
}
