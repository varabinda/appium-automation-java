## Test Flow: Verify Project Creation

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
