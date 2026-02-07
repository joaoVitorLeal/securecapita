package io.github.joaovitorleal.securecapita.repository;

import io.github.joaovitorleal.securecapita.domain.AccountVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountVerificationJpaRepository extends JpaRepository<AccountVerification, Long> {

    Optional<AccountVerification> findByUrl(String url);
    void deleteByUserId(Long userId);
}
