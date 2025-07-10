from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import ocr_module

app = FastAPI()

@app.get("/ocr")
def read_ocr(url: str):
    try:
        result = ocr_module.perform_ocr_from_url(url)
        return {"text": result}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))
