import java.util.List;
import java.util.ArrayList;
public class Main{
  public static void main(String[] args){
    Builder builder = new ConcreteBuilder();
    builder.buildPart();
    Product product = builder.getResult();
    product.show();
  }
}

class Product{
	List<String> parts;
  public void Add(String part){
    parts.add(part);
  }
  public void show(){
    System.out.print("Composed by:");
    for(String part:parts){
      System.out.print(part + " ");
    }
    System.out.println();
  }
}

abstract class Builder{
  public abstract void buildPart();
  public abstract Product getResult();
}
class ConcreteBuilder extends Builder{
  Product product= new Product();
  @Override
  public void buildPart(){
    product.Add("A");
    product.Add("B");
    product.Add("C");
  }
  @Override
  public Product getResult(){
    return product;
  }
}
