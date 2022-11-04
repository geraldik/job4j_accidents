package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accident.Main;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentService service;

    @Test
    @WithMockUser
    public void checkAccidentsPage() throws Exception {
        this.mockMvc.perform(get("/accidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents"));
    }

    @Test
    @WithMockUser
    public void checkCreateAccidentPage() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void checkEditFormPage() throws Exception {
        given(this.service.findById(1)).willReturn(new Accident(1, "name", "text",
                "address", new AccidentType(1, "typeName")));
        this.mockMvc.perform(get("/formEditAccident/1").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAccidentPost() throws Exception {
        this.mockMvc.perform(post("/saveAccident")
                        .param("id", "1")
                        .param("name", "Столкновение двух машин")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(service).save(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("Столкновение двух машин");
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageEditAccidentPost() throws Exception {
        this.mockMvc.perform(post("/editAccident")
                        .param("name", "accident")
                        .param("description", "accident description")
                        .param("address", "address")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(service).update(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("accident");
        assertThat(argument.getValue().getDescription()).isEqualTo("accident description");
        assertThat(argument.getValue().getAddress()).isEqualTo("address");
        assertThat(argument.getValue().getType().getId()).isEqualTo(1);
        assertThat(argument.getValue().getRules().stream().findFirst().get().getId()).isEqualTo(1);

    }
}