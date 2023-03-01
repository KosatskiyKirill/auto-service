package springboot.app.autoservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.autoservice.model.CarOwner;

@Repository
public interface CarOwnerDao extends JpaRepository<CarOwner, Long> {
}
