package com.company;

import com.company.userValidator.User;
import com.company.userValidator.UserValidator;

import java.time.LocalDate;

import static com.company.userValidator.UserValidator.*;

public class Main {

    public static void main(String[] args) {
	// write your code
        User user = new User(
                "Mahlangu",
                "mahlangupt.1010@gmail.com",
                "+27744xxx266",
                LocalDate.of(2015,10,10)
        );

        ValidResults results = isEmailValid().and(
                isPhoneValid())
                .and(isAldult())
                .apply(user);

        System.out.println(results);

    }
}
