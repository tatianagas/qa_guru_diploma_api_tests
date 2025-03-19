package steps;

import io.qameta.allure.Step;
import model.PetModel;
import specs.TestSpec;

import static io.restassured.RestAssured.given;

public class PetUpdateSteps {

    @Step("Обновляем данные питомца")
    public PetModel updatePet(PetModel pet) {
        return given(TestSpec.requestSpec)
                .body(pet)
                .when()
                .put("")
                .then()
                .spec(TestSpec.getResponseSpec(200))
                .extract().as(PetModel.class);
    }
}
