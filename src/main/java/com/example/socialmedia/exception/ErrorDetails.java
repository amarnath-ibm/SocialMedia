package com.example.socialmedia.exception;

import java.time.LocalDateTime;

public record ErrorDetails(String message,
                           String description,
                           LocalDateTime timeStamp) {} 
