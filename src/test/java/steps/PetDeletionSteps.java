package steps;

import specs.TestSpec;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class PetDeletionSteps {
    public void deletePetById(long petId) {
        step("Удаляем питомца по ID " + petId, () ->
                given(TestSpec.requestSpec)
                        .when()
                        .delete("/{petId}", petId)
                        .then()
                        .spec(TestSpec.getResponseSpec(200))
        );
    }
}
