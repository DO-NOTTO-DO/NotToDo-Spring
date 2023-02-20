package sopt.nottodo.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.auth.domain.FcmToken;

@Repository
public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {

}
