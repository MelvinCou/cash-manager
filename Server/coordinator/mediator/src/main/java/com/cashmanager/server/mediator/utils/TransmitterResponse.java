package com.cashmanager.server.mediator.utils;

import com.cashmanager.server.common.enumeration.OperationStatus;
import com.cashmanager.server.common.enumeration.OperationStep;
import lombok.Data;

@Data
 public class TransmitterResponse<T> {
    private T data;
    private OperationStep operationStep;
    private OperationStatus operationStatus;
    private String message;
}
