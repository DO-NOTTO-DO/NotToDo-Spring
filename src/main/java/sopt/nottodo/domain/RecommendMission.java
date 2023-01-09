package sopt.nottodo.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class RecommendMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recommend_category_id")
    private RecommendCategory recommendCategory;

    @Column(unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "recommendMission", cascade = CascadeType.ALL)
    private List<RecommendAction> recommendActions = new ArrayList<>();
}
