package sopt.nottodo.auth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sopt.nottodo.common.domain.TimeStamped;
import sopt.nottodo.auth.dto.SignupRequest;
import sopt.nottodo.mission.domain.Mission;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User extends TimeStamped implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column(unique = true)
    private String socialId;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column
    private String name;

    @Column
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FcmToken> fcmTokens = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    public User(SignupRequest signupRequest) {
        this.email = signupRequest.getEmail();
        this.socialType = signupRequest.getSocialType();
        this.socialId = signupRequest.getSocialId();
        this.name = signupRequest.getName();
        this.image = signupRequest.getImage();
        addFcmToken(signupRequest.getFcmToken());
    }

    public void addFcmToken(FcmToken fcmToken) {
        fcmTokens.add(fcmToken);
        fcmToken.setUser(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
