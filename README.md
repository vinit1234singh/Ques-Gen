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

```mermaid
graph LR
A[Client API Call] --> B[Upload Service (Spring Boot)]
B --> C[Save metadata to DB]
B --> D[FTP File to /IN Directory]
D --> E[OCR Service (Python)]
E --> F[Extract Text with Tesseract]
F --> G[Send Parsed Text to Response Generator]
G --> H[Call Google Gemini API]
H --> I[Return Generated AI Response]
