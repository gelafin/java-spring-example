package com.example.demo.networkcalculators;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NetworkCalculatorController {

	@GetMapping("/simulate-tcp-slowstart")
	public ArrayList<TcpSlowstartSimulationGroup> simulateTcpSlowstart(
		@RequestParam(defaultValue = "1500") Integer mssBytes,
		@RequestParam(defaultValue = "10500") Integer slowstartCongestionWindowLimitBytes,
		@RequestParam(defaultValue = "15") Integer packetCount,
		@RequestParam(defaultValue = "0") Integer packetSizeBytes
	) {
		return NetworkCalculator.simulateTcpSlowstart(
			mssBytes, slowstartCongestionWindowLimitBytes, packetCount, packetSizeBytes
		);
	}
}
