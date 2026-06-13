package Repository;

import entities.BankAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BankRepo {

    private HashMap<String, BankAccount> accounts= new HashMap<>();


    public void save(BankAccount account){
        accounts.put(account.getAccountNumber(),account);
    }
    public Optional<BankAccount> findById(String accountNumber){
      return   Optional.ofNullable(accounts.get(accountNumber.trim()));

    }
    public List<BankAccount> findAll() {
        // We take the raw values from the HashMap and package them into a brand new List.
        return new ArrayList<>(accounts.values());
    }
}
