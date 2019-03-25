package server.configuration;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import server.accounts.Account;
import server.accounts.AccountRepository;
import server.accounts.AccountRole;
import server.accounts.AccountService;
import server.configuration.properties.AppProperties;

/**
 * @GitHub : https://github.com/zacscoding
 */
@Configuration
public class AppConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            AccountService accountService;

            @Autowired
            AppProperties properties;

            @Autowired
            AccountRepository accountRepository;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Account admin = createAccount(properties.getAdminUsername(), properties.getAdminPassword(),
                    Arrays.asList(AccountRole.ADMIN, AccountRole.USER).stream().collect(Collectors.toSet())
                );

                Account user = createAccount(properties.getUserUsername(), properties.getUserPassword(),
                    Arrays.asList(AccountRole.USER).stream().collect(Collectors.toSet())
                );

                saveIfNotExist(admin);
                saveIfNotExist(user);
            }

            private void saveIfNotExist(Account account) {
                if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
                    return;
                }

                accountService.saveAccount(account);
            }

            private Account createAccount(String email, String password, Set<AccountRole> roles) {
                return Account.builder()
                    .email(email)
                    .password(password)
                    .roles(roles)
                    .build();
            }
        };
    }
}