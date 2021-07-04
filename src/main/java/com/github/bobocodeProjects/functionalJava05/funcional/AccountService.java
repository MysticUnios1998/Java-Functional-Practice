package com.github.bobocodeProjects.functionalJava05.funcional;

import com.github.bobocodeProjects.model.Account;

@FunctionalInterface
public interface AccountService {
    void processAccount(Account account);

    default void processWithNoAccount(){
        /* No operation */
    }
}
