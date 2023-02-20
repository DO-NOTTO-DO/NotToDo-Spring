package sopt.nottodo.recommend.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class RecommendMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String situation;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "recommendMission", cascade = CascadeType.ALL)
    private List<RecommendAction> recommendActions = new ArrayList<>();
}
