package com.github.bobocodeProjects.functionalJava05.funcional;

import java.util.Optional;

import com.github.bobocodeProjects.model.CreditAccount;

@FunctionalInterface
public interface CreditAccountProvider {
    Optional<CreditAccount> getAccount();
}
