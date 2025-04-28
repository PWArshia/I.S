package Common;

public class Dessert {

    private int id;
    private String Name;
    private double price;
    private int DessertQuantity;
    private double buyPrice;


//    ----------------------------------------------------------------->Cons

    public Dessert(){}

    public Dessert(int ID,String Name, double price, int DessertQuantity , double buyPrice) {
        this.SetId(ID);
        this.SetName(Name);
        this.SetPrice(price);
        this.SetDessertQuantity(DessertQuantity);
        this.SetBuyPrice(buyPrice);
    }

    @Override
    public String toString() {
        return  this.GetId()+Commons.Commons+this.GetName()+Commons.Commons+this.GetPrice()+Commons.Commons+this.GetBuyPrice()+Commons.Commons+this.GetDessertQuantity();
    }

//    --------------------------------------------------------------------> Setter

    public boolean SetId(int ID){
        if(ID<0){
            return false;
        }
        this.id=ID;
        return true;
    }

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

    public boolean SetBuyPrice(double buyPrice){
        if (buyPrice < 0){
            return false;
        }
        this.buyPrice = buyPrice;
        return true;
    }
//    ---------------------------------------------------------------------------------->Getter

    public int GetId(){
        return this.id;
    }
    public String GetName(){
        return this.Name;
    }
    public double GetPrice(){
        return this.price;
    }
    public int GetDessertQuantity(){
        return this.DessertQuantity;
    }
    public double GetBuyPrice(){
        return this.buyPrice;
    }





}
