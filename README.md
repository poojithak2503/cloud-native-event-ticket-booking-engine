# Cloud-Native Event Ticket Booking Engine

## Overview

The Cloud-Native Event Ticket Booking Engine is a scalable ticket reservation platform designed to handle high-volume event bookings during flash-sale scenarios. The application uses Spring Boot with a layered architecture and leverages asynchronous RabbitMQ messaging to process booking requests without overloading the system. The backend is designed to be deployed on Kubernetes with Docker containers and integrates with AWS RDS for persistent event data storage.

This project demonstrates cloud-native application development using Java, Spring Boot, RabbitMQ, Kubernetes, Docker, AWS RDS, Jenkins CI/CD, JUnit, and Mockito.

---

# Features

- Event Management
- Ticket Booking
- Seat Allocation
- Flash Sale Reservation Handling
- Asynchronous Booking Queue
- RabbitMQ Messaging
- Booking Cancellation
- Event Analytics
- Kubernetes Deployment Monitoring
- AWS RDS Ready Repository
- REST APIs
- Unit Testing

---

# Technology Stack

| Technology | Version |
|------------|----------|
| Java | 11 |
| Spring Boot | 2.x |
| Spring MVC | Latest |
| RabbitMQ | Latest |
| Kubernetes | Latest |
| Docker | Latest |
| AWS RDS | PostgreSQL |
| Jenkins | Latest |
| JUnit 5 | Latest |
| Mockito | Latest |
| Maven | 3.x |

---

# Project Structure

```
cloud-native-event-ticket-booking-engine
│
├── controller
│     BookingController.java
│
├── model
│     Event.java
│
├── repository
│     EventRepository.java
│
├── service
│     BookingService.java
│     SeatAllocationService.java
│     RabbitMQPublisherService.java
│     RabbitMQConsumerService.java
│     DeploymentMonitoringService.java
│
├── test
│     BookingServiceTest.java
│
└── TicketBookingApplication.java
```

---

# System Architecture

```
                 Customers

                     │

                     ▼

              REST API Requests

                     │

                     ▼

            BookingController

                     │

          ┌──────────┼──────────┐
          │          │          │

          ▼          ▼          ▼

 BookingService  SeatAllocation  RabbitMQPublisher

          │          │

          ▼          ▼

      EventRepository

          │

          ▼

      AWS RDS Database

                     │

                     ▼

          RabbitMQ Queue

                     │

                     ▼

      RabbitMQConsumerService

                     │

                     ▼

      Booking Confirmation

                     │

                     ▼

        Kubernetes Cluster
```

---

# Flash Sale Booking Workflow

```
Customer

   │

   ▼

Select Event

   │

   ▼

Choose Seats

   │

   ▼

Booking Request

   │

   ▼

RabbitMQ Queue

   │

   ▼

Seat Allocation Service

   │

   ▼

Reserve Seats

   │

   ▼

Booking Confirmed
```

---

# Seat Allocation Workflow

```
Booking Request

      │

      ▼

Check Event

      │

      ▼

Seats Available ?

      │

 ┌────┴────┐

 ▼         ▼

Yes        No

 │          │

 ▼          ▼

Reserve   Reject

 │

 ▼

Update Available Seats

 │

 ▼

Return Confirmation
```

---

# CI/CD Deployment Pipeline

```
Developer Push

      │

      ▼

Git Repository

      │

      ▼

Jenkins Pipeline

      │

      ▼

Compile

      │

      ▼

JUnit Tests

      │

      ▼

Build Docker Image

      │

      ▼

Push Image

      │

      ▼

Deploy to Kubernetes

      │

      ▼

Rolling Update

      │

      ▼

Production
```

---

# REST APIs

## Create Event

```
POST /api/events
```

Example Request

```json
{
  "eventId":1,
  "eventName":"Music Festival",
  "venue":"New York",
  "totalSeats":500,
  "ticketPrice":150,
  "organizer":"Live Nation"
}
```

---

## Get All Events

```
GET /api/events
```

---

## Get Event

```
GET /api/events/{eventId}
```

---

## Book Tickets

```
POST /api/events/{eventId}/book?seats=2
```

---

## Cancel Booking

```
POST /api/events/{eventId}/cancel?seats=2
```

---

## Booking Summary

```
GET /api/events/summary
```

---

# RabbitMQ Queue

Booking Queue

```
booking.queue
```

Example Messages

```
BOOK|101|2

BOOK|102|5

CANCEL|101|1
```

---

# Dashboard Metrics

The booking dashboard displays:

- Total Events
- Active Events
- Sold Out Events
- Available Seats
- Total Bookings
- Booking Revenue
- Queue Size
- RabbitMQ Status
- Kubernetes Pod Status
- Deployment Health

---

# Business Components

## BookingController

Exposes REST APIs for creating events, booking tickets, cancellations, and booking summaries.

---

## BookingService

Handles event management, ticket reservations, booking cancellation, revenue calculation, and booking statistics.

---

## SeatAllocationService

Implements synchronized seat allocation to prevent overbooking during flash-sale events.

---

## EventRepository

Provides CRUD operations for events and acts as the persistence layer for AWS RDS integration.

---

## RabbitMQPublisherService

Publishes booking and cancellation requests asynchronously to RabbitMQ queues.

---

## RabbitMQConsumerService

Consumes booking messages, processes seat reservations, and handles booking cancellations.

---

## DeploymentMonitoringService

Simulates Kubernetes deployment health, pod monitoring, Docker image status, and application availability.

---

## BookingServiceTest

Validates booking workflows, seat allocation logic, cancellations, and booking reports using JUnit and Mockito.

---

# Sample Dashboard

```
Cloud-Native Ticket Booking Dashboard

-------------------------------------

Total Events : 12

Total Bookings : 8245

Available Seats : 412

Sold Out Events : 4

Booking Revenue : $1,248,500

RabbitMQ Queue : Healthy

Running Pods : 5

AWS RDS : Connected

Deployment : Running
```

---

# Sample Booking Report

```
Booking Summary

--------------------------------

Event : Music Festival

Venue : New York

Total Seats : 500

Booked Seats : 472

Available Seats : 28

Revenue : $70,800

Booking Status : Active
```

---

# Testing Strategy

The project includes testing for:

- Event Creation
- Ticket Booking
- Booking Cancellation
- Seat Allocation
- Flash Sale Scenarios
- Sold Out Events
- Revenue Calculation
- RabbitMQ Message Processing
- Repository Operations
- Exception Handling

Testing Tools

- JUnit 5
- Mockito
- Mock Objects
- Assertions
- Service Layer Testing

---

# Enterprise Concepts Demonstrated

- Spring Boot
- Spring MVC
- Layered Architecture
- REST API Development
- RabbitMQ Messaging
- Event-Driven Architecture
- Asynchronous Processing
- Flash Sale Booking
- Concurrent Seat Allocation
- AWS RDS Integration
- Kubernetes Deployment
- Docker Containerization
- Jenkins CI/CD
- Repository Pattern
- Dependency Injection
- Object-Oriented Programming
- Unit Testing
- Mockito Mocking

---

# Future Enhancements

- Spring Security
- JWT Authentication
- OAuth2 Login
- Redis Distributed Cache
- Payment Gateway Integration
- Email Notifications
- SMS Notifications
- QR Code Tickets
- Kafka Event Streaming
- Prometheus Monitoring
- Grafana Dashboards
- Helm Charts
- Kubernetes Auto Scaling
- AWS ECS Deployment
- API Gateway
- OpenAPI / Swagger
- SonarQube
- Blue-Green Deployment

---

# Learning Outcomes

This project demonstrates practical implementation of:

- Cloud-Native Java Development
- Spring Boot REST APIs
- RabbitMQ Messaging
- Event-Driven Architecture
- High-Concurrency Seat Allocation
- Flash Sale Booking Systems
- Kubernetes Deployments
- Docker Containerization
- Jenkins CI/CD Pipelines
- AWS RDS Integration
- JUnit Testing
- Mockito Unit Testing
- Enterprise Layered Architecture

---

## Author

**Poojitha Kanuri**

Java Full Stack Developer

Email: poojithakanuri03@gmail.com

LinkedIn: https://linkedin.com/in/poojithakanuri

GitHub: https://github.com/poojithak2503
