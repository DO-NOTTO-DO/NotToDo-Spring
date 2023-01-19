package sopt.nottodo.dto.environment;

import lombok.Getter;

@Getter
public class CategoryDto {
    private final Long id;
    private final String name;
    private final String image;
    private final String activeImage;


    public CategoryDto(Long id, String name, String image, String activeImage) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.activeImage = activeImage;
    }
}
