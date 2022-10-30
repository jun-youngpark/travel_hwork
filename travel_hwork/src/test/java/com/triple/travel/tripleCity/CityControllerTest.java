package com.triple.travel.tripleCity;

import com.triple.travel.common.ResponseStatus;
import com.triple.travel.travel.TravelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @DisplayName("도시 사전 등록 ")
    void Before() throws Exception {
      String content = """
              {
                           "tripleCityName": "서울",
                           "refCityCode": "KOR_SEO",
                           "tripleCityEngName": "seoul",
                           "categoryCode": "KOR",
                           "tripleCityIntro": " 살기 좋고 여행하기 좋은 나라입니다."
                       }
              """;
        ResultActions result = mockMvc.perform(
                post("/v1/api/city/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TripleCityController.class))
                .andExpect(handler().methodName("save"))
                .andExpect(jsonPath("$.success",equalTo("OK")));
    }

    @BeforeEach
    @DisplayName("도시 사전 등록 ")
    void testSave() throws Exception {
        String content = """
              {
                           "tripleCityName": "인천",
                           "refCityCode": "KOR_INC",
                           "tripleCityEngName": "inchen",
                           "categoryCode": "KOR",
                           "tripleCityIntro": "인천항과 인천국제공항을 중심으로 제조업과 물류 산업이 발달된 도시"
                       }
              """;
        ResultActions result = mockMvc.perform(
                post("/v1/api/city/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TripleCityController.class))
                .andExpect(handler().methodName("save"))
                .andExpect(jsonPath("$.success",equalTo("OK")));
    }

    /*#@Test
    @DisplayName("도시 등록 실패 테스트")
    void testSaveFail() throws Exception {
        String content = """
              {
                           
                           "refCityCode": "KOR_SEO",
                           "tripleCityEngName": "seoul",
                           "categoryCode": "KOR",
                           "tripleCityIntro": " 살기 좋고 여행하기 좋은 나라입니다."
                       }
              """;
        ResultActions result = mockMvc.perform(
                post("/v1/api/city/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content)
        );
        result.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(TripleCityController.class))
                .andExpect(handler().methodName("save"))
                .andExpect(jsonPath("$.success",equalTo("FAIL")));
    }
    */

    @Test
    @DisplayName("도시 수정 테스트")
    void testModify() throws Exception {
        String content =  """
                    {
                        "tripleCitySeq": 1,
                        "tripleCityName": "인천",
                        "refCityCode": "KOR_INC",
                        "tripleCityEngName": "inchen",
                        "categoryCode": "KOR",
                        "tripleCityIntro": "인천 앞바다가 있습니다."
                    }
        """;

         ResultActions result = mockMvc.perform(
                put("/v1/api/city/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TripleCityController.class))
                .andExpect(handler().methodName("modify"))
                .andExpect(jsonPath("$.success",equalTo("OK")));

    }

    /*
    @Test
    @DisplayName("도시 수정 실패 테스트")
    void testModifyFail() throws Exception {
        String content =  """
                   {
                        "tripleCitySeq": 1,
                        "tripleCityName": "인천",
                        "refCityCode": "KOR_INC",
                        "tripleCityEngName": "inchen",
                        "categoryCode": "KOR",
                        "tripleCityIntro": "인천 앞바다가 있습니다."
                    }
        """;
        ResultActions result = mockMvc.perform(
                post("/v1/api/city/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        result.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(handler().handlerType(TripleCityController.class))
                .andExpect(handler().methodName("modify"))
                .andExpect(jsonPath("$.success",equalTo("FAIL")));
    }
*/

    @Test
    @DisplayName("도시 단일 조회")
    public void testGet() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/v1/api/city/get/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TripleCityController.class))
                .andExpect(handler().methodName("get"))
                .andExpect(jsonPath("$.success",equalTo("OK")));
    }

    /**
     *
     * Method: delete(@PathVariable Long travelId)
     *
     */
    @Test
    @DisplayName("도시 삭제")
    public void testDelete() throws Exception {
        ResultActions result = mockMvc.perform(
                delete("/v1/api/city/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(TripleCityController.class))
                .andExpect(handler().methodName("delete"))
                .andExpect(jsonPath("$.success",equalTo("OK")));
    }

}