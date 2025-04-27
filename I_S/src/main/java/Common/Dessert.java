package Common;

public class Dessert {

    private String Name;
    private double price;
    private int DessertQuantity;

    public Dessert(){}

    public Dessert(String Name, double price, int DessertQuantity) {}

//    --------------------------------------------------------------------> Setter

    public boolean SetName(String name){
        if(name.length()<3)
            return false;
//        for (int x=0; x<name.length(); x++) {
//            if(!((name.charAt(x)==' ') || (name.charAt(x)>='a' && name.charAt(x)<='z')
//                    || (name.charAt(x)>='A' && name.charAt(x)<='Z')))
//                return false;
//        }
        if(name.indexOf(!((name.charAt(x)==' ') || (name.charAt(x)>='a' && name.charAt(x)<='z')
                    || (name.charAt(x)>='A' && name.charAt(x)<='Z'))){

        }
        this.Name = name;
        return true;
    }




}
