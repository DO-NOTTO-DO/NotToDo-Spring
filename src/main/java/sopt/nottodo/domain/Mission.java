package sopt.nottodo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id")
    private Situation situation;

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actionDate;

    @Column
    private String goal;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Action> actions = new ArrayList<>();
}
