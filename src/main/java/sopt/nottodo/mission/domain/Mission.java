package sopt.nottodo.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sopt.nottodo.auth.domain.User;
import sopt.nottodo.common.domain.TimeStamped;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mission extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String situation;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MissionGoal> missionGoals = new ArrayList<>();
}
