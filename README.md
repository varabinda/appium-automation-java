# Appium Java Automation Framework

This framework is a UI automation solution for both web and mobile applications using **Appium** and **Java**. It is designed for comprehensive test automation, supporting essential functionalities for mobile and web UI testing.

## Key Features

- **Cross-Platform Support**:
  - Automates both **mobile** applications (iOS and Android) and **web** applications with a unified codebase.

- **Appium Integration**:
  - Uses **Appium** for mobile automation, enabling test execution on physical or virtual devices.
  - Supports features like gestures, touch actions, and multi-touch events.

- **Selenium for Web Testing**:
  - Leverages **Selenium WebDriver** to interact with web applications, ensuring cross-browser compatibility.
  - Supports automation for actions like clicking, form input, and validations.

- **TestNG for Test Management**:
  - Uses **TestNG** to organize test cases, run parallel tests, and generate reports.
  - Provides test grouping, dependency management, and annotations for efficient test execution.

- **Page Object Model (POM)**:
  - Implements the **Page Object Model** design pattern, enhancing maintainability and reusability.
  - Each page or screen in the application is represented by a separate class, encapsulating UI interactions.

- **Utility Functions**:
  - Includes **utility classes** for common actions like waiting for elements, taking screenshots, and generating random data.

- **Test Data Management**:
  - Supports **external test data** (e.g., from files or databases) to facilitate data-driven testing.

- **Logging and Reporting**:
  - Provides detailed **logging** for debugging and tracking test execution.
  - Generates **TestNG** reports with pass/fail statistics and failure analysis.

- **Parallel Test Execution**:
  - Allows parallel test execution across different devices or browsers for improved test efficiency.

## Framework Structure

- **src/main/java**: Core classes, including Page Object Models, utility classes, and Appium driver management.
- **src/test/java**: Test cases for both web and mobile platforms.
- **resources**: Test configuration files (e.g., device capabilities, browser configurations).

## Usage

### 1. Setup
- Clone the repository and configure the environment with **Appium** and **Java**.
- Ensure necessary drivers (e.g., Android/iOS or browser drivers) are installed.

### 2. Writing Tests
- Extend the base test class and define actions for web or mobile UI.
- Use the Page Object Model to interact with elements for clean and maintainable test code.

### 3. Running Tests
- Execute tests using **TestNG** XML files or via Maven commands for continuous integration.

---

This framework provides a comprehensive solution for automating UI tests across mobile and web applications, making it a powerful tool for QA and development teams.


## Sample Test Flow: Verify Project Creation

This test script automates the process of verifying the creation of a new project in the mobile application. Below is a step-by-step breakdown of the process:

### 1. **Login**
- The test starts by logging into the application using a username and password retrieved from the test data.
  
### 2. **Navigate to Project Section**
- After logging in, the user is directed to the "My Office Inspection" page.
- The user selects the menu and navigates to the "Project" section.

### 3. **Initiate Project Creation**
- On the "Project" page, the "Create" button is clicked to initiate the creation of a new project.

### 4. **Enter Project Details**
- The user inputs the necessary project details such as project name, representative information, office, client, address, and additional configuration fields.

### 5. **Save the Project**
- Once all the required fields are filled, the project is saved, and the user is redirected to the "Project Detail" page to verify the details of the newly created project.

### 6. **Search and Verify Project**
- The user searches for the newly created project by its name.
- The search bar is closed once the project is verified.

### 7. **(Optional) Logout**
- A logout step is included but commented out in the current test. If enabled, it logs out the user from the application after project creation.
