package co.muntashir.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GaugeController {
    private final Gauge queueSize;

    public GaugeController(CollectorRegistry collectorRegistry) {
        queueSize = Gauge.build()
                .name("queue_size")
                .labelNames("queue_name")
                .help("Size of queue.")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/push")
    public String push() {

        queueSize.labels("muntashir-example").inc();

        return "You pushed an item to the  muntashir example queue!";
    }

    @GetMapping(value = "/pop")
    public String pop() {
        queueSize.labels("muntashir-example").dec();

        return "You popped an item from the muntashir example queue!";
    }
}
