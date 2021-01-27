package com.company.userValidator;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
import static com.company.userValidator.UserValidator.*;
import static com.company.userValidator.UserValidator.ValidResults.*;

public interface UserValidator extends Function<User, ValidResults> {

    static UserValidator isEmailValid(){
        return user -> user.getEmail().contains("@") ?
                SUCCESS: EMAIL_NUMBER_NOT_VALID;
    }

    static UserValidator isPhoneValid(){
        return user -> user.getPhoneNumber().startsWith("+27") ?
                SUCCESS: PHONE_NUMBER_NOT_VALID;
    }

    static UserValidator isAldult(){
        return user -> Period.between(user.getDob(),
                LocalDate.now()).getYears() > 18 ?
                SUCCESS: IS_NOT_AN_ADULT;
    }
    // Combinator
    default UserValidator and ( UserValidator other ){
        return user -> {
          ValidResults results = this.apply(user);
          return results.equals(SUCCESS)? other.apply(user):results;
        };
    }

    enum ValidResults{
        SUCCESS,
        PHONE_NUMBER_NOT_VALID,
        EMAIL_NUMBER_NOT_VALID,
        IS_NOT_AN_ADULT
    }
}
