

# Contact Management App

This is a simple **Contact Management Application** built using **Spring Boot** for the backend and **Thymeleaf** for templating. The app allows users to add, edit, delete, search, and sort contacts. It uses a **database** (H2) for storing contact information with **persistent storage**.

---

## Features

- **Add Contacts**: Add new contacts with name, phone, and email.
- **View Contacts**: Display a list of all contacts stored in the database.
- **Edit Contacts**: Edit existing contact details.
- **Delete Contacts**: Remove contacts from the list.
- **Search Contacts**: Search contacts by name, phone, or email.
- **Sort Contacts**: Sort the contacts by name, phone, or email.
- **Persistent Storage**: Contacts are stored in a **database** (H2 by default, can be replaced with other databases).

---

## Technologies Used

- **Spring Boot**: Backend framework for building RESTful applications.
- **Thymeleaf**: Templating engine for rendering HTML pages.
- **Spring Data JPA**: Data access layer for database interaction.
- **H2 Database**: In-memory database for persistent storage (you can change it to MySQL, PostgreSQL, etc.).
- **HTML, CSS**: Frontend styling and layout.

---

## Project Scope

https://docs.google.com/document/d/1DQc9w1AUuiZeFqiejbFhxcWMOLPc50KPuZhjFgeyItI/edit?usp=sharing

## Setup & Installation

### 1. **Clone the Repository**

Clone the project from GitHub:

```bash
git clone https://github.com/Judinus10/ContactManagementApp.git
cd contact-management-app
```

### 2. **Install Dependencies**

If you haven't already, install **Maven** to handle the dependencies:

```bash
mvn clean install
```

### 3. **Run the Application**

Run the application with the following command:

```bash
mvn spring-boot:run
```

This will start the application on `http://localhost:8080`.

### 4. **Access the Application**

Once the application is running, open a browser and navigate to:

- **Home Page (Contacts List)**: `http://localhost:8080/`
- **Add Contact Page**: `http://localhost:8080/addContact`

---

## Database Configuration

The default database used is **H2**, which is an in-memory database. You can access the H2 console at:

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: ``

To switch to a different database (e.g., MySQL), update the `application.properties` file accordingly.

---

## File Structure

```
contact-management/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── demo/
│       │               ├── ContactApplication.java            # Main Spring Boot application
│       │
│       │               ├── controller/
│       │               │   └── ContactController.java         # Handles HTTP requests
│       │
│       │               ├── model/
│       │               │   └── Contact.java                   # Contact entity/model class
│       │
│       │               ├── repository/
│       │               │   └── ContactRepository.java         # JPA repository interface
│       │
│       │               └── service/
│       │                   └── ContactService.java            # Business logic
│
│       ├── resources/
│       │   ├── static/
│       │   │   ├── css/
│       │   │   │   └── style.css                              # Stylesheet
│       │   │   └── js/
│       │   │       └── script.js                              # JavaScript logic
│       │   │
│       │   ├── templates/
│       │   │   ├── addContact.html                            # Form for adding new contact
│       │   │   ├── confirmDelete.html                         # Confirmation page for deleting a contact
│       │   │   ├── editContact.html                           # Form for editing existing contact
│       │   │   └── index.html                                 # Main page to display all contacts
│       │   │
│       │   └── application.properties                         # Spring Boot config
│
├── .gitignore
├── README.md
├── LICENSE
└── pom.xml                                                   # Maven build configuration
```

---

## Running Tests

To run the tests (if any are added later):

```bash
mvn test
```

---

## Contribution

Feel free to fork and contribute to this project. Create a pull request for any improvements, bug fixes, or feature additions.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

