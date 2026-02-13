# Spring-ai Project

This is a demo Spring Boot project integrating Spring AI with OpenAI models.

## Features
- REST API endpoints for chat and streaming AI responses
- Uses Spring Boot 4.x and Spring AI 2.x
- Example controller for chat interactions

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- An OpenAI API key (do NOT commit your API key)

## Setup
1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd Spring-ai
   ```
2. **Configure your API key:**
   - Copy `src/main/resources/application.properties.example` to `src/main/resources/application.properties`.
   - Add your OpenAI API key and any other required properties. Example:
     ```properties
     spring.ai.openai.api-key=sk-...
     spring.ai.openai.base-url=https://api.openai.com/v1
     ```
   - **Never commit your real API key!**

3. **Build the project:**
   ```sh
   mvn clean install
   ```

4. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```
   Or, run the generated jar:
   ```sh
   java -jar target/Spring-ai-0.0.1-SNAPSHOT.jar
   ```

## API Endpoints
- `GET /chat?message=...` — Get a chat response
- `GET /stream` — Stream AI-generated content

## Notes
- The file `application.properties` is git-ignored for security.
- Do not share or commit your API key.

## License
MIT or as specified in the project.
