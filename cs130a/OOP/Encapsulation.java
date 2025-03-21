package OOP;

public class Encapsulation {
    private int weight;

    public int getWeight(){
        return weight;
    }

    public void setWeight(int value){
        weight = value;
    }
}

class ExploitShipping {
    public static void main (String[] args) {
      Encapsulation s = new Encapsulation();
      s.setWeight(-3);   // Still not the behavior we are looking for
    }
  }

class Shipping{
    private static final int MIN_WEIGHT = -1;
    private int weight;

    public int getWeight(){
        return weight;
    }

    public int setWeight(int value){
        return Math.max(MIN_WEIGHT, value);
    }
}

class ExploitShipping2 {
    public static void main (String[] args) {
      Shipping s = new Shipping();
      s.setWeight(-3);   // weight is set to MIN_WEIGHT
    }
  }