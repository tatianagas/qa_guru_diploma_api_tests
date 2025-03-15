package services;

import model.PetModel;

public class PetUpdateService {
    public PetModel createUpdatedPet(PetModel petToUpdate) {
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
