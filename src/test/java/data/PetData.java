package data;

import model.PetModel;

import java.util.List;

public class PetData {
    public static final int PET_ID = 12345;
    public static final int CATEGORY_ID = 1;
    public static final String CATEGORY_NAME = "Cat";
    public static final String PET_NAME = "Murzik";
    public static final String PHOTO_URL = "https://animals.com/cat1.jpg";
    public static final int TAG_ID = 1;
    public static final String TAG_NAME = "cute";
    public static final String PET_STATUS = "available";

    public static List<String> getPhotoUrls() {
        return List.of(PHOTO_URL);
    }

    public static List<PetModel.Tag> getTags() {
        PetModel.Tag tag = new PetModel.Tag(TAG_ID, TAG_NAME);
        return List.of(tag);
    }

    public static PetModel.Category getCategory() {
        return new PetModel.Category(CATEGORY_ID, CATEGORY_NAME);
    }

    public static PetModel getDefaultPet() {
        return new PetModel(
                PET_ID,
                getCategory(),
                PET_NAME,
                getPhotoUrls(),
                getTags(),
                PET_STATUS
        );
    }
}