## Drone Service

### Introduction

This task is completed using spring boot framework and h2 as InMemory DB. 


### Requirements
- Java 11
- Lombok plugin support
- Enable Annotation Processor

Properties:

| property                       |    sample value    | description                                     |
|--------------------------------|:------------------:|-------------------------------------------------|
| `drone.max.number `            |         10         | Maximum Number of Drones Allowed  on The System |
| `drone.schedular.delayMs`      |       10000        | Delay in Milliseconds for Drone Scheduler Task  |
| `server.port`                  |        8080        | server http port                                |



### Run Instruction
- `./mvnw spring-boot:run`
- Database is automatically loaded with 1 drone on application start up.
- A maximum of 10 drones can be registered
- visit `./log/drone.log` to see the audit log

### Drone Life Cycle/Features:
- A new drone can be registered by supplying a unique Serial Number(Optional) and Model Name(Mandatory).
- Once the done is registered, a default 20 percent battery capacity will be allocated where the initial state will be IDLE.
- Drone is only allowed to load payload (medications) upto 500 grams once the battery is charged upto 25 percent and is in LOADING state.
- Charge is automatically done upto 100 percent through a Drone Scheduler Task, when the drone is in IDLE/LOADING state.
- Drone starts draining its battery by 20 percent when it is either in LOADED/DELIVERING/DELIVERED/RETURNING state.
- Drone will get back to IDLE state and charge continues automatically when it returns after item delivery.
- Drone weight including items weight and battery capacity will be calculated, streamed and logged at runtime.
- Drone weight will reset when the item is delivered/unloaded.

[//]: # (### Run Test)

### Postman
The Documentation for all sever endpoints can be found in `src/main/resources/Drone_API_Collection.postman_collection.json`

### Sample Log:
Drone is Idle. Now Charging... Data: {serial_number=TEST_123, weight=100.0, battery=20.0, state=IDLE, medications_size=0, medications_weight=0.0}

Drone is ready to load. Data: {serial_number=TEST_123, weight=100.0, battery=40.0, state=LOADING, medications_size=0, medications_weight=0.0}

Drone is loaded, Initiating delivery... Data: {serial_number=TEST_123, weight=200.0, battery=100.0, state=LOADED, medications_size=1, medications_weight=100.0}

Payload is being delivered. Data: {serial_number=TEST_123, weight=200.0, battery=80.0, state=DELIVERING, medications_size=1, medications_weight=100.0}

Payload is delivered. Data: {serial_number=TEST_123, weight=100.0, battery=60.0, state=DELIVERED, medications_size=0, medications_weight=0.0}

Drone is returned. Data: {serial_number=TEST_123, weight=100.0, battery=40.0, state=RETURNING, medications_size=0, medications_weight=0.0}

Drone is Idle. Now Charging... Data: {serial_number=TEST_123, weight=100.0, battery=20.0, state=IDLE, medications_size=0, medications_weight=0.0}

Drone is ready to load. Data: {serial_number=TEST_123, weight=100.0, battery=40.0, state=LOADING, medications_size=0, medications_weight=0.0}

