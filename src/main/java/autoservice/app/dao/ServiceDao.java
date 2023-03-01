package autoservice.app.dao;


import autoservice.app.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDao extends JpaRepository<Services, Long> {
    List<Services> getAllByStatusAndMaster_Id(String status, Long id);
}
