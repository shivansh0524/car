import java.util.*;

class car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return (basePricePerDay * rentalDays);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class customer {
    private String customerId;
    private String name;

    public customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class rental {
    private car car;
    private customer customer;
    private int days;

    public rental(car car, customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public car getCar() {
        return car;
    }

    public customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class carRentalSystem {
    private List<car> cars;
    private List<customer> customers;
    private List<rental> rentals;

    public carRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(car car) {
        cars.add(car);
    }

    public void addCustomer(customer customer) {
        customers.add(customer);
    }

    public void rentCar(car car, customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(car car) {
        car.returnCar();
        rental rentalToRemove = null;
        for (rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned Successfully.");
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Car Rental System ===");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter your Choice:");

            int choice = sc.nextInt();
            sc.nextLine(); // consume next line

            if (choice == 1) {
                System.out.println("\n== Rent a Car ==");
                System.out.println("Enter Your Name:");
                String customerName = sc.nextLine();

                System.out.println("\n Available Cars:");
                for (car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " -" + car.getBrand() + " -" + car.getModel());
                    }
                }

                System.out.println("Enter the Car ID you want to rent:");
                String carId = sc.nextLine();

                System.out.println("Enter the number of days for rental:");
                int rentaldays = sc.nextInt();
                sc.nextLine(); // consume nextline

                customer newCustomer = new customer("shivansh" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                car selectedCar = null;
                for (car car : cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentaldays);
                    System.out.println("\n == Rental Information == \n");
                    System.out.println("Customer ID:" + newCustomer.getCustomerId());
                    System.out.println("Customer Name" + newCustomer.getName());
                    System.out.println("Car:" + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days:" + rentaldays);
                    System.out.println("Total Price : " + totalPrice);

                    System.out.println("\n Confirm Rental (Y/N) :");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentaldays);
                        System.out.println("\n Car Rented Successfully.");
                    } else {
                        System.out.println("Rental Cancelled.");
                    }
                } else {
                    System.out.println("\n Invalid Car Selection or Car is not Available for Rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n == Return a Car == \n");
                System.out.println("Enter the Car Id you want to return:");
                String carId = sc.nextLine();

                car carToReturn = null;
                for (car car : cars) {
                    if (car.getCarId().equalsIgnoreCase(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }
                if (carToReturn != null) {
                    customer customer = null;
                    for (rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned Successfully by:" + customer.getName());
                    } else {
                        System.out.println("Car was not Rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid Car id or the car is not rented");
                }
            } else if (choice == 3) {
                System.out.println("Exiting the car rental system.");
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        carRentalSystem rentalSystem = new carRentalSystem();
        car cr1 = new car("1", "Hyundai", "I20", 1300);
        car cr2 = new car("2", "Suzuki", "Ciaz", 1100);
        car cr3 = new car("3", "Kia", "Seltos", 1200);
        rentalSystem.addCar(cr1);
        rentalSystem.addCar(cr2);
        rentalSystem.addCar(cr3);
        rentalSystem.menu();
    }
}
