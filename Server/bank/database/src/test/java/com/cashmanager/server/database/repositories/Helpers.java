package com.cashmanager.server.database.repositories;

import com.cashmanager.server.database.entities.PaymentMethod;
import com.cashmanager.server.database.entities.Transaction;
import com.cashmanager.server.database.entities.User;
import com.cashmanager.server.database.enums.TransactionStatus;
import com.cashmanager.server.database.enums.UserRole;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Helpers {
    public static User createUser() {
        return new User("username" + getRandomInt(0, 1000),
                "password", UserRole.CLIENT);
    }

    public static Transaction createTransaction(PaymentMethod method) {
        return new Transaction(method, TransactionStatus.PENDING,
                new BigDecimal(getRandomInt(0, 1000)), "receiver");
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
