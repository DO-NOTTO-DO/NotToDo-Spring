package sopt.nottodo.recommend.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sopt.nottodo.domain.RecommendCategory;

@Getter
@EqualsAndHashCode
public class CategoryDto {
    private final Long id;
    private final String name;
    private final String image;
    private final String activeImage;

    public CategoryDto(RecommendCategory category) {
        this.id = category.getId();
        this.name = category.getName();
        this.image = category.getImage();
        this.activeImage = category.getActiveImage();
    }
}
