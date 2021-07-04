package com.github.bobocodeProjects.funcJava05;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.github.bobocodeProjects.exception.AccountNotFoundException;
import com.github.bobocodeProjects.functionalJava05.CrazyOptionals;
import com.github.bobocodeProjects.functionalJava05.funcional.AccountService;
import com.github.bobocodeProjects.model.Account;
import com.github.bobocodeProjects.model.CreditAccount;
import com.github.bobocodeProjects.util.Accounts;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Comparator.comparing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CrazyOptionalsTest {

    @Test
    public void optionalOfStringShouldAcceptNull() {
        Optional<String> optionalString = CrazyOptionals.optionalOfString(null);

        assertEquals(Optional.empty(), optionalString);
    }

    @Test
    public void optionalOfStringShouldHoldSting() {
        Optional<String> optionalString = CrazyOptionals.optionalOfString("Hello");

        assertEquals("Hello", optionalString.get());
    }

    @Test
    public void depositWhenAccountExistsShouldUpdateBalance() {
        Account account = Accounts.generateAccount();
        BigDecimal balanceBeforeUpdate = account.getBalance();
        BigDecimal amountToAdd = BigDecimal.valueOf(1000);

        CrazyOptionals.deposit(() -> Optional.of(account), amountToAdd);

        assertEquals(balanceBeforeUpdate.add(amountToAdd), account.getBalance());
    }

    @Test
    public void depositWhenOptionalIsEmptyShouldDoNothing() {
        CrazyOptionals.deposit(Optional::empty, BigDecimal.ZERO);
    }

    @Test
    public void optionalOfAccountShouldNotAcceptNull() {
        assertThrows(NullPointerException.class, () -> CrazyOptionals.optionalOfAccount(null));
    }

    @Test
    public void optionalOfAccountShouldHoldAccount() {
        Account account = Accounts.generateAccount();
        Optional<Account> optionalAccount = CrazyOptionals.optionalOfAccount(account);

        assertEquals(account, optionalAccount.get());
    }

    @Test
    public void getAccountShouldReturnAccountGotFromProvider() {
        Account providedAccount = Accounts.generateAccount();
        Account defaultAccount = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getAccount(() -> Optional.of(providedAccount), defaultAccount);

        assertEquals(providedAccount, receivedAccount);
    }

    @Test
    public void getAccountWhenNoAccountIsProvidedShouldReturnDefaultAccount() {
        Account defaultAccount = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getAccount(Optional::empty, defaultAccount);

        assertEquals(defaultAccount, receivedAccount);
    }

    @Test
    public void processAccountWhenAccountIsProvidedShouldPassAccountToService() {
        Account account = Accounts.generateAccount();
        BigDecimal initialBalance = account.getBalance();
        BigDecimal bonus = BigDecimal.valueOf(1000);

        CrazyOptionals.processAccount(() -> Optional.of(account), a -> a.setBalance(account.getBalance().add(bonus)));

        assertEquals(initialBalance.add(bonus), account.getBalance());
    }

    @Test
    public void processAccountWhenNoAccountProvidedShouldProcessWithNoAccount() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());
        AccountService accountService = mock(AccountService.class);

        CrazyOptionals.processAccount(() -> optionalAccountSpy, accountService);

        verify(accountService, times(1)).processWithNoAccount();
        verify(accountService, never()).processAccount(any());

    }

    @Test
    public void processAccountShouldUseNullSafeDeclarativeIfStatement() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());

        CrazyOptionals.processAccount(() -> optionalAccountSpy, account -> {
        });

        verify(optionalAccountSpy, times(1)).ifPresentOrElse(any(), any());
    }

    @Test
    public void getOrGenerateAccountShouldReturnAccountGotFromProvider() {
        Account providedAccount = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getOrGenerateAccount(() -> Optional.of(providedAccount));

        assertEquals(providedAccount, receivedAccount);
    }

    @Test
    public void getOrGenerateAccountWhenNoAccountIsProvidedShouldGenerateAccount() {
        Account receivedAccount = CrazyOptionals.getOrGenerateAccount(Optional::empty);

        assertNotNull(receivedAccount);
    }

    @Test
    public void getOrGenerateAccountWhenNoAccountIsProvidedShouldUseLazyInitialization() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());

        CrazyOptionals.getOrGenerateAccount(() -> optionalAccountSpy);

        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, never()).get();
        verify(optionalAccountSpy, times(1)).orElseGet(any());
    }

    @Test
    public void retrieveBalanceWhenAccountIsNotProvidedShouldReturnEmptyOptional() {
        Optional<BigDecimal> balance = CrazyOptionals.retrieveBalance(Optional::empty);

        assertFalse(balance.isPresent());
    }

    @Test
    public void retrieveBalanceWhenBalanceIsNullShouldReturnEmptyOptional() {
        Account account = Accounts.generateAccount();
        account.setBalance(null);

        Optional<BigDecimal> balance = CrazyOptionals.retrieveBalance(() -> Optional.of(account));

        assertFalse(balance.isPresent());
    }

    @Test
    public void retrieveBalanceShouldReturnOptionalBalance() {
        Account account = Accounts.generateAccount();

        Optional<BigDecimal> balance = CrazyOptionals.retrieveBalance(() -> Optional.of(account));

        assertEquals(account.getBalance(), balance.get());
    }

    @Test
    public void retrieveBalanceShouldUseNullSafeMapping() {
        Account account = Accounts.generateAccount();
        Optional<Account> optionalAccountSpy = spy(Optional.of(account));

        CrazyOptionals.retrieveBalance(() -> optionalAccountSpy);

        verify(optionalAccountSpy, never()).get();
        //verify(optionalAccountSpy, never()).isEmpty();
        verify(optionalAccountSpy, times(1)).isPresent();
        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, never()).orElseGet(any());
        verify(optionalAccountSpy, times(1)).map(any());
    }

    @Test
    public void getAccountShouldReturnProvidedAccount() {
        Account account = Accounts.generateAccount();

        Account receivedAccount = CrazyOptionals.getAccount(() -> Optional.of(account));

        assertEquals(account, receivedAccount);
    }

    @Test
    public void getAccountWhenNoAccountIsProvidedShouldThrowAccountNotFoundException() {
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> CrazyOptionals.getAccount(Optional::empty));
        assertEquals("No Account provided!", exception.getMessage());
    }

    @Test
    public void retrieveCreditBalanceWhenAccountIsNotProvidedShouldReturnEmptyOptional() {
        Optional<BigDecimal> creditBalance = CrazyOptionals.retrieveCreditBalance(Optional::empty);

        assertFalse(creditBalance.isPresent());
    }

    @Test
    public void retrieveCreditBalanceWhenBalanceIsNullShouldReturnEmptyOptional() {
        CreditAccount account = Accounts.generateCreditAccount();
        account.setCreditBalance(null);

        Optional<BigDecimal> creditBalance = CrazyOptionals.retrieveCreditBalance(() -> Optional.of(account));

        assertFalse(creditBalance.isPresent());
    }

    @Test
    public void retrieveCreditBalanceShouldReturnOptionalBalance() {
        CreditAccount account = Accounts.generateCreditAccount();

        Optional<BigDecimal> creditBalance = CrazyOptionals.retrieveCreditBalance(() -> Optional.of(account));

        assertEquals(account.getCreditBalance(), creditBalance);
    }

    @Test
    public void retrieveCreditBalanceShouldUseNullSafeMapping() {
        CreditAccount creditAccount = Accounts.generateCreditAccount();
        Optional<CreditAccount> optionalCreditAccountSpy = spy(Optional.of(creditAccount));

        CrazyOptionals.retrieveCreditBalance(() -> optionalCreditAccountSpy);

        verify(optionalCreditAccountSpy, never()).get();
        //verify(optionalCreditAccountSpy, never()).isEmpty();
        verify(optionalCreditAccountSpy, times(1)).isPresent();
        verify(optionalCreditAccountSpy, never()).orElse(any());
        verify(optionalCreditAccountSpy, never()).orElseGet(any());
        verify(optionalCreditAccountSpy, times(1)).flatMap(any());
    }

    @Test
    public void retrieveAccountGmailWhenNoAccountProvidedShouldReturnEmptyOptional() {
        Optional<Account> optionalGmailAccount = CrazyOptionals.retrieveAccountGmail(Optional::empty);

        assertEquals(Optional.empty(), optionalGmailAccount);
    }

    @Test
    public void retrieveAccountGmailWhenAccountEmailIsNotGmailShouldReturnEmptyOptional() {
        Account account = Accounts.generateCreditAccount();
        account.setEmail("bobby@yahoo.com");

        Optional<Account> optionalGmailAccount = CrazyOptionals.retrieveAccountGmail(() -> Optional.of(account));

        assertEquals(Optional.empty(), optionalGmailAccount);
    }

    @Test
    public void retrieveAccountGmailWhenEmailIsGmailShouldReturnEmail() {
        Account account = Accounts.generateCreditAccount();
        account.setEmail("johnny@gmail.com");

        Optional<Account> optionalGmailAccount = CrazyOptionals.retrieveAccountGmail(() -> Optional.of(account));

        assertEquals(account, optionalGmailAccount.get());
    }

    @Test
    public void retrieveAccountGmailShouldUseNullSafeFiltering() {
        Account account = Accounts.generateAccount();
        account.setEmail("johnny@gmail.com");
        Optional<Account> optionalAccountSpy = spy(Optional.of(account));

        CrazyOptionals.retrieveAccountGmail(() -> optionalAccountSpy);

        verify(optionalAccountSpy, never()).get();
        verify(optionalAccountSpy, times(1)).isPresent();
        //verify(optionalAccountSpy, never()).isEmpty();
        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, times(1)).filter(any());
    }

    @Test
    public void getAccountWithFallbackShouldBeRetrievedFromMainProvider() {
        Account account = Accounts.generateAccount();
        Account fallbackAccount = Accounts.generateAccount();

        Account retrievedAccount = CrazyOptionals.getAccountWithFallback(() -> Optional.of(account), () -> Optional.of(fallbackAccount));

        assertEquals(account, retrievedAccount);
    }

    @Test
    public void getAccountWithFallbackWhenNoAccountIsProvidedByMainProviderShouldUseFallback() {
        Account fallbackAccount = Accounts.generateAccount();

        Account retrievedAccount = CrazyOptionals.getAccountWithFallback(Optional::empty, () -> Optional.of(fallbackAccount));

        assertEquals(fallbackAccount, retrievedAccount);
    }

    @Test
    public void getAccountWithFallbackWhenNoAccountShouldThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> CrazyOptionals.getAccountWithFallback(Optional::empty, Optional::empty));
    }

    @Test
    public void getAccountWithFallbackShouldUseNullSafeFallbackStrategy() {
        Optional<Account> optionalAccountSpy = spy(Optional.empty());

        CrazyOptionals.getAccountWithFallback(() -> optionalAccountSpy, () -> Optional.of(Accounts.generateAccount()));

        verify(optionalAccountSpy, times(1)).isPresent();
        //verify(optionalAccountSpy, never()).isEmpty();
        verify(optionalAccountSpy, never()).get();
        verify(optionalAccountSpy, never()).orElse(any());
        verify(optionalAccountSpy, never()).orElseGet(any());
        verify(optionalAccountSpy, times(1)).or(any());
    }

    @Test
    public void getAccountWithMaxBalance() {
        List<Account> accounts = Accounts.generateAccountList(5);
        Account richestAccount = getMaxAccount(accounts, comparing(Account::getBalance));

        Account receivedAccount = CrazyOptionals.getAccountWithMaxBalance(accounts);

        assertEquals(richestAccount, receivedAccount);
    }

    @Test
    public void getAccountWithMaxBalanceWhenListIsEmptyShouldThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> CrazyOptionals.getAccountWithMaxBalance(Collections.emptyList()));
    }

    @Test
    public void findMinBalanceValueShouldReturnCorrectDoubleValue() {
        List<Account> accounts = Accounts.generateAccountList(5);
        Account accountWithLowestBalance = getMaxAccount(accounts, comparing(Account::getBalance).reversed());
        double expectedBalance = accountWithLowestBalance.getBalance().doubleValue();

        OptionalDouble optionalMinBalance = CrazyOptionals.findMinBalanceValue(accounts);

        assertEquals(expectedBalance, optionalMinBalance.getAsDouble(), 0.001);
    }

    @Test
    public void findMinBalanceValueWhenListIsEmptyShouldReturnOptionalEmpty() {
        OptionalDouble optionalMinBalance = CrazyOptionals.findMinBalanceValue(Collections.emptyList());

        assertEquals(OptionalDouble.empty(), optionalMinBalance);
    }

    @Test
    public void processAccountWithMaxBalance() {
        List<Account> accounts = Accounts.generateAccountList(5);
        Account richestAccount = getMaxAccount(accounts, comparing(Account::getBalance));
        AccountService accountServiceSpy = Mockito.spy(AccountService.class);

        CrazyOptionals.processAccountWithMaxBalance(accounts, accountServiceSpy);

        verify(accountServiceSpy, times(1)).processAccount(richestAccount);
    }

    @Test
    public void calculateTotalCreditBalanceShouldCalculateCorrectTotal() {
        List<CreditAccount> accounts = Accounts.generateCreditAccountList(5);
        double expectedTotal = calculateTotalCreditBalance(accounts);

        double calculatedTotal = CrazyOptionals.calculateTotalCreditBalance(accounts);

        assertEquals(expectedTotal, calculatedTotal, 0.001);
    }

    @Test
    public void calculateTotalCreditBalanceWhenListIsEmptyShouldReturnZero() {
        double calculatedTotal = CrazyOptionals.calculateTotalCreditBalance(Collections.emptyList());

        assertEquals(0.0, calculatedTotal, 0.001);
    }
    
    private Account getMaxAccount(List<Account> accounts, Comparator<Account> comparator) {
        return accounts.stream()
                .max(comparator)
                .orElseThrow();
    }

    private double calculateTotalCreditBalance(List<CreditAccount> accounts) {
        return accounts.stream()
                .map(CreditAccount::getCreditBalance)
                .flatMap(Optional::stream)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}
