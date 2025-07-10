import requests
from PIL import Image
from io import BytesIO
import pytesseract

def perform_ocr_from_url(url: str) -> str:
    response = requests.get(url)
    img = Image.open(BytesIO(response.content))
    text = pytesseract.image_to_string(img)
    return text
