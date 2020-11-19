package by.ovsyanka.teamwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevGroupRepository  extends JpaRepository<DevGroup, Long> {
    Optional<DevGroup> findDevGroupByName(String name);
}
