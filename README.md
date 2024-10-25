# PDF Generation API

## Project Overview
This Spring Boot API generates PDF invoices based on user-provided data and stores them in local storage. The API checks for existing PDFs to avoid redundant generation. This project is designed for backend use without UI, storing files in a specified directory on the local disk.

### Key Features
- Generates PDF invoices based on structured data input.
- Stores the generated PDF locally in a specified directory.
- Checks if a PDF for the same data already exists to avoid regeneration.
- Returns a structured response with the file path and success message.

