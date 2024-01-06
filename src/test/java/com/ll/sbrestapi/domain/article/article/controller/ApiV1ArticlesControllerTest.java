package com.ll.sbrestapi.domain.article.article.controller;

import com.ll.sbrestapi.domain.member.article.article.controller.ApiV1ArticlesController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiV1ArticlesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/articles")
    void t1() throws Exception{
        //when
        ResultActions resultActions = mockMvc
                .perform(get("/api/v1/articles"))
                .andDo(print());
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ApiV1ArticlesController.class))
                .andExpect(handler().methodName("getArticles"))
                .andExpect(jsonPath("$.data.items[0].id", is(10)))
                .andExpect(jsonPath("$.data.items[0].title", is("제목10")));
    }
}
