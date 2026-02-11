package sabatinoprovenza.BE_S6_L3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sabatinoprovenza.BE_S6_L3.entities.Blog;

import java.util.UUID;

@Repository
public interface BlogsRepository extends JpaRepository<Blog, UUID> {

}
