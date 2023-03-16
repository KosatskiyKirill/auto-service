package autoservice.app.service;

import autoservice.app.model.Master;
import java.util.Optional;

public interface MasterService {
    Master create(Master master);

    Master update(Master master);

    Optional<Master> findById(Long id);
}
