# Ques-Gen: Headless Document Processing API

Ques-Gen is a microservices-based **headless API** designed for intelligent document processing. It enables seamless file uploads, OCR-based text extraction, and AI-powered response generation using Google Gemini. This backend system is ideal for integrating into personal AI agents, enterprise automation systems, or document intelligence platforms — all without a frontend.

---

## 🌐 Overview

This project includes 3 core microservices:

1. **Upload & Routing Service (Spring Boot)**  
   Accepts file + description, saves metadata to DB, and routes the file via FTP.

2. **OCR Service (Python)**  
   Monitors the `/IN` directory, extracts text using OCR, and sends parsed data via REST API.

3. **Response Generator (Spring Boot + Gemini API)**  
   Receives parsed text and generates contextual responses using Google Gemini AI.

---

## ⚙️ Architecture

<details> <summary>Click to expand Mermaid diagram</summary> <pre> ```mermaid graph TD A[Client API Call] --> B[Upload Service (Spring Boot)] B --> C[Save metadata to DB] B --> D[FTP File to /IN Directory] D --> E[OCR Service (Python)] E --> F[Extract Text with Tesseract] F --> G[Send Parsed Text to Response Generator] G --> H[Call Google Gemini API] H --> I[Return Generated AI Response] ``` </pre> </details>


## 💡 Key Features


✅ Headless API-first design (no frontend required)

📂 Multipart file upload + file description

🔄 FTP-based file transfer between services

🧠 Tesseract-based OCR in Python

🤖 AI response generation via Gemini API

🧱 Built to be extended for personal AI agent backends

💾 Database persistence for metadata

## 🧰 Tech Stack

Layer	Technology
Upload Service -->	Java 17, Spring Boot, JPA, MySQL

OCR Service	Python 3.x, Tesseract OCR, Flask

AI Generator	Spring Boot, REST APIs, Gemini API

File Transfer	FTP Protocol

Database	MySQL or PostgreSQL


## 🛠️ Setup & Usage

🔗 Prerequisites
Java 17+

Python 3.8+

Maven

easyOCR installed

FTP Server (e.g., vsftpd)

MySQL/Postgres

Google Gemini API Key
