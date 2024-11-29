### MicroboxLabs Backend Challenge: Logs Ingestion and Viewing (Java Quarkus)
This technical test requires you to design and implement a **Logs Ingestion and Viewing** backend using **Java Quarkus**. The backend will help manage incoming log entries, store them, and provide API endpoints to view and filter the logs.

#### **Before You Begin**
Fork this repository and invite the provided collaborators: `@korutx`, `@odtorres`. Should you have any questions, contact `devtest@microboxlabs.com`. 

#### **Guidelines**

- We provide a basic Quarkus project with a basic setup.
- You can use any additional libraries or tools you see fit, but make sure to justify your choices.
- Quarkus documentation is available [here](https://quarkus.io/guides/).

#### **Problem Description**
MicroboxLabs needs a backend system to help their engineering team efficiently manage incoming log entries from various microservices. The new system will provide REST API endpoints for **logs ingestion**, **storage**, and **viewing** by users. The backend will need to handle log parsing, storage, and filtering based on different criteria.

#### **Core Requirements**
The goal is to create a **Java Quarkus** backend application that provides REST API endpoints for log ingestion, viewing, and filtering. The key features are as follows:

### **API Features**
1. **Logs Ingestion Endpoint (Admin Only)**
   - Create an API endpoint (`/api/logs/upload`) that accepts a **plain text file** containing log entries.
   - Each log entry in the file must have the following format:
     ```
     [2024-11-01 10:00:00] [INFO] Service-A: Successfully completed task.
     [2024-11-01 10:01:00] [ERROR] Service-B: Failed to connect to the database.
     [2024-11-01 10:02:00] [WARNING] Service-C: Response time is slow.
     ```
   - Parse each log entry and store it in the database with the following fields:
     - **Timestamp** (e.g., `2024-11-01 10:00:00`)
     - **Log Level** (e.g., `INFO`, `ERROR`, `WARNING`)
     - **Service Name** (e.g., `Service-A`)
     - **Message** (e.g., `Successfully completed task`)

2. **View Logs Endpoint**
   - Create an API endpoint (`/api/logs`) that allows users to view all log entries.
   - The endpoint should provide pagination to efficiently handle large amounts of logs.
   - The response should include fields like **timestamp**, **log level**, **service name**, and **message**.

3. **Filtering Logs Endpoint**
   - Create an API endpoint (`/api/logs/filter`) that allows users to filter logs based on the following parameters:
     - **Date Range**: Start and end dates.
     - **Log Level**: Filter by `INFO`, `WARNING`, `ERROR`.
     - **Service Name**: Filter by the name of the service (e.g., `Service-A`).

### **Database Requirements**
- Use a lightweight database (e.g., **H2** or **PostgreSQL**).
- Store each log entry as a separate record in the database with the necessary fields.

### **Authorization**
- Implement **basic authorization** to distinguish between **Admin** users and **Regular** users:
  - **Admin User**: Allowed to upload log files.
  - **Regular User**: Allowed only to view and filter logs.

### **Technologies and Tools to Use**
- **Backend Framework**: Quarkus (Java).
- **Database**: H2 or PostgreSQL for storing log entries.
- **RESTful API**: Use **Quarkus RESTEasy** to implement the endpoints.
- **Security**: Implement basic authentication using **Quarkus Security**.

### **Use Cases**
1. **Admin Uploads Logs**:
   - An **Admin** user calls the `/api/logs/upload` endpoint to upload a plain text log file.
   - The backend parses the file and stores each log entry in the database.

2. **Viewing Logs**:
   - A **Regular User** makes a GET request to `/api/logs` to view logs.
   - The backend responds with a paginated list of log entries.

3. **Filtering Logs**:
   - A user makes a GET request to `/api/logs/filter` with parameters like date range, log level, or service name.
   - The backend returns the filtered log entries.

#### **Aspects to Be Evaluated**
1. **Functionality**:
   - Does the solution meet all the core requirements?
   - Are users able to upload, view, and filter logs effectively?
2. **Software Design**:
   - Proper use of Quarkus features and logical organization of code.
   - Clean separation between data, business, and presentation layers.
3. **Code Quality**:
   - Readable and maintainable code with meaningful comments.
   - Effective use of modern Java and Quarkus features.
4. **Testing**:
   - **Unit Tests** for parsing logs and storing entries.
   - **Integration Tests** for the API endpoints.
5. **Documentation**:
   - Clear instructions on how to set up and run the project.
   - In-line comments for complex parts of the code.

#### **Aspects to Ignore**
- **Deployment**: Focus on local execution and testing rather than deployment.
- **Advanced Security**: Keep authentication simple, as the emphasis is on functionality.

#### **Optional Bonus Points**
- **Swagger Documentation**: Use **Quarkus OpenAPI** to add Swagger documentation for the API.
- **Real-Time Streaming**: Add support for a real-time streaming endpoint using **Server-Sent Events (SSE)** to push new log entries to users as they arrive.

#### **Getting Started**
1. **Fork/Clone** the repository.
2. Set up a **Quarkus** project and configure your chosen database.
3. Implement the required REST API endpoints.
4. Use any tools or resources, including AI (e.g., ChatGPT or GitHub Copilot), to assist you.

This challenge aims to evaluate your ability to work on a backend application using **Java Quarkus**, focusing on REST API creation, database integration, and effective use of the Quarkus framework.
