package steps;

import io.qameta.allure.Step;
import specs.TestSpec;

import static io.restassured.RestAssured.given;

public class PetDeletionSteps {

    @Step("Удаляем питомца по ID {petId}")
    public void deletePetById(long petId) {
        given(TestSpec.requestSpec)
                .when()
                .delete("/{petId}", petId)
                .then()
                .spec(TestSpec.getResponseSpec(200)); // Ожидаем код ответа 200
    }
}
