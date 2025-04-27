package Common;

public class Dessert {

    private String Name;
    private double price;
    private int DessertQuantity;


//    ----------------------------------------------------------------->Cons

    public Dessert(){}

    public Dessert(String Name, double price, int DessertQuantity) {
        this.SetName(Name);
        this.SetPrice(price);
        this.SetDessertQuantity(DessertQuantity);
    }

    @Override
    public String toString() {
        return  this.GetName()+Commons.Commons+this.GetPrice()+Commons.Commons+this.GetDessertQuantity();
    }

//    --------------------------------------------------------------------> Setter

    public boolean SetName(String name){
        if(name.length()<3)
            return false;
        for (int x=0; x<name.length(); x++) {
            if(!((name.charAt(x)==' ') || (name.charAt(x)>='a' && name.charAt(x)<='z')
                    || (name.charAt(x)>='A' && name.charAt(x)<='Z')))
                return false;
        }

        this.Name = name;
        return true;
    }

    public boolean SetPrice(double price){
        if (price < 0){
            return false;
        }
        this.price = price;
        return true;
    }

    public boolean SetDessertQuantity(int DessertQuantity){
        if (DessertQuantity < 0){
            return false;
        }
        this.DessertQuantity = DessertQuantity;
        return true;
    }
//    ---------------------------------------------------------------------------------->Getter


    public String GetName(){
        return this.Name;
    }
    public double GetPrice(){
        return this.price;
    }
    public int GetDessertQuantity(){
        return this.DessertQuantity;
    }





}
