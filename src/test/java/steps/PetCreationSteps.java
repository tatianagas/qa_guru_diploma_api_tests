package steps;

import model.PetModel;
import specs.TestSpec;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class PetCreationSteps {
    public PetModel createPet(PetModel pet) {
        return step("Создаем нового питомца", () ->
                given(TestSpec.requestSpec)
                        .body(pet)
                        .when()
                        .post()
                        .then()
                        .spec(TestSpec.getResponseSpec(200))
                        .extract().as(PetModel.class)
        );
    }
}
