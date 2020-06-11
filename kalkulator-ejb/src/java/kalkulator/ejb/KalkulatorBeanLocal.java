/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.ejb;

import javax.ejb.Local;

/**
 *
 * @author rafli
 */
@Local
public interface KalkulatorBeanLocal {
    
    public double subtract(double minuend, double subtrahend);
    public double multiply(double factor1, double factor2);
    public double divide(double dividend, double divisor);
    public double add(double addend1, double addend2);
    public double equal(double value1, double value2, String op);
    public String isNumber(String oldValue, String newValue);
    public double reset();
    public String delete(String nowValue);
    public String History();
    public String History(String value1);
    public String History(String value1, String value2, String value3);
    public String History(String value1, String value2, String value3, String value4);
}
