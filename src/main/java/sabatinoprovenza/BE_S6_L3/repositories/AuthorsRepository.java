package sabatinoprovenza.BE_S6_L3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sabatinoprovenza.BE_S6_L3.entities.Author;

import java.util.Optional;
import java.util.UUID;

public interface AuthorsRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByEmail(String email);

}
