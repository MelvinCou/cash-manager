package com.cashmanager.server.account.verification;

import com.cashmanager.server.database.entities.User;
import com.cashmanager.server.database.enums.UserRole;
import com.cashmanager.server.database.repositories.UserRepository;

public final class UserVerification {
    private static UserRepository userRepository;

    /**
     * Private constructor to hide the implicit public one
     */
    private UserVerification() { }

    /**
     * Verify if the user exists
     * @param username the username to verify
     * @return true if the user exists, false otherwise
     */
    public static boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Verify if the user is admin
     * @param user the user to verify
     * @return true if the user is admin, false otherwise
     */
    public static boolean isAdmin(User user) {
        return user.getRole().equals(UserRole.ADMIN);
    }
}
