package com.sfeir.common.gwt.sample.moneyboard.server.utils;

import java.math.BigDecimal;

public class Validation{
    public static boolean checkRib(String rib) {
        StringBuilder extendedRib = new StringBuilder(rib.length());
        for (char currentChar : rib.toCharArray()) {
            //Works on base 36
            int currentCharValue = Character.digit(currentChar, Character.MAX_RADIX);
            //Convert character to simple digit
            extendedRib.append(currentCharValue<10?currentCharValue:(currentCharValue + (int) StrictMath.pow(2,(currentCharValue-10)/9))%10);
        }
 
        return new BigDecimal(extendedRib.toString()).remainder(new BigDecimal(97)).intValue() == 0;
    }
}