package sopt.nottodo.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sopt.nottodo.common.domain.TimeStamped;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Action extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mission_goal_id")
    private MissionGoal missionGoal;
}
