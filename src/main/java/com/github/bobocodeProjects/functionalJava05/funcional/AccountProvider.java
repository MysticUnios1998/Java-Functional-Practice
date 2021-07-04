package com.github.bobocodeProjects.functionalJava05.funcional;

import java.util.Optional;

import com.github.bobocodeProjects.model.Account;

@FunctionalInterface
public interface AccountProvider {
    Optional<Account> getAccount();
}
