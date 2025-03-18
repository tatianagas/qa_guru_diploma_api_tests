package steps;

import io.qameta.allure.Step;
import model.ErrorResponsePetModel;
import model.PetModel;
import model.PetStatus;
import specs.TestSpec;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetSearchSteps {

    @Step("Получаем список всех питомцев")
    public List<PetModel> getAllPets() {
        return given(TestSpec.requestSpec)
                .queryParam("status", "available,pending,sold")
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(TestSpec.getResponseSpec(200))
                .extract().jsonPath().getList("", PetModel.class);
    }

    @Step("Получаем питомца по ID {petId}")
    public PetModel getPetById(long petId) {
        return given(TestSpec.requestSpec)
                .when()
                .get("/pet/{petId}", petId)
                .then()
                .spec(TestSpec.getResponseSpec(200))
                .extract().as(PetModel.class);
    }

    @Step("Ищем питомцев по статусу {status}")
    public List<PetModel> findPetsByStatus(PetStatus status) {
        return given(TestSpec.requestSpec)
                .queryParam("status", status.name().toLowerCase())
                .when()
                .get("/pet/findByStatus")
                .then()
                .spec(TestSpec.getResponseSpec(200))
                .extract().jsonPath().getList("", PetModel.class);
    }

    @Step("Получаем ошибку для несуществующего питомца по ID {petId}")
    public ErrorResponsePetModel getPetByIdNotFound(long petId) {
        return given(TestSpec.requestSpec)
                .when()
                .get("/pet/{petId}", petId)
                .then()
                .spec(TestSpec.getResponseSpec(404)) // Ожидаем код ответа 404
                .extract().as(ErrorResponsePetModel.class);
    }
}

