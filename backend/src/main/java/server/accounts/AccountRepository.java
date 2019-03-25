package server.accounts;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Account repository
 *
 * @GitHub : https://github.com/zacscoding
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String username);
}
