package tests;

import data.PetData;
import io.qameta.allure.*;
import model.ErrorResponsePetModel;
import model.PetModel;
import model.PetStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import steps.*;
import utils.PetGenerator;

import java.util.List;

@Epic("Pet Store API")
@Feature("Управление питомцами")
@Story("Базовые операции над питомцами")
public class PetTest extends TestBase {

    private final PetCreationSteps petCreationSteps = new PetCreationSteps();
    private final PetSearchSteps petSearchSteps = new PetSearchSteps();
    private final PetUpdateSteps petUpdateSteps = new PetUpdateSteps();
    private final PetVerificationSteps petVerificationSteps = new PetVerificationSteps();
    private final PetDeletionSteps petDeletionSteps = new PetDeletionSteps();


    @Test
    @DisplayName("Создание нового питомца")
    @Severity(SeverityLevel.BLOCKER)

    void createPetTest() {
        PetModel pet = PetData.getDefaultPet();

        PetModel createdPet = petCreationSteps.createPet(pet);
        petVerificationSteps.verifyCreatedPet(
                createdPet,
                PetData.PET_ID,
                PetData.PET_NAME,
                PetData.PET_STATUS,
                PetData.CATEGORY_NAME,
                PetData.TAG_NAME,
                PetData.PHOTO_URL
        );
    }

    @ParameterizedTest
    @EnumSource(PetStatus.class)
    @DisplayName("Поиск питомцев по статусу {status}")
    @Severity(SeverityLevel.CRITICAL)

    void findPetsByStatusTest(PetStatus status) {
        List<PetModel> pets = petSearchSteps.findPetsByStatus(status);
        petVerificationSteps.verifyPetsListIsNotEmpty(pets);
    }

    @Test
    @DisplayName("Успешный поиск питомца по ID")
    @Severity(SeverityLevel.NORMAL)

    void findPetByIdSuccessTest() {
        List<PetModel> pets = petSearchSteps.getAllPets();
        petVerificationSteps.verifyPetsListIsNotEmpty(pets);

        PetModel expectedPet = pets.get(0);
        long petId = expectedPet.getId();

        PetModel pet = petSearchSteps.getPetById(petId);
        petVerificationSteps.verifyPetNameAndStatus(pet, expectedPet.getName(), expectedPet.getStatus());
    }

    @Test
    @DisplayName("Поиск несуществующего питомца по ID")
    @Severity(SeverityLevel.NORMAL)

    void findPetByIdNotFoundTest() {
        long nonExistentPetId = Long.MAX_VALUE;
        String expectedErrorMessage = "Pet not found";

        ErrorResponsePetModel errorResponse = petSearchSteps.getPetByIdNotFound(nonExistentPetId);
        petVerificationSteps.verifyErrorMessage(errorResponse, expectedErrorMessage);
    }

    @Test
    @DisplayName("Обновление данных существующего питомца")
    @Severity(SeverityLevel.CRITICAL)

    void updatePetTest() {
        List<PetModel> pets = petSearchSteps.getAllPets();
        petVerificationSteps.verifyPetsListIsNotEmpty(pets);

        PetModel petToUpdate = pets.get(0);
        long petId = petToUpdate.getId();

        PetModel updatedPet = PetGenerator.createUpdatedPet(petToUpdate);
        PetModel responsePet = petUpdateSteps.updatePet(updatedPet);

        petVerificationSteps.verifyUpdatedPet(responsePet, petId, updatedPet.getName(), updatedPet.getStatus());

        PetModel fetchedPet = petSearchSteps.getPetById(petId);
        petVerificationSteps.verifyPetNameAndStatus(fetchedPet, updatedPet.getName(), updatedPet.getStatus());
    }

    @Test
    @DisplayName("Удаление питомца по ID")
    @Severity(SeverityLevel.CRITICAL)

    void deletePetByIdTest() {
        PetModel pet = PetData.getDefaultPet();
        PetModel createdPet = petCreationSteps.createPet(pet);

        long petId = createdPet.getId();
        petDeletionSteps.deletePetById(petId);

        petSearchSteps.getPetByIdNotFound(petId);
    }
}