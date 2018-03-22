package wuhang.java8.optional;

import java.util.Optional;

public class PersonO {
    
    private Optional<CarO> car;
    
    public PersonO(Optional<CarO> car) {
        this.car = car;
    }
    
    public Optional<CarO> getCar() {
        return car;
    }
}
