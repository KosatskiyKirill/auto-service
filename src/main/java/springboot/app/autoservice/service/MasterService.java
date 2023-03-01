package springboot.app.autoservice.service;

import springboot.app.autoservice.model.Master;

import java.util.Optional;

public interface MasterService {
    Master create(Master master);

    Master update(Master master);

    Optional<Master> findById(Long id);
}
