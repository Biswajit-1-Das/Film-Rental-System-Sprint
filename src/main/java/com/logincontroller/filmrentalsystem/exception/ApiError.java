package com.logincontroller.filmrentalsystem.exception;

public record ApiError(int status, String message, String path) {
}
