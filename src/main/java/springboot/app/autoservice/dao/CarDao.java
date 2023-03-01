package springboot.app.autoservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.autoservice.model.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Long> {
}
