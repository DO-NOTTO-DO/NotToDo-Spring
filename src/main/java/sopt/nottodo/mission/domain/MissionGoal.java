package sopt.nottodo.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sopt.nottodo.common.domain.TimeStamped;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MissionGoal extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String goal;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @OneToMany(mappedBy = "missionGoal", cascade = CascadeType.ALL)
    private List<DailyMission> dailyMissions = new ArrayList<>();

    @OneToMany(mappedBy = "missionGoal", cascade = CascadeType.ALL)
    private List<Action> actions = new ArrayList<>();
}
