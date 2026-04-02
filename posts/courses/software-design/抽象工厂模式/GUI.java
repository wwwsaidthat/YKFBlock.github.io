
public class GUI {
    public static void main(String[] args){
        Factory factory = new WindowFactory();
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        button.paint();
        checkbox.paint();
        factory = new MacFactory();
        button = factory.createButton();
        checkbox = factory.createCheckbox();
        button.paint();
        checkbox.paint();
    }
}


abstract class Button{
    abstract void paint();
}

abstract class Checkbox{
    abstract void paint();
}

class WindowButton extends Button{
    @Override
    void paint(){
        System.out.println("WindowButton");
    }
}

class MacButton extends Button{
    @Override
    void paint(){
        System.out.println("MacButton");
    }
}

class WindowCheckbox extends Checkbox{
    @Override
    void paint(){
        System.out.println("WindowCheckbox");
    }
}
class MacCheckbox extends Checkbox{
    @Override
    void paint(){
        System.out.println("MacCheckbox");
    }
}
abstract class Factory{
    abstract Button createButton();
    abstract Checkbox createCheckbox();
}

class WindowFactory extends Factory{
    @Override
    Button createButton(){
        return new WindowButton();
    }
    @Override
    Checkbox createCheckbox(){
        return new WindowCheckbox();
    }
}

class MacFactory extends Factory{
    @Override
    Button createButton(){
        return new MacButton();
    }
    @Override
    Checkbox createCheckbox(){
        return new MacCheckbox();
    }
}