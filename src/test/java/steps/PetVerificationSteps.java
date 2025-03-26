package steps;

import model.ErrorResponsePetModel;
import model.PetModel;
import org.assertj.core.api.SoftAssertions;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class PetVerificationSteps {
    public void verifyCreatedPet(PetModel createdPet, long expectedId, String expectedName,
                                 String expectedStatus, String expectedCategoryName,
                                 String expectedTagName, String expectedPhotoUrl) {
        step("Проверяем свойства созданного питомца", () -> {
            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(createdPet.getId()).isEqualTo(expectedId);
                softly.assertThat(createdPet.getName()).isEqualTo(expectedName);
                softly.assertThat(createdPet.getStatus()).isEqualTo(expectedStatus);
                softly.assertThat(createdPet.getCategory().getName()).isEqualTo(expectedCategoryName);
                softly.assertThat(createdPet.getTags().get(0).getName()).isEqualTo(expectedTagName);
                softly.assertThat(createdPet.getPhotoUrls()).containsExactly(expectedPhotoUrl);
            });
        });
    }

    public void verifyPetsListIsNotEmpty(List<PetModel> pets) {
        step("Проверяем, что список питомцев не пуст", () ->
                assertThat(pets).isNotEmpty()
        );
    }

    public void verifyPetNameAndStatus(PetModel pet, String expectedName, String expectedStatus) {
        step("Проверяем имя и статус питомца", () -> {
            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(pet.getName()).isEqualTo(expectedName);
                softly.assertThat(pet.getStatus()).isEqualTo(expectedStatus);
            });
        });
    }

    public void verifyErrorMessage(ErrorResponsePetModel errorResponse, String expectedMessage) {
        step("Проверяем сообщение об ошибке", () ->
                assertThat(errorResponse.getMessage()).isEqualTo(expectedMessage)
        );
    }

    public void verifyUpdatedPet(PetModel updatedPet, long expectedId, String expectedName, String expectedStatus) {
        step("Проверяем обновленные данные питомца", () -> {
            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(updatedPet.getId()).isEqualTo(expectedId);
                softly.assertThat(updatedPet.getName()).isEqualTo(expectedName);
                softly.assertThat(updatedPet.getStatus()).isEqualTo(expectedStatus);
            });
        });
    }
}