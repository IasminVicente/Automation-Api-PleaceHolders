package steps;

import api.ApiHeaders;
import api.ApiRequest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.json.JSONObject;
import org.junit.Assert;
import user.UsersLombok;
import utils.PropertiesUltils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PleaceHolderSteps extends ApiRequest {
    PropertiesUltils prop = new PropertiesUltils();
    ApiHeaders apiHeaders = new ApiHeaders();
    Faker faker = new Faker();
    UsersLombok user;

//--------------------------------- POST ---------------------------------
    @Dado("que a Api PlaceHolder não requer token")
    public void que_a_api_place_holder_não_requer_token() {
        System.out.println("API não necessita de um token");

    }

    @Quando("envio um request de cadastro de usuário com dados validos")
    public void envio_um_request_de_cadastro_de_usuário_com_dados_validos() throws IOException {
        super.uri = prop.getProp("uri_pleaceHolder");
        super.headers = apiHeaders.placeHolderHeaders();

        user = UsersLombok.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .body("Fazendo o primeiro commit")
                .build();

        super.body = new JSONObject(new Gson().toJson(user));
        super.POST();
    }

    @Entao("o usuário deve ser criado corretamente")
    public void o_usuário_deve_ser_criado_corretamente() {
        assertEquals(user, response.jsonPath().getObject("", UsersLombok.class),
                "Erro na comparação do objeto");
    }

    @Entao("o status code deve ser {int}")
    public void o_status_code_deve_ser(Integer statusEsperado) {
        assertEquals(statusEsperado, response.statusCode(),
                "Status code diferente do esperado!");
    }
//--------------------------------- GET ----------------------------------
    @Dado("existe um usuário cadastrado na api")
    public void existe_um_usuário_cadastrado_na_api() throws IOException {
        envio_um_request_de_cadastro_de_usuário_com_dados_validos();
    }

    @Quando("buscar esse usuário")
    public void buscar_esse_usuário() throws IOException {
        super.uri = prop.getProp("uri_pleaceHolder") + "/" + response.jsonPath().getJsonObject("id=2");
        super.headers = apiHeaders.placeHolderHeaders();
        super.body = new JSONObject();
        super.GET();
    }

    @Entao("os dados do usuário devem ser retornados")
    public void os_dados_do_usuário_devem_ser_retornados() {
        assertEquals("quo vero reiciendis velit similique earum",
                response.jsonPath().getString("name"));
    }
//--------------------------------- PUT ----------------------------------
    @Quando("altero os dados do usuário")
    public void altero_os_dados_do_usuário() throws IOException {
        super.uri = prop.getProp("uri_pleaceHolder") + "/" + response.jsonPath().getJsonObject("id=2");
        super.headers = apiHeaders.placeHolderHeaders();
        user.setName("Isso é um teste");
        super.body = new JSONObject(new Gson().toJson(user));
        super.PUT();
}

    @Entao("o usuário deve ser alterado com sucesso")
    public void o_usuário_deve_ser_alterado_com_sucesso() {
        assertEquals(user, response.jsonPath().getObject("", UsersLombok.class),
                "Erro na comparação do objeto");
    }
//--------------------------------- PATCH ----------------------------------
    @Quando("altero um ou mais dados do usuário")
    public void altero_um_ou_mais_dados_do_usuário() throws IOException {
            super.uri = prop.getProp("uri_pleaceHolder") + "/" + response.jsonPath().getJsonObject("id=2");
            super.headers = apiHeaders.placeHolderHeaders();
            user.setEmail("teste@teste.com");
            user.setName("Teste");
        super.body = new JSONObject("{\"email\":\"teste@teste.com\",\"name\":\"Teste\"}");
            super.PATCH();
    }

    @Entao("os dados deve ser alterado com sucesso")
    public void os_dados_deve_ser_alterado_com_sucesso() {
        assertEquals(body.getString("email"), response.jsonPath().getString("email"));
        assertEquals(body.getString("name"), response.jsonPath().getString("name"));

    }
//--------------------------------- DELETE ----------------------------------
    @Quando("deleto esse usuário")
    public void deleto_esse_usuário() throws IOException {
        super.uri = prop.getProp("uri_pleaceHolder") + "/" + response.jsonPath().getJsonObject("id=2");
        super.body = new JSONObject(new Gson().toJson(user));
        super.DELETE();

    }
    @Entao("o usuário é deletado corretamente")
    public void o_usuário_é_deletado_corretamente() {
        Assert.assertEquals(200, response.statusCode());

    }
}
