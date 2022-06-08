package com.example.demo.networkcalculators;

/**
 * Provides fields for simulating the Tcp slowstart phase
 */
record TcpSlowstartSimulation(
    Integer groupNumber, Integer congestionWindowBytes, Integer congestionWindowMss, Integer[] packetsSentThisGroup
) {}

/**
 * Provides calculations for computer network problems
 * Translated from my repo https://github.com/gelafin/network-calculators
 */
public class NetworkCalculator {

    public NetworkCalculator() {}

    /**
     * Simulates the TCP slow-start phase by calculating changes in congestion window size
     * @param mssBytes Maximum Segment Size, in bytes
     * @return simulation result record
     */
    public static TcpSlowstartSimulation simulateTcpSlowstart(
        Integer mssBytes, Integer slowstartCongestionWindowLimitBytes, Integer packetCount, Integer packetSizeBytes
    ) {
        TcpSlowstartSimulation resultOut = new TcpSlowstartSimulation(1, 1, 1, new Integer[]{1});
        return resultOut;
    }
}
