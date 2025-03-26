package steps;

import model.PetModel;
import specs.TestSpec;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class PetUpdateSteps {
    public PetModel updatePet(PetModel pet) {
        return step("Обновляем данные питомца", () ->
                given(TestSpec.requestSpec)
                        .body(pet)
                        .when()
                        .put()
                        .then()
                        .spec(TestSpec.getResponseSpec(200))
                        .extract().as(PetModel.class)
        );
    }
}
