package pl.pawelciesielski.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountService extends JpaRepository<Account, Long> {

}
