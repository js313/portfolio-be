# Portfolio Website Backend

This repository contains the backend services for my portfolio website. It handles API requests for fetching project lists, resumes, and contact form submissions. It is designed to support dynamic updates to the portfolio content via admin-only APIs.

## Features
- **RESTful API:** Provides endpoints for fetching project details, resume data, and handling contact form submissions.
- **Dynamic Content Management:** Admin APIs for creating, updating, and deleting projects.
- **Database Integration:** Uses MySQL for storing project data, contact messages, and other dynamic content.
- **Secure Admin Access:** Admin routes are protected to ensure only authorized changes are made.
- **Asynchronous Operations:** Efficient handling of multiple requests with Java's concurrency features.

## Technologies Used
- **Language:** Java
- **Framework:** Spring Boot
- **Database:** MySQL
- **Other Tools:** JDBC, Maven

## Installation

To set up this project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/js313/portfolio-backend.git
   ```
2. Navigate to the project directory:
   ```bash
   cd portfolio-backend
   ```
3. Configure the database:
   - Create a MySQL database named `portfolio`.
   - Update the database credentials in `application.properties`.
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/portfolio
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   java -jar target/portfolio-backend.jar
   ```
6. Access the API at `http://localhost:8080`.

## Docker Compose Setup
1. Ensure you have Docker and Docker Compose installed on your system.
2. Create a .env file in the project root and define the following variables:
   ```env
   MYSQL_ROOT_PASSWORD=your_root_password
   MYSQL_DATABASE=portfolio
   MYSQL_USER=your_mysql_user
   MYSQL_PASSWORD=your_mysql_password
   MYSQL_HOST=mysql
   SECRET_KEY=your_secret_key
   EXPIRATION_TIME=86400000 # Example: 1 day in milliseconds
   FIRST_ADMIN_PASSWORD=your_admin_password
   ```
3. Start the application using Docker Compose:
   ```bash
   docker-compose up --build
   ```
4. Access the API at `http://localhost:8080`.

## API Endpoints
### Public Endpoints
- GET /api/projects - Fetch the list of projects.
- GET /api/project-types - Fetch the list of project types.
- GET /api/resume - Fetch the resume data.
- GET /api/p5-sketch/{id} - Fetch the built sketch by id from github `p5-sketches` mono repo.
### Admin Endpoints
- POST /api/projects - Add a new project.
- PUT /api/projects/{id} - Update an existing project.
- DELETE /api/projects/{id} - Delete a project.
- POST /api/project-types - Add a new project type.
- PUT /api/project-types/{id} - Update an existing project type.
- DELETE /api/project-types/{id} - Delete a project type.
### Contact Form
- POST /api/contact - Submit a contact message.

## License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/js313/portfolio-be/blob/main/LICENSE) file for details.

## Contact
Feel free to reach out to me via:

GitHub: [@js313](https://github.com/js313)
Portfolio: [js313.github.io](https://js313.github.io)
