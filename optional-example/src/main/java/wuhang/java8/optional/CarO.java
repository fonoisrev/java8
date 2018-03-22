package wuhang.java8.optional;

import wuhang.java8.tradition.Insurance;

import java.util.Optional;

public class CarO {
    private Optional<Insurance> insurance;
    
    public CarO(Optional<Insurance>  insurance) {
        this.insurance = insurance;
    }
    
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
