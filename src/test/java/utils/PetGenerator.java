package utils;

import model.PetModel;

public class PetGenerator {

    public static PetModel createUpdatedPet(PetModel petToUpdate) {
        return new PetModel(
                petToUpdate.getId(),
                petToUpdate.getCategory(),
                "Updated" + petToUpdate.getName(),
                petToUpdate.getPhotoUrls(),
                petToUpdate.getTags(),
                "sold"
        );
    }
}
