# main.py
from fastapi import FastAPI, HTTPException
from ocr_module import perform_ocr_from_springboot

app = FastAPI()

@app.get("/ocr")
def read_ocr(filename: str):
    """
    GET API to perform OCR on a file served by Spring Boot.
    Usage: /ocr?filename=image.png
    """
    try:
        result = perform_ocr_from_springboot(filename)
        return {"text": result}
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"OCR processing failed: {str(e)}")
