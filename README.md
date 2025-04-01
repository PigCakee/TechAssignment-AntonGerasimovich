# TechAssignment-AntonGerasimovich

This repository contains a Payment App built as part of the Wallee Tech Assignment. The app demonstrates a clean architecture implementation using MVVM and Jetpack Compose. It includes two screens: a PinPad screen for entering a transaction amount and a Receipt screen that displays transaction details after processing a simulated payment.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
  - [Module Structure](#module-structure)
  - [Key Components](#key-components)
- [Setup and Usage](#setup-and-usage)
- [Testing](#testing)
- [Dependencies](#dependencies)
- [Screenshots](#screenshots)

---

## Overview

This Payment App allows users to enter a transaction amount via a PinPad interface, simulates a payment request by downloading and parsing JSON data from a REST API, and then displays a detailed receipt screen. The project is implemented using Clean Architecture and MVVM design patterns with Jetpack Compose for UI and Hilt for dependency injection.

---

## Features

- **PinPad Screen:**
  - A custom PinPad UI for entering the transaction amount.
  - Real-time formatting of the entered amount with thousand separators and fixed decimal places.
  - Haptic feedback for key presses.
  - Continuous backspace deletion via long press.
  - Loading spinner overlay during payment processing.
  - Error handling with toast notifications for invalid inputs (e.g., zero amount).

- **Receipt Screen:**
  - Displays transaction details including Transaction ID, Status, Final Amount (with applied tax, tip, and discount), Tax, Currency, and Date.
  - Data mapping and business logic to calculate tax and final amount.

- **Network Layer:**
  - Retrofit and OkHttp are used to communicate with the payment API.
  - JSON parsing is done with Kotlinx Serialization.
  - A dedicated network module handles API responses and error cases.

- **Unit Testing:**
  - Comprehensive tests for repository and ViewModel logic using JUnit 5, Google Truth, and Turbine.
  - Tests cover successful responses, HTTP errors, exception handling, and UI events.

---

## Architecture

The project is organized into multiple modules to achieve a clean separation of concerns:

### Module Structure
├── app/             // Android application module
├── common/
│   ├── di/          // Hilt DispatchersQualifiers
│   ├── navigation/  // Navigation Manager & related utils
│   ├── network/     // BaseRepository, NetworkResult, common network handling
│   └── utility/     // Utility classes (e.g., DateTimeUtils)
├── feature-payment/
│   ├── domain/      // Use cases, domain models
│   ├── data/        // Repositories, Retrofit APIs
│   ├── presentation // Compose UI, ViewModels
│   └── di/          // Hilt modules for payment feature
└── build-logic/     // Gradle convention plugins

### Key Components

- **Domain Layer:**
  - Contains domain models (e.g., `Transaction`) and repository interfaces.
  - Implements use cases (e.g., `MakePaymentUseCase`) that encapsulate business logic.

- **Data Layer:**
  - Contains data models representing API responses (e.g., `ResponseTransaction`, `ResponseAmount`).
  - Implements the `PaymentRepositoryImpl` which uses Retrofit to fetch data from the payment API.
  - Handles data mapping from network responses to domain models.

- **Presentation Layer:**
  - Implements the PinPad screen (for amount input) and Receipt screen (for displaying transaction details) using Jetpack Compose.
  - Manages UI state and user interactions via ViewModels (e.g., `PinPadViewModel`).
  - Applies error handling, loading states, and navigation (using a `NavigationManager`).

- **DI (Wiring) Layer:**
  - Provides Hilt modules to inject dependencies like Retrofit, API services, repositories, and use cases.
  - Ensures a clean separation and easy scalability.

---

## Setup and Usage

### Prerequisites

- Android Studio.
- JDK 11 or later.
- Gradle 8.x or later.

### Running the App

1. **Clone the Repository:**
   git clone https://github.com/PigCakee/TechAssignment-AntonGerasimovich.git
2.	Open in Android Studio:
	•	Open the project in Android Studio.
	•	Sync Gradle to download all dependencies.
3.	Build and Run:
	•	Select an emulator or connected device.
	•	Click Run to build and install the app.
4.	Usage:
	•	On the PinPad screen, enter the transaction amount using the custom keypad.
	•	Press the OK button to simulate a payment transaction.
	•	A loading spinner will appear while the payment is processed.
	•	Upon successful transaction, you will navigate to the Receipt dialog showing transaction details.

## Testing

Running Unit Tests

The project includes unit tests for repository and ViewModel logic. To run the tests:
	1.	From Android Studio:
	•	Right-click on the test folder and select Run Tests in ‘test’.
	2.	From Command Line:
  •	./gradlew test

Tests utilize JUnit 5, Google Truth, and Turbine for verifying network responses and UI events.

## Dependencies

Key dependencies are managed via Gradle version catalog (libs.versions.toml). Notable libraries include:
	•	Retrofit & OkHttp: For network communication.
	•	Kotlinx Serialization: For JSON parsing.
	•	Hilt: For dependency injection.
	•	Jetpack Compose: For UI development.
	•	JUnit 5, Mockk, Turbine, and Truth: For unit testing.

For the complete list of dependencies, please refer to the libs.versions.toml file in the root project directory.

## Screenshots
![Initial State](https://github.com/user-attachments/assets/33c25d3f-5b6e-45c4-9caf-7104b75983dc)
![A few digits](https://github.com/user-attachments/assets/e82bcba0-ac8d-4aff-9073-7676cc5b3da7)
![9 Digits](https://github.com/user-attachments/assets/5e7abe3f-e511-41a2-9f0c-36b07275a28d)
![Transaction Receipt](https://github.com/user-attachments/assets/95f55961-3827-4f08-b23e-5d8ebf199412)
