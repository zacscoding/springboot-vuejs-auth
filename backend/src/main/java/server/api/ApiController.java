package server.api;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.accounts.Account;
import server.accounts.CurrentUser;

/**
 * @GitHub : https://github.com/zacscoding
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/home")
    public String home(@CurrentUser Account account) {
        logger.info("## request /home. user : {}", account == null ? null : account.getEmail());
        return "Hello " + extractUsername(account) + "!";
    }

    @GetMapping("/me")
    public Map<String, Object> me(@CurrentUser Account account) {
        logger.info("## request /me. user : {}", account.getEmail());

        Map<String, Object> ret = new HashMap<>();
        ret.put("email", account.getEmail());
        ret.put("roles", account.getRoles());

        return ret;
    }

    private String extractUsername(Account account) {
        if (account == null) {
            return "Anonymous";
        }

        int atIndex = account.getEmail().indexOf('@');
        if (atIndex > -1) {
            return account.getEmail().substring(0, atIndex);
        }

        return account.getEmail();
    }
}
