package sopt.nottodo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class RecommendCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String image;

    @OneToMany(mappedBy = "recommendCategory", cascade = CascadeType.ALL)
    private List<RecommendMission> recommendMissions = new ArrayList<>();
}
