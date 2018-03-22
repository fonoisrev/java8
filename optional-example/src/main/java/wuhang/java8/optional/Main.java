package wuhang.java8.optional;

import wuhang.java8.tradition.Insurance;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println(getCarInsuranceName(new PersonO(Optional.of(new CarO(Optional.of(new Insurance("abd")))))));
        System.out.println(getCarInsuranceName(new PersonO(Optional.of(new CarO(Optional.of(new Insurance(null)))))));
    }
    
    public static String getCarInsuranceName(PersonO personO) {
        return Optional.ofNullable(personO).flatMap(PersonO::getCar).flatMap(CarO::getInsurance).map(Insurance::getName).orElse("UnKnown");
    }
}
