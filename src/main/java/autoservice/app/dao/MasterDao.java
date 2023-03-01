package autoservice.app.dao;

import autoservice.app.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDao extends JpaRepository<Master, Long> {
}
