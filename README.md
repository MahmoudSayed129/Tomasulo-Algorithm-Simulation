# Tomasulo Algorithm Simulation

This project implements a simulation of the Tomasulo algorithm, closely following the original design to provide a detailed and accurate representation. It encompasses various components essential for simulating the functionality of floating-point operations, memory load and store operations, and the management of register files within a processor. Additionally, the project includes a graphical user interface (GUI) for user interaction and visualization of the Tomasulo algorithm in action.

## Project Structure

The simulation is structured into six main classes that represent different aspects of the Tomasulo algorithm, along with two additional classes for the GUI. Below is an overview of each class:

### 1. FP Addition Class
- **Attributes:**
  - Number of cells (reservation stations)
  - Number of empty cells in the reservation stations
  - Table (reservation station table)
- **Methods:**
  - `issue`: Handles the issuance of add instructions, managing operand readiness and reservation station occupancy.

### 2. FP Multiplication Class
- Similar in structure to the FP Addition class, this class handles multiplication instructions.

### 3. Loads Class
- **Attributes:**
  - Number of cells (reservation stations)
  - Number of empty cells in the reservation stations
  - Table (reservation station table)
- **Methods:**
  - `issue`: Manages the issuance of load instructions.
  - `isFull`: Checks if the reservation stations are full.
  - `publishResult`: Releases a reservation station after execution.
  - `getTable`: Returns the reservation station table.

### 4. Stores Class
- Structured similarly to the Loads class, this class deals with store instructions and includes methods for issuing instructions, checking station availability, and managing execution results.

### 5. Processor Class
- Serves as the central controller of the simulation, initializing all components and managing the execution pipeline.
- **Features:**
  - Holder table for tracking instructions
  - Parsing and handling instructions through various stages (issue, execute, write)
  - Functions to facilitate GUI interaction and provide insights into the simulation's progress

### 6. Register File Class
- Represents the floating-point registers, handling data storage and updates.
- **Methods:**
  - `listen`: Listens for results from reservation stations.
  - `get`: Retrieves values from the register file.
  - `update`: Updates values in the register file.

### 7. GUI
- Divided into two classes for user input and simulation visualization:
  - **Main View:** Collects user inputs regarding simulation parameters.
  - **Tomasulo View:** Displays the state of the simulation, including reservation stations, buffers, and register files, with cycle-by-cycle updates.

## Test Cases

The simulation's accuracy and functionality were validated against several test cases, including scenarios like read-after-write, write-after-write, write-after-read, full reservation stations, unavailable operands, and full program execution monitoring.

## Usage

1. Set up the simulation parameters through the Main View, specifying the number of slots for various reservation stations and buffers, operation execution cycles, memory size, and the instruction file name.
2. Transition to the Tomasulo View to visualize the simulation's progress, with the ability to advance through cycles and monitor the dynamic state of instructions and hardware components.

This project offers a comprehensive tool for understanding and exploring the Tomasulo algorithm through simulation, providing valuable insights into dynamic scheduling and execution within microprocessors.
