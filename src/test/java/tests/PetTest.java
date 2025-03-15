package tests;

import data.PetData;
import model.ErrorResponsePetModel;
import model.PetModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PetService;
import services.PetUpdateService;
import specs.TestSpec;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTest extends TestBase {

    private final PetService petService = new PetService();
    private final PetUpdateService petUpdateService = new PetUpdateService();

    @Test
    @DisplayName("Создание нового питомца")
    void createPetTest() {

        PetModel.Category category = PetData.getCategory();
        List<String> photoUrls = PetData.getPhotoUrls();
        List<PetModel.Tag> tags = PetData.getTags();

        PetModel pet = new PetModel(
                PetData.PET_ID,
                category,
                PetData.PET_NAME,
                photoUrls,
                tags,
                PetData.PET_STATUS
        );

        PetModel createdPet = step("Создаем нового питомца", () ->
                given(TestSpec.requestSpec)
                        .body(pet)
                        .when()
                        .post("/pet")
                        .then()
                        .spec(TestSpec.responseCod200Spec)
                        .extract().as(PetModel.class));

        step("Проверяем свойства созданного питомца", () -> {
            assertThat(createdPet.getId()).isEqualTo(PetData.PET_ID);
            assertThat(createdPet.getName()).isEqualTo(PetData.PET_NAME);
            assertThat(createdPet.getStatus()).isEqualTo(PetData.PET_STATUS);
            assertThat(createdPet.getCategory().getName()).isEqualTo(PetData.CATEGORY_NAME);
            assertThat(createdPet.getTags().get(0).getName()).isEqualTo(PetData.TAG_NAME);
            assertThat(createdPet.getPhotoUrls()).containsExactly(PetData.PHOTO_URL);
        });
    }

    @Test
    @DisplayName("Проверка наличия питомца с именем 'King Kong' среди проданных")
    void findSoldPetsTest() {

        String expectedName = "King Kong";


        List<PetModel> pets = step("Ищем питомцев со статусом 'sold'", () ->
                given(TestSpec.requestSpec)
                        .queryParam("status", "sold")
                        .when()
                        .get("/pet/findByStatus")
                        .then()
                        .spec(TestSpec.responseCod200Spec)
                        .extract().jsonPath().getList("", PetModel.class));

        step("Проверяем количество питомцев", () -> {
            assertThat(pets).hasSize(7);
        });


        step("Проверяем наличие питомца с именем 'King Kong'", () -> {
            boolean isKingKongFound = pets.stream()
                    .anyMatch(pet -> pet.getName().equals(expectedName));
            assertThat(isKingKongFound).isTrue();
        });
    }

    @Test
    @DisplayName("Успешный поиск питомца по ID")
    void findPetByIdSuccessTest() {

        List<PetModel> pets = petService.getAllPets();

        step("Проверяем, что список питомцев не пустой", () -> {
            assertThat(pets).isNotEmpty();
        });

        PetModel expectedPet = pets.get(0);
        long petId = expectedPet.getId();

        PetModel pet = step("Ищем питомца по ID " + petId, () ->
                petService.getPetById(petId));

        step("Проверяем имя и статус питомца", () -> {
            assertThat(pet.getName()).isEqualTo(expectedPet.getName());
            assertThat(pet.getStatus()).isEqualTo(expectedPet.getStatus());
        });
    }

    @Test
    @DisplayName("Поиск несуществующего питомца по ID")
    void findPetByIdNotFoundTest() {

        long petId = 80;
        String expectedErrorMessage = "Pet not found";

        ErrorResponsePetModel errorResponse = step("Ищем питомца по несуществующему ID " + petId, () ->
                given(TestSpec.requestSpec)
                        .when()
                        .get("/pet/{petId}", petId)
                        .then()
                        .spec(TestSpec.responseCod404Spec)
                        .extract().as(ErrorResponsePetModel.class));

        step("Проверяем сообщение об ошибке", () -> {
            assertThat(errorResponse.getMessage()).isEqualTo(expectedErrorMessage);
        });
    }

    @Test
    @DisplayName("Обновление данных существующего питомца")
    void updatePetTest() {

        List<PetModel> pets = petService.getAllPets();


        step("Проверяем, что список питомцев не пустой", () -> {
            assertThat(pets).isNotEmpty();
        });


        PetModel petToUpdate = pets.get(0);
        long petId = petToUpdate.getId();


        PetModel updatedPet = petUpdateService.createUpdatedPet(petToUpdate);


        PetModel responsePet = step("Обновляем данные питомца", () ->
                given(TestSpec.requestSpec)
                        .body(updatedPet)
                        .when()
                        .put("/pet")
                        .then()
                        .spec(TestSpec.responseCod200Spec)
                        .extract().as(PetModel.class));

        step("Проверяем обновленные данные питомца", () -> {
            assertThat(responsePet.getId()).isEqualTo(petId);
            assertThat(responsePet.getName()).isEqualTo(updatedPet.getName());
            assertThat(responsePet.getStatus()).isEqualTo(updatedPet.getStatus());
        });

        PetModel fetchedPet = step("Проверяем изменения через GET-запрос", () ->
                given(TestSpec.requestSpec)
                        .when()
                        .get("/pet/{petId}", petId)
                        .then()
                        .spec(TestSpec.responseCod200Spec)
                        .extract().as(PetModel.class));

        step("Проверяем, что изменения сохранились", () -> {
            assertThat(fetchedPet.getName()).isEqualTo(updatedPet.getName());
            assertThat(fetchedPet.getStatus()).isEqualTo(updatedPet.getStatus());
        });
    }
}


