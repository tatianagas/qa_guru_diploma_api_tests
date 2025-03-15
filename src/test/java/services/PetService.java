package services;

import io.qameta.allure.Step;
import model.ErrorResponsePetModel;
import model.PetModel;
import specs.TestSpec;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetService {

    @Step("Получаем список всех питомцев")
    public List<PetModel> getAllPets() {
        return given(TestSpec.requestSpec)
                .queryParam("status", "available,pending,sold")
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(TestSpec.responseCod200Spec)
                .extract().jsonPath().getList("", PetModel.class);
    }

    @Step("Получаем питомца по ID {petId}")
    public PetModel getPetById(long petId) {
        return given(TestSpec.requestSpec)
                .when()
                .get("/pet/{petId}", petId)
                .then()
                .spec(TestSpec.responseCod200Spec)
                .extract().as(PetModel.class);
    }

    @Step("Получаем ошибку для несуществующего питомца по ID {petId}")
    public ErrorResponsePetModel getPetByIdNotFound(long petId) {
        return given(TestSpec.requestSpec)
                .when()
                .get("/pet/{petId}", petId)
                .then()
                .spec(TestSpec.responseCod404Spec)
                .extract().as(ErrorResponsePetModel.class);
    }
}
