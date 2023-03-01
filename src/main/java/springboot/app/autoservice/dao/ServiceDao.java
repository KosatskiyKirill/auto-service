package springboot.app.autoservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.autoservice.model.Services;
import java.util.List;

@Repository
public interface ServiceDao extends JpaRepository<Services, Long> {
    List<Services> getAllByStatusAndMaster_Id(String status, Long id);
}
