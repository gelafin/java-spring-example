package com.example.demo.networkcalculators;

import java.util.ArrayList;

/**
 * Provides fields for simulating a packet group in the Tcp slowstart phase.
 * Intended to occur within arrays.
 */
record TcpSlowstartSimulationGroup(
    Integer groupNumber, Integer congestionWindowBytes, Integer congestionWindowMss, ArrayList<Integer> packetsSentThisGroup
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
     * @param slowstartCongestionWindowLimitBytes congestion window size limit for the slow-start phase, in bytes
     * @param packetCount how many packets are waiting to be sent
     * @param packetSizeBytes Size of each packet. If 0, packet_size will be set to mss_bytes
     * @return simulation result record showing data for each group
     */
    public static ArrayList<TcpSlowstartSimulationGroup> simulateTcpSlowstart(
        Integer mssBytes, Integer slowstartCongestionWindowLimitBytes, Integer packetCount, Integer packetSizeBytes
    ) {
        ArrayList<TcpSlowstartSimulationGroup> resultOut = new ArrayList<TcpSlowstartSimulationGroup>();
        packetSizeBytes = packetSizeBytes == 0 ? mssBytes : packetSizeBytes;
        int currentCongestionWindowMss = 1;
        int currentCongestionWindowBytes = mssBytes * currentCongestionWindowMss;
        int packetIndex = 0;
        int groupNumber = 1;

        while (packetIndex < packetCount) {
            // form a packet group according to congestion window size
            int packetGroupBytes = 0;
            ArrayList<Integer> packetGroupNumbers = new ArrayList<Integer>();
            int packetNumberOut = packetIndex + 1; // start numbering from 1

            // while there's still room (and waiting packets)
            while (
                packetGroupBytes <= currentCongestionWindowBytes - packetSizeBytes &&
                packetNumberOut - 1 < packetCount
            ) {
                // add a packet
                packetGroupNumbers.add(packetNumberOut);

                // maintain loop
                packetGroupBytes += packetSizeBytes;
                ++packetNumberOut;
            }

            // save this packet group's details for return
            TcpSlowstartSimulationGroup simulationGroupOut = new TcpSlowstartSimulationGroup(
                groupNumber, currentCongestionWindowBytes, currentCongestionWindowMss, packetGroupNumbers
            );
            resultOut.add(simulationGroupOut);

            // increase congestion window size so the next packet group can be bigger
            if (currentCongestionWindowBytes >= slowstartCongestionWindowLimitBytes) {
                // at the limit; increase linearly
                ++currentCongestionWindowMss;
            } else {
                // not at the limit; increase exponentially
                currentCongestionWindowMss *= 2;
            }

            // update current_congestion_window_mss's dependent variable
            currentCongestionWindowBytes = mssBytes * currentCongestionWindowMss;

            // maintain loop
            ++groupNumber;
            packetIndex += packetGroupNumbers.size();
        }

        return resultOut;
    }
}
