//SISTEM MANAJEMEN MOBIL// 
public class App {
    public static void main(String[] args) throws Exception {
        Car car1 = new ElectricCar("Tesla", 50);
        Car car2 = new FuelCar("Toyota", 75);

        // Car 1 (Electric Type)
        System.out.println("ELECTRIC CAR");
        car1.tampilkanStatus();
        car1.startEngine();
        car1.shiftUp();
        car1.accelerate();
        car1.accelerate();
        car1.setBrake(true);
        car1.decelerate();
        car1.setBrake(false);
        car1.accelerate();
        car1.setBrake(true);
        ((ElectricCar) car1).reCharge();
        car1.tampilkanStatus();
        car1.decelerate();
        car1.decelerate();
        car1.decelerate();

        // Car 2 (fuel)
        System.out.println("\nFUEL CAR");
        car2.startEngine();
        car2.shiftUp();
        car2.accelerate();
        ((FuelCar) car2).reFuel();
        car2.tampilkanStatus();
        car2.accelerate();
        car2.shiftUp();
        car2.setBrake(true);
        car2.decelerate();
        car2.shiftDown();
        car2.stopEngine();
        car2.tampilkanStatus();

    }
}

// ABSTRACTION
abstract class Car {
    protected String brand;
    protected int speed = 0;
    protected int maxSpeed;
    protected boolean engineOn = false;
    protected boolean brakeOn;
    protected int gear = 1;

    // constructor
    public Car(String Brand) {
        this.brand = Brand;
        speed = 0;
    }

    // Setter brand
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter brand
    public String getBrand() {
        return brand;
    }

    // Setter speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Getter speed
    public int getSpeed() {
        return speed;
    }

    public void startEngine() {
        this.engineOn = true;
        System.out.println("Engine Started");
    }

    public void stopEngine() {
        this.engineOn = false;
        System.out.println("Engine Stopped");
    }

    // getter engine
    public boolean getEngine() {
        return engineOn;
    }

    // setter brake
    public void setBrake(boolean brake) {
        this.brakeOn = brake;
    }

    // getter brake
    public boolean getBrake() {
        return brakeOn;
    }

    // method
    public void shiftUp() {
        gear++;
        if (gear >= 10) {
            gear = 10;
        }
        System.out.println("Gear: " + gear);
    }

    public void shiftDown() {
        if (gear > 0) {
            gear--;
        }
        System.out.println("Gear: " + gear);
    }

    public void tampilkanStatus() {
        System.out.println("\nSTATUS");
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed);
        if (engineOn) {
            System.out.println("Engine: ON");
        } else {
            System.out.println("Engine: OFF");
        }
        if (brakeOn) {
            System.out.println("Brake: ON");
        } else {
            System.out.println("Brake: OFF");
        }
        System.out.println("Gear: " + gear + "\n");
    }

    // abstract method
    abstract void accelerate();

    abstract void decelerate();
}

// INHERITANCE
class ElectricCar extends Car {
    // ENCAPSULATION
    private int battery;

    // constructor
    public ElectricCar(String brand, int battery) {
        super(brand);
        this.battery = battery;
        this.maxSpeed = 120;
    }

    // POLYMORPHISM
    @Override
    public void accelerate() {
        if (engineOn && battery > 0 && !brakeOn) {
            speed = speed + 10;
            battery = battery - 5;
            if (speed > maxSpeed) {
                speed = maxSpeed;
            }
            System.out.println("Speed: " + speed + " | Battery: " + battery + "%");
        } else {
            System.out.println("Engine Off atau Baterry Habis atau Brake ON");
        }

    }

    @Override
    public void decelerate() {
        if (engineOn && brakeOn && battery > 0 && speed > 0) {
            speed = speed - 10;
            battery = battery - 5;
            if (speed <= 0) {
                speed = 0;
                System.out.println("Mobil (Electric Type) berhenti");
            }
            System.out.println("Speed: " + speed + " | Battery: " + battery + "%");
        }
    }

    // methood
    public void reCharge() {
        battery = battery + 20;
        if (battery >= 100) {
            battery = 100;
        }
        System.out.println("Battery: " + battery + "% (Charged)");
    }
}

// INHERITANCE
class FuelCar extends Car {
    // ENCAPSULATION
    private int gas;

    // constructor
    public FuelCar(String brand, int gas) {
        super(brand);
        this.gas = gas;
        this.maxSpeed = 100;
    }

    // POLYMORPHISM
    @Override
    public void accelerate() {
        if (engineOn && gas > 0 && !brakeOn) {
            speed = speed + 10;
            gas = gas - 5;
            if (speed > maxSpeed) {
                speed = maxSpeed;
            }
            System.out.println("Speed: " + speed + " | Gas: " + gas + "%");

        } else {
            System.out.println("Engine Off atau Gas Habis");
        }
    }

    @Override
    public void decelerate() {
        if (engineOn && brakeOn && gas > 0) {
            speed = speed - 5;
            gas = gas - 5;
            if (speed <= 0) {
                speed = 0;
                System.out.println("Mobil (fuel type) Berhenti");
            }
            System.out.println("Speed: " + speed + " | Gas: " + gas + "%");

        }
    }

    // method
    public void reFuel() {
        gas = gas + 20;
        if (gas > 100) {
            gas = 100;
        }
        System.out.println("Gas: " + gas + "% (Charged)");
    }
}