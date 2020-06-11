/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.ejb;

import javax.ejb.Stateful;

/**
 *
 * @author rafli
 */
@Stateful
public class KalkulatorBean implements KalkulatorBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public double add(double addend1, double addend2) {
        return addend1 + addend2;
    }
    @Override
    public double subtract(double minuend, double subtrahend) {
        return minuend - subtrahend;
    }
    @Override
    public double multiply(double factor1, double factor2) {
        return factor1 * factor2;
    }
    @Override
    public double divide(double dividend, double divisor) {
        return dividend / divisor;
    }
    
    @Override
    public double reset() {
        return 0.0;
    }
    
    @Override
    @SuppressWarnings("UnusedAssignment")
    public double equal(double value1, double value2, String op) {
        double results = 0.0;
        switch (op) {
            case "add":
                results = add(value1, value2);
                break;
            case "sub":
                results = subtract(value1, value2);
                break;
            case "div":
                results = divide(value1, value2);
                break;
            case "mul":
                results = multiply(value1, value2);
                break;
        }
        return results;
    }

    @Override
    public String isNumber(String oldValue, String newValue) {
        return (oldValue == null) ? newValue : oldValue+newValue;
    }

    @Override
    public String delete(String nowValue) {
        return nowValue.substring(0, nowValue.length()-1);
    }

    @Override
    public String History() {
        return null;
    }
    
    @Override
    public String History(String value1) {
        return value1;
    }

    @Override
    public String History(String value1, String value2, String value3) {
        String history = null;
        
        switch (value2) {
            case "add":
                history = value1+"+"+value3;
                break;
            case "sub":
                history = value1+"-"+value3;
                break;
            case "div":
                history = value1+"/"+value3;
                break;
            case "mul":
                history = value1+"x"+value3;
                break;
        }
        
        return history;
    }
    
    @Override
    public String History(String value1, String value2, String value3, String value4) {
        String history = null;
        
        switch (value2) {
            case "add":
                history = value1+"+"+value3+"="+value4;
                break;
            case "sub":
                history = value1+"-"+value3+"="+value4;
                break;
            case "div":
                history = value1+"/"+value3+"="+value4;
                break;
            case "mul":
                history = value1+"x"+value3+"="+value4;
                break;
        }
        
        return history;
    }
}
