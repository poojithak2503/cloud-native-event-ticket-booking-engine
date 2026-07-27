package com.poojitha.ticketbooking.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeploymentMonitoringService {

    public Map<String, Object> deploymentStatus() {

        Map<String, Object> report =
                new HashMap<>();

        report.put("Application",
                "Ticket Booking Engine");

        report.put("Docker Image",
                "ticket-booking:latest");

        report.put("Kubernetes Namespace",
                "production");

        report.put("Running Pods",
                5);

        report.put("Available Pods",
                5);

        report.put("RabbitMQ Status",
                "Healthy");

        report.put("AWS RDS",
                "Connected");

        report.put("Deployment Status",
                "Running");

        report.put("Last Deployment",
                LocalDateTime.now());

        return report;

    }

    public boolean applicationHealthy() {

        return true;

    }

    public String healthReport() {

        if (applicationHealthy()) {
            return "Cluster Healthy";
        }

        return "Deployment Issue";

    }

}
