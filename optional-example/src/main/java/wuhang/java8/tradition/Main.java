package wuhang.java8.tradition;

public class Main {
    public static void main(String[] args) {
        System.out.println(getCarInsuranceName(new Person(new Car(new
                Insurance("myname")))));
    }
    
    public static String getCarInsuranceName(Person person) {
        // 丑陋的null check代码，重复的Unknown返回
        if (person == null) {
            return "Unknown";
        }
        
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
    
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        
        return insurance.getName();
    }
}
