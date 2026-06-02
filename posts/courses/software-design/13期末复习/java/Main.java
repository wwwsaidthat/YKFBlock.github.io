public class Main{
    public static void main(String[] args){

    }
}
abstract class Order{
    int ID;
    double price;
    int num;
    String payMethod;

}
class NormalOrder extends Order{
    public NormalOrder(int ID, double price, int num, String payMethod){
        this.ID = ID;
        this.price = price;
        this.num = num;
        this.payMethod = payMethod;
    }
}
 
class PreOrder extends Order{
    public PreOrder(int ID, double price, int num){
        this.ID = ID;
        this.price = price;
        this.num = num;
        this.payMethod = "AliPay";
    }
}
class GroupOrder extends Order{

}
abstract class PaymentMethod{
    public abstract void pay(double amount);
}

class PaymentMethodProvider{
    PaymentMethod getPaymentMethod(String methodName){

    }
}

abstract class OrderProcessor{
    final void processOrder(Order order){
        System.out.println("check order");
        double totalPrice = (double)(order.price * order.num );

    }
    public abstract void doPayment(Order order);
}