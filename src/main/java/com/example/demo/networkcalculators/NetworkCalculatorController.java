package com.example.demo.networkcalculators;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NetworkCalculatorController {

	@GetMapping("/simulate-tcp-slowstart")
	public TcpSlowstartSimulation simulateTcpSlowstart(@RequestParam(value = "name", defaultValue = "World") String name) {
		return NetworkCalculator.simulateTcpSlowstart(1460, 11680, 15, 0);
	}
}
