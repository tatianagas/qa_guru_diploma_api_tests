package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class PetModel {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    @Data
    @AllArgsConstructor
    public static class Category {
        private int id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class Tag {
        private int id;
        private String name;
    }
}
