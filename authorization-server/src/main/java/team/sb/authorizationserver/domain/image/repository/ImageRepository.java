package team.sb.authorizationserver.domain.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sb.authorizationserver.domain.image.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
