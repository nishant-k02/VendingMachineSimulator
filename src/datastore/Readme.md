# 🥤 Vending Machine System – CS586 Final Project

## 📚 Overview

This project implements two vending machines (VM1 and VM2) using:

- 🧠 **State Design Pattern**
- 🧩 **Strategy Design Pattern**
- 🏭 **Abstract Factory Pattern**

The system models a real-world vending machine using centralized finite state machine (FSM) logic to manage credit, drinks, cups, and additives.

---

## 🏗️ Architecture

- `VM1` uses `float`-based pricing and additive logic (sugar, chocolate)
- `VM2` uses `int`-based pricing with different additives (sugar, cream)
- `MDA_EFSM` is the centralized FSM that controls transitions between states
- `OP` is the output processor delegating responsibilities to strategy classes
- `AbstractFactory` creates VM-specific components and strategies
- `DS1` and `DS2` handle different datatypes (float and int)

---

## 🧩 Design Patterns

### 🧠 1. State Pattern

- Implemented via `State` abstract class and 4 concrete states:
  - `Start`, `NoCups`, `Idle`, `CoinsInserted`
- `MDA_EFSM` holds a reference to all states and delegates actions

### 🧩 2. Strategy Pattern

- `OP` delegates operations to strategy interfaces:
  - `StorePrice`, `ReturnCoins`, `IncreaseCF`, `DisposeDrink`, `DisposeAdditives`, `ZeroCF`
- Each VM injects its own strategy implementation

### 🏭 3. Abstract Factory Pattern

- `VM1Factory` and `VM2Factory` generate:
  - `DS1` / `DS2`
  - Strategy implementations customized per VM

---

## 🔁 Additive Reset Logic (Latest Fix)

- After a drink is dispensed via `disposeDrink()`, the additives array `A[]` is now **reset to zero**
- This ensures **new customers start with no previously selected additives**

---

## 🚀 How to Compile and Run

### 🔧 Compile the Project

```bash
javac Main/*.java datastore/*.java factory/*.java mda_efsm/*.java op/*.java op/strategies/*.java state/*.java vm/*.java
```

---

### 🧑‍💻 Author

- Nishant Khandhar
- M.S. in Computer Science
- Illinois Institute of Technology
- nkhandhar@hawk.iit.edu
