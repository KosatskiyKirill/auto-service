package autoservice.app.dao;

import autoservice.app.model.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Service, Long> {
    List<Service> getAllByStatusAndMasterId(String status, Long id);

    Optional<Service> findByName(String name);
}
