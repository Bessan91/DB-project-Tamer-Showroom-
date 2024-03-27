package application;

public class Car {
	private String Car_id;
	private String Car_brand;
	private String Car_model;
	private int Year_Car;
	private String color;
	private int Wieght_car;
	private double Price;
	private String technical_Specificatin;
	private int Number_of_cars;
	
	
	public Car() {}
	public Car(String Car_brand,int Number_of_cars) {
		this.Car_brand=Car_brand;
		this.Number_of_cars=Number_of_cars;
	}
	public Car(String Car_model,int Year_Car,String color) {
		this.Car_model=Car_model;
		this.color=color;
		this.Year_Car=Year_Car;
	}
	public Car(String car_id, String car_model, int year_Car, String color, int wieght_car, int price,String technical_Specificatin) {
		super();
		Car_id = car_id;
		Car_model = car_model;
		Year_Car = year_Car;
		this.color = color;
		Wieght_car = wieght_car;
		Price = price;
		this.technical_Specificatin = technical_Specificatin;
	}
	public Car(String Car_id,String Car_brand,String Car_model,int Year_Car,String color,
			int Wieght_car,int Price,String technical_Specificatin) {
		this.Car_id=Car_id;
		this.Car_brand=Car_brand;
		this.Car_model=Car_model;
		this.Year_Car=Year_Car;
		this.color=color;
		this.Wieght_car=Wieght_car;
		this.Price=Price;
		this.technical_Specificatin=technical_Specificatin;
		
	}
	public void Car2(String Car_id,String Car_brand, String Car_model,int Year_Car,String color,
			int Price,int Number_of_cars,String technical_Specificatin) {
		this.Car_id=Car_id;
		this.Car_brand=Car_brand;
		this.Car_model=Car_model;
		this.Year_Car=Year_Car;
		this.color=color;
		this.Price=Price;
		this.Number_of_cars=Number_of_cars;
		this.technical_Specificatin=technical_Specificatin;
	}
	
	/*public Car(String Car_id,String Car_brand,int Year_Car,String color,
			int Price,int Number_of_cars,String technical_Specificatin) {
		this.Car_id=Car_id;
		this.Car_brand=Car_brand;
		this.Year_Car=Year_Car;
		this.color=color;
		this.Price=Price;
		this.Number_of_cars=Number_of_cars;
		this.technical_Specificatin=technical_Specificatin;
	}*/
	public String getCar_brand() {
		return Car_brand;
	}
	public void setCar_brand(String car_brand) {
		Car_brand = car_brand;
	}
	
	
	public String getCar_id() {
		return Car_id;
	}
	public void setCar_id(String car_id) {
		Car_id = car_id;
	}
	public String getCar_model() {
		return Car_model;
	}
	public void setCar_model(String car_model) {
		Car_model = car_model;
	}
	public int getYear_Car() {
		return Year_Car;
	}
	public void setYear_Car(int year_Car) {
		Year_Car = year_Car;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getWieght_car() {
		return Wieght_car;
	}
	public void setWieght_car(int wieght_car) {
		Wieght_car = wieght_car;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getTechnical_Specificatin() {
		return technical_Specificatin;
	}
	public void setTechnical_Specificatin(String technical_Specificatin) {
		this.technical_Specificatin = technical_Specificatin;
	}
	public int getNumber_of_cars() {
		return Number_of_cars;
	}
	public void setNumber_of_cars(int number_of_cars) {
		Number_of_cars = number_of_cars;
	}
	@Override
	public String toString() {
	    return Car_id + "," + Car_brand + "," + Car_model + ","
	            + Year_Car + "," + color + "," + Wieght_car + "," + Price
	            + "," + technical_Specificatin;
	}



	
}
