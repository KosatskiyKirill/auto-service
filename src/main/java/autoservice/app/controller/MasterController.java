package autoservice.app.controller;

import static java.util.stream.Collectors.toList;

import autoservice.app.dto.request.MasterRequestDto;
import autoservice.app.dto.response.MasterResponseDto;
import autoservice.app.dto.response.OrderResponseDto;
import autoservice.app.model.Master;
import autoservice.app.service.MasterService;
import autoservice.app.service.OrderService;
import autoservice.app.service.ServiceService;
import autoservice.app.service.mapper.MasterMapper;
import autoservice.app.service.mapper.OrderMapper;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterMapper masterMapper;
    private final MasterService masterService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ServiceService serviceService;

    public MasterController(MasterMapper masterMapper, MasterService masterService,
                            OrderService orderService, OrderMapper orderMapper,
                            ServiceService serviceService) {
        this.masterMapper = masterMapper;
        this.masterService = masterService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<MasterResponseDto> createMaster(@RequestBody MasterRequestDto masterDto) {
        Master newMaster = masterService.create(masterMapper.toModel(masterDto));
        return ResponseEntity.ok(masterMapper.toDto(newMaster));
    }

    @PutMapping("/{masterId}")
    public ResponseEntity<MasterResponseDto> updateMaster(@PathVariable Long masterId,
                                                  @RequestBody MasterRequestDto masterDto) {
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
    public ResponseEntity<List<OrderResponseDto>> getMastersOrders(@PathVariable Long masterId) {
        return masterService.findById(masterId)
                .map(m -> orderService.getAllByMasterId(masterId).stream()
                       .map(orderMapper::toDto)
                       .collect(toList()))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{masterId}/salary")
    public ResponseEntity<BigDecimal> getMasterSalary(@PathVariable Long masterId) {
        return ResponseEntity.ok(serviceService.getSalaryForMaster(masterId));
    }
}
