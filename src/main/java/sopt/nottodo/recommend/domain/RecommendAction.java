package sopt.nottodo.recommend.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class RecommendAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recommend_mission_id")
    private RecommendMission recommendMission;

    @Column(unique = true, nullable = false)
    private String name;
}
