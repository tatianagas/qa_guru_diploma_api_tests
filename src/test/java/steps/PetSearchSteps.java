package steps;

import model.ErrorResponsePetModel;
import model.PetModel;
import model.PetStatus;
import specs.TestSpec;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import java.util.List;

public class PetSearchSteps {
    public List<PetModel> getAllPets() {
        return step("Получаем список всех питомцев", () ->
                given(TestSpec.requestSpec)
                        .queryParam("status", "available,pending,sold")
                        .when()
                        .get("/findByStatus")
                        .then()
                        .spec(TestSpec.getResponseSpec(200))
                        .extract().jsonPath().getList("", PetModel.class)
        );
    }

    public PetModel getPetById(long petId) {
        return step("Получаем питомца по ID " + petId, () ->
                given(TestSpec.requestSpec)
                        .when()
                        .get("/{petId}", petId)
                        .then()
                        .spec(TestSpec.getResponseSpec(200))
                        .extract().as(PetModel.class)
        );
    }

    public List<PetModel> findPetsByStatus(PetStatus status) {
        return step("Ищем питомцев по статусу " + status.name().toLowerCase(), () ->
                given(TestSpec.requestSpec)
                        .queryParam("status", status.name().toLowerCase())
                        .when()
                        .get("/findByStatus")
                        .then()
                        .spec(TestSpec.getResponseSpec(200))
                        .extract().jsonPath().getList("", PetModel.class)
        );
    }

    public ErrorResponsePetModel getPetByIdNotFound(long petId) {
        return step("Получаем ошибку для несуществующего питомца по ID " + petId, () ->
                given(TestSpec.requestSpec)
                        .when()
                        .get("/{petId}", petId)
                        .then()
                        .spec(TestSpec.getResponseSpec(404))
                        .extract().as(ErrorResponsePetModel.class)
        );
    }
}

