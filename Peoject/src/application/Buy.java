//package application;
//
//import java.util.Date;
//
//public class Buy {
//     private int Sell_ID;
//	private String Customer_id;
//	private String  Car_model;
//    private String Car_id;
//    private Date Purchase_History;
//    private double countBuy;
//    private int countCar;
//    
//    
//    public String getCar_model() {
//		return Car_model;
//	}
//
//	public void setCar_model(String car_model) {
//		Car_model = car_model;
//	}
//
//	public int getSell_ID() {
// 		return Sell_ID;
// 	}
//
// 	public void setSell_ID(int sell_ID) {
// 		Sell_ID = sell_ID;
// 	}
//
//    public String getCustomer_id() {
//        return Customer_id;
//    }
//
//    public void setCustomer_id(String customer_id) {
//        Customer_id = customer_id;
//    }
//
//    public String getCar_id() {
//        return Car_id;
//    }
//
//    public void setCar_id(String car_id) {
//        Car_id = car_id;
//    }
//
//    public Date getPurchase_History() {
//        return Purchase_History;
//    }
//
//    public void setPurchase_History(Date purchase_History) {
//        Purchase_History = purchase_History;
//    }
//
//    public double getCountBuy() {
//        return countBuy;
//    }
//
//    public void setCountBuy(double countBuy) {
//        this.countBuy = countBuy;
//    }
//
//    public int getCountCar() {
//        return countCar;
//    }
//
//    public void setCountCar(int countCar) {
//        this.countCar = countCar;
//    }
//
//	public Buy(int sell_ID, String customer_id, String car_id, Date purchase_History, double countBuy, int countCar) {
//		super();
//		Sell_ID = sell_ID;
//		Customer_id = customer_id;
//		Car_id = car_id;
//		Purchase_History = purchase_History;
//		this.countBuy = countBuy;
//		this.countCar = countCar;
//	}
//	 public Buy(int sell_ID, String customer_id, String car_id, String Car_model, Date purchase_History, double countBuy, int countCar) {
//	        this(sell_ID, customer_id, car_id, purchase_History, countBuy, countCar);
//	        this.Car_model = Car_model;
//	    }
//
//	@Override
//	public String toString() {
//		return "Buy [Sell_ID=" + Sell_ID + ", Customer_id=" + Customer_id + ", Car_id=" + Car_id + ", Purchase_History="
//				+ Purchase_History + ", countBuy=" + countBuy + ", countCar=" + countCar + "]";
//	}
//
//
//}


package application;

import java.util.Date;

public class Buy {
     private int Sell_ID;
	private String Customer_id;
    private String Car_id;
    private Date Purchase_History;
    private double countBuy;
    private int countCar;
    
    
    
    public Buy() {
		super();
	}

	public int getSell_ID() {
 		return Sell_ID;
 	}

 	public void setSell_ID(int sell_ID) {
 		Sell_ID = sell_ID;
 	}

    public String getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(String customer_id) {
        Customer_id = customer_id;
    }

    public String getCar_id() {
        return Car_id;
    }

    public void setCar_id(String car_id) {
        Car_id = car_id;
    }

    public Date getPurchase_History() {
        return Purchase_History;
    }

    public void setPurchase_History(Date purchase_History) {
        Purchase_History = purchase_History;
    }

    public double getCountBuy() {
        return countBuy;
    }

    public void setCountBuy(double countBuy) {
        this.countBuy = countBuy;
    }

    public int getCountCar() {
        return countCar;
    }

    public void setCountCar(int countCar) {
        this.countCar = countCar;
    }

	public Buy(int sell_ID, String customer_id, String car_id, Date purchase_History, double countBuy, int countCar) {
		super();
		Sell_ID = sell_ID;
		Customer_id = customer_id;
		Car_id = car_id;
		Purchase_History = purchase_History;
		this.countBuy = countBuy;
		this.countCar = countCar;
	}

	@Override
	public String toString() {
		return Sell_ID + "," + Customer_id + "," + Car_id + ","
				+ Purchase_History + "," + countBuy + "," + countCar;
	}


}