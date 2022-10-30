package com.triple.travel.travel;


import com.triple.travel.tripleCity.TripleCityController;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TravelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void before() throws Exception {
        String userInfo = """
                { "userId": "JYPARK", "userName": "박준영"}
                """;
        mockMvc.perform(
                post("/v1/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(userInfo)
        );

        {
            String content = """
              {
                           "tripleCitySeq": 1,
                           "userId": "JYPARK",
                           "travelTitle": "미국여행" ,
                           "travelStyle": "배낭여행" ,
                           "travelStartDate":"2022-10-15 10:15:00",
                           "travelEndDate":"2022-12-15 23:30:00"
                       }
              """;
            ResultActions result = mockMvc.perform(
                    post("/v1/api/travel/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(content)
            );
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(handler().handlerType(TravelController.class))
                    .andExpect(handler().methodName("save"))
                    .andExpect(jsonPath("$.success",equalTo("OK")));
        }
    }

    @AfterEach
    public void after() throws Exception {
    }

/** 
* 
* Method: save(@RequestBody @Valid TravelRequestDto tripleCityRequestDto) 
* 
*/ 
@Test
@DisplayName("여행 등록 ")
public void testSave() throws Exception {
    String content = """
              {
                        
                           "tripleCitySeq": 2,
                           "userId": "JYPARK",
                           "travelTitle": "국내여행 다시하기" ,
                           "travelCompanion": "김길동" ,
                           "travelStyle": "휴양지" ,
                           "travelStartDate":"2022-12-16 16:15:00",
                           "travelEndDate":"2022-12-20 20:30:00"
                       }
              """;
    ResultActions result = mockMvc.perform(
            post("/v1/api/travel/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content)
    );
    result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(TravelController.class))
            .andExpect(handler().methodName("save"))
            .andExpect(jsonPath("$.success",equalTo("OK")));
} 

/** 
* 
* Method: modify(@RequestBody TravelRequestDto tripleCityRequestDto) 
* 
*/ 
@Test
public void testModify() throws Exception {
    String content = """
              {
                            "travelId": 1,
                           "tripleCitySeq": 1,
                           "userId": "JYPARK",
                           "travelTitle": "비행기 예약 전" ,
                           "travelStartDate":"2023-01-15 10:15:00",
                           "travelEndDate":"2023-02-15 23:30:00"
                       }
              """;
    ResultActions result = mockMvc.perform(
            put("/v1/api/travel/modify")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content)
    );
    result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(TravelController.class))
            .andExpect(handler().methodName("modify"))
            .andExpect(jsonPath("$.success",equalTo("OK")));
} 

/** 
* 
* Method: get(@PathVariable Long travelId) 
* 
*/ 
@Test
public void testGet() throws Exception {
    ResultActions result = mockMvc.perform(
            get("/v1/api/travel/get/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)

    );
    result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(TravelController.class))
            .andExpect(handler().methodName("get"))
            .andExpect(jsonPath("$.success",equalTo("OK")));
}

/**
* 
* Method: delete(@PathVariable Long travelId) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    ResultActions result = mockMvc.perform(
            delete("/v1/api/travel/delete/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)

    );
    result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(TravelController.class))
            .andExpect(handler().methodName("delete"))
            .andExpect(jsonPath("$.success",equalTo("OK")));
} 


} 
