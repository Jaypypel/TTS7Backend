package com.TTS.DbWebAPIs.Exceptions;

import jakarta.validation.constraints.NotNull;

public class InvalidAssignTaskRequestException extends RuntimeException {
    public InvalidAssignTaskRequestException(@NotNull String message) {
        super(message);
    }
}
