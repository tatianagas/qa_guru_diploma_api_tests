package steps;

import io.qameta.allure.Step;
import model.PetModel;
import specs.TestSpec;

import static io.restassured.RestAssured.given;

public class PetCreationSteps {

    @Step("Создаем нового питомца")
    public PetModel createPet(PetModel pet) {
        return given(TestSpec.requestSpec)
                .body(pet)
                .when()
                .post("")
                .then()
                .spec(TestSpec.getResponseSpec(200))
                .extract().as(PetModel.class);
    }
}
