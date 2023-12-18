package com.cashmanager.server.mediator.utils;

import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import lombok.Data;

import java.util.UUID;

@Data
public class MediatorResponse {
    private OperationStep currentOperationStep;
    private OperationStatus operationStatus;
    private UUID itemId;
    private String message;

    public MediatorResponse() {
    }

    public MediatorResponse(OperationStep currentOperationStep, OperationStatus operationStatus, String message) {
        this.currentOperationStep = currentOperationStep;
        this.operationStatus = operationStatus;
        this.message = message;
    }

    public MediatorResponse(OperationStep currentOperationStep, OperationStatus operationStatus, String message, UUID itemId) {
        this(currentOperationStep,operationStatus, message);
        this.itemId = itemId;
    }

}
