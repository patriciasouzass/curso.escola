package com.example.demo.infra.controllers;

import com.example.demo.service.AlunoService;
import java.io.File;
import java.io.IOException;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willDoNothing;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class AlunoControllerTest {

    public static final String CADASTRAR_ALUNO_URL = "/alunos/cadastrar";
    public static final String BUSCAR_ALUNOS_URL = "/alunos/pesquisar/todos";
    public static final String BUSCAR_ALUNOS_POR_ID_URL = "/alunos/pesquisar/19";
    public static final String BUSCAR_ALUNOS_POR_NOME_URL = "/alunos/pesquisar/Carlos";
    public static final String ATUALIZAR_ALUNO_POR_ID_URL = "/alunos/atualizar/5";
    private MockMvc mvc;

    @Mock
    private AlunoService service;
    @InjectMocks
    private AlunoController controller;


    @Test
    void whenPostIsCalledThenAlunoIsCreated() throws Exception {

        String requestBody = "{\"id\": 1, \"matricula\": 1335, "
                + " \"nome\": Carolina, \"sobrenome\": Santos,"
                + " \"dataNascimento\": \"14/02/1987\", \"email\": email@email.com,"
                + "\"cpf\": \"09412337400\", \"endereco\": Rua Nossa Senhora,"
                + (" \"turma\": {\"id\": 1," +
                "\"nome\": \"TurmaA\"," +
                "\"turno\": \"Noite\"} }")
                .trim();

        mvc.perform(post(CADASTRAR_ALUNO_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    assertNotNull(result.getResponse());
                })
                .andDo(print())
                .andReturn();
    }


    @Test
    void whenCallGetAllAlunoThenReturnAlunoData() throws Exception {
        var expected = getMockedObject(
                "json/aluno/aluno_success_response.json");

        mvc.perform(get(BUSCAR_ALUNOS_URL))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertNotNull(result.getResponse());
                    assertEquals(expected, result.getResponse().getContentAsString(),
                            JSONCompareMode.STRICT);
                })
                .andDo(print())
                .andReturn();
    }

    @Test
    void whenCallGetAlunoById() throws Exception {
        var expected = getMockedObject(
                "json/aluno/aluno_by_id_success_response.json");

        mvc.perform(get(BUSCAR_ALUNOS_POR_ID_URL))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertNotNull(result.getResponse());
                    assertEquals(expected, result.getResponse().getContentAsString(),
                            JSONCompareMode.LENIENT);
                })
                .andDo(print())
                .andReturn();
    }

    @Test
    void whenCallGetAlunoListByName() throws Exception {
        var expected = getMockedObject(
                "json/aluno/aluno_by_name_success_response.json");

        mvc.perform(get(BUSCAR_ALUNOS_POR_NOME_URL))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertNotNull(result.getResponse());
                    assertEquals(expected, result.getResponse().getContentAsString(),
                            JSONCompareMode.LENIENT);
                })
                .andDo(print())
                .andReturn();
    }

    @Test
    void whenCallGetUpdateAlunoById() throws Exception {
        var requestBody = getMockedObject("json/aluno/atualizar_aluno_by_id_success_response.json");

        mvc.perform(patch(ATUALIZAR_ALUNO_POR_ID_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void whenCallDeleteAluno() throws Exception {
        long alunoId = 1L;
        willDoNothing().given(controller).deleteAluno(alunoId);

        ResultActions response = mvc.perform(delete("/alunos/excluir/{id}", alunoId));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    public static String getMockedObject(String jsonFilePath) throws IOException {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        return FileUtils.readFileToString(
                new File(classLoader.getResource(jsonFilePath).getFile()), CharEncoding.UTF_8);
    }
}