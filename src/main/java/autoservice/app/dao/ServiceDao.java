package autoservice.app.dao;

import autoservice.app.model.Services;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Services, Long> {
    List<Services> getAllByStatusAndMasterId(String status, Long id);
}
