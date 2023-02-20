package sopt.nottodo.mission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sopt.nottodo.common.domain.TimeStamped;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DailyMission extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;
}
