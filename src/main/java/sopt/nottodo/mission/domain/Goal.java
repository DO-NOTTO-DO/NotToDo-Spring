package sopt.nottodo.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sopt.nottodo.common.domain.TimeStamped;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Goal extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();
}
