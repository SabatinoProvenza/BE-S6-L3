package sabatinoprovenza.BE_S6_L3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sabatinoprovenza.BE_S6_L3.entities.Author;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByEmail(String email);

    Optional<Author> findById(UUID id);

}
