# ocr_module.py
import requests
from PIL import Image
from io import BytesIO
import pytesseract

def perform_ocr_from_springboot(filename: str) -> str:
    """
    Fetches an image from Spring Boot's file-serving endpoint and performs OCR on it.
    """
    try:
        url = f"https://springboot-app.up.railway.app/uploads/{filename}"
        response = requests.get(url)
        response.raise_for_status()
        image = Image.open(BytesIO(response.content))
        text = pytesseract.image_to_string(image)
        return text
    except Exception as e:
        return f"OCR Failed: {str(e)}"
