package springboot.app.autoservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.autoservice.model.Master;

@Repository
public interface MasterDao extends JpaRepository<Master, Long> {
}
