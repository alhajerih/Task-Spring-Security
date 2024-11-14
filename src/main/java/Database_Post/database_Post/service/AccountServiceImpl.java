package Database_Post.database_Post.service;

import Database_Post.database_Post.entity.AccountEntity;
import Database_Post.database_Post.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity createAccount(Long userId) {
        AccountEntity account = new AccountEntity();
        account.setUserId(userId);
        account.setBalance(0.0);
        return accountRepository.save(account);
    }
}
