package com.cashmanager.server.database.repository;

import com.cashmanager.server.common.enumeration.TransactionStatus;
import com.cashmanager.server.database.entity.PaymentMethod;
import com.cashmanager.server.database.entity.Transaction;
import com.cashmanager.server.database.entity.User;
import com.cashmanager.server.database.enumeration.UserRole;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Helpers {
    public static User createUser() {
        return new User("username" + getRandomInt(0, 1000),
                "password", UserRole.CLIENT);
    }

    public static Transaction createTransaction(PaymentMethod method) {
        return new Transaction(method, TransactionStatus.PAYMENT_IN_PROGRESS,
                new BigDecimal(getRandomInt(0, 1000)), "receiver");
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
