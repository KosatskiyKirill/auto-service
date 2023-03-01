package autoservice.app.dao;

import autoservice.app.model.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerDao extends JpaRepository<CarOwner, Long> {
}
