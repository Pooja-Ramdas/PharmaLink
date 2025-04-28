package com.pharmalink.exceptions;

public class MedicineNotFoundException extends Exception {
  public MedicineNotFoundException() {
    super("Medicine not found");
  }

  public MedicineNotFoundException(String message) {
    super(message);
  }

  public MedicineNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public MedicineNotFoundException(Throwable cause) {
    super(cause);
  }
}
