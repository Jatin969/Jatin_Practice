package Abstraction_Examples.UpiBanking.bank.sbi;

import Abstraction_Examples.UpiBanking.bank.BankAccountDetails;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class bankAccountsRepo {
    static Set<BankAccountDetails> cards = new HashSet<>();
    static HashMap<String, Integer> upiAndPin = new HashMap<>();
}
