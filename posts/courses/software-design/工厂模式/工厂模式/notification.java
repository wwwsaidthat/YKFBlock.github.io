
public class notification {
    public void main(){
        Factory factory = new EmailFactory();
        Notifier notifier = factory.createNotifier();
        notifier.notify("yaokaifang@example.com","Hello World");
    }
}
abstract class Notifier{
    abstract void notify(String to,String message);
}

class EmailNotifier extends Notifier{
    @Override
    void notify(String to,String message){
        System.out.println("Email: " + to + " " + message);
    }
}

class SmsNotifier extends Notifier{
    @Override
    void notify(String to,String message){
        System.out.println("Sms: " + to + " " + message);
    }
}

class PushNotifier extends Notifier{
    @Override
    void notify(String to,String message){
        System.out.println("Push: " + to + " " + message);
    }
}
abstract class Factory{
    abstract Notifier createNotifier();
}

class EmailFactory extends Factory{
    @Override
    Notifier createNotifier(){
        return new EmailNotifier();
    }
}
class SmsFactory extends Factory{
    @Override
    Notifier createNotifier(){
        return new SmsNotifier();
    }
}

class PushFactory extends Factory{
    @Override
    Notifier createNotifier(){
        return new PushNotifier();
    }
}
