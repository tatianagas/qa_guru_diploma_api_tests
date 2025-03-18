package steps;

import io.qameta.allure.Step;
import model.ErrorResponsePetModel;
import model.PetModel;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PetVerificationSteps {

    @Step("Проверяем свойства созданного питомца")
    public void verifyCreatedPet(PetModel createdPet, long expectedId, String expectedName, String expectedStatus, String expectedCategoryName, String expectedTagName, String expectedPhotoUrl) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(createdPet.getId()).isEqualTo(expectedId);
            softly.assertThat(createdPet.getName()).isEqualTo(expectedName);
            softly.assertThat(createdPet.getStatus()).isEqualTo(expectedStatus);
            softly.assertThat(createdPet.getCategory().getName()).isEqualTo(expectedCategoryName);
            softly.assertThat(createdPet.getTags().get(0).getName()).isEqualTo(expectedTagName);
            softly.assertThat(createdPet.getPhotoUrls()).containsExactly(expectedPhotoUrl);
        });
    }

    @Step("Проверяем, что список питомцев не пуст")
    public void verifyPetsListIsNotEmpty(List<PetModel> pets) {
        assertThat(pets).isNotEmpty();
    }

    @Step("Проверяем имя и статус питомца")
    public void verifyPetNameAndStatus(PetModel pet, String expectedName, String expectedStatus) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(pet.getName()).isEqualTo(expectedName);
            softly.assertThat(pet.getStatus()).isEqualTo(expectedStatus);
        });
    }

    @Step("Проверяем сообщение об ошибке")
    public void verifyErrorMessage(ErrorResponsePetModel errorResponse, String expectedMessage) {
        assertThat(errorResponse.getMessage()).isEqualTo(expectedMessage);
    }

    @Step("Проверяем обновленные данные питомца")
    public void verifyUpdatedPet(PetModel updatedPet, long expectedId, String expectedName, String expectedStatus) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedPet.getId()).isEqualTo(expectedId);
            softly.assertThat(updatedPet.getName()).isEqualTo(expectedName);
            softly.assertThat(updatedPet.getStatus()).isEqualTo(expectedStatus);
        });
    }
}