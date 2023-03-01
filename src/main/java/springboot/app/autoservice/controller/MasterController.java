package springboot.app.autoservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.app.autoservice.dto.MasterDto;
import springboot.app.autoservice.dto.OrderDto;
import springboot.app.autoservice.dto.mapper.impl.MasterMapper;
import springboot.app.autoservice.dto.mapper.impl.OrderMapper;
import springboot.app.autoservice.model.Master;
import springboot.app.autoservice.service.MasterService;
import springboot.app.autoservice.service.OrderService;
import springboot.app.autoservice.service.ServiceService;

import java.util.List;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterMapper masterMapper;
    private final MasterService masterService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ServiceService serviceService;

    public MasterController(MasterMapper masterMapper, MasterService masterService,
                            OrderService orderService, OrderMapper orderMapper, ServiceService serviceService) {
        this.masterMapper = masterMapper;
        this.masterService = masterService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<MasterDto> createMaster(@RequestBody MasterDto masterDto) {
        Master newMaster = masterService.create(masterMapper.toModel(masterDto));
        return ResponseEntity.ok(masterMapper.toDto(newMaster));
    }

    @PutMapping("/{masterId}")
    public ResponseEntity<MasterDto> updateMaster(@PathVariable Long masterId,
                                                  @RequestBody MasterDto masterDto) {
        return masterService.findById(masterId)
                .map(m -> {
                    m.setFullName(masterDto.getFullName());
                    return masterService.update(m);
                })
                .map(masterMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{masterId}/orders")
    public ResponseEntity<List<OrderDto>> getMastersOrders(@PathVariable Long masterId) {
        return masterService.findById(masterId)
                .map(m -> orderService.getAllByMasterId(masterId).stream()
                       .map(orderMapper::toDto)
                       .collect(toList()))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{masterId}/salary")
    public ResponseEntity<Double> getMasterSalary(@PathVariable Long masterId) {
        return ResponseEntity.ok(serviceService.getSalaryForMaster(masterId));
    }
}
