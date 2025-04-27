package Common;

public class Foods {
    private int id;
    private String FoodName;
    private enum FoodType {
        Fastfood,Stew,Kebab;
        public String toString() {
            if(this == Fastfood)
                return "FASTFOOD";
            if (this == Stew)
                return "STEW";
            if (this == Kebab)
                return "KEBAB";
            return "UNKNOWN";
        }

    }
    private FoodType foodType;
    private double FoodPrice;
    private int FoodQuantity;



    public Foods(){}


    public Foods(int id, String FoodName, FoodType foodType, double FoodPrice, int FoodQuantity) {
        this.SetID(id);
        this.SetFoodName(FoodName);
        this.foodType = foodType;
        this.FoodPrice = FoodPrice;
        this.FoodQuantity = FoodQuantity;
    }

    @Override
    public String toString() {
        return this.GetID()+Commons.Commons + this.GetFoodName() + Commons.Commons
                + this.GetFoodType() + Commons.Commons + this.GetFoodPrice() + Commons.Commons + this.GetFoodQuantity();
    }


    public boolean SetID(int id) {
        if(id>=0 && id<100){
            this.id = id;
            return true;
        }
        return false;
    }


    public int GetID() {
        return id;
    }



    public boolean SetFoodName(String FoodName) {
        if(FoodName.length()<3)
            return false;
        for (int x=0; x<FoodName.length(); x++) {
            if(!((FoodName.charAt(x)==' ') || (FoodName.charAt(x)>='a' && FoodName.charAt(x)<='z')
                    || (FoodName.charAt(x)>='A' && FoodName.charAt(x)<='Z')))
                return false;
        }
        this.FoodName = FoodName;
        return true;
    }


    public String GetFoodName() {
        return FoodName;
    }



    public boolean SetFoodPrice(double FoodPrice) {
        if(FoodPrice<0)
            return false;
        this.FoodPrice = FoodPrice;
        return true;
    }


    public double GetFoodPrice() {
        return FoodPrice;
    }

    public boolean SetFoodQuantity(int FoodQuantity) {
        if(FoodQuantity<0)
            return false;
        this.FoodQuantity = FoodQuantity;
        return true;
    }
    public int GetFoodQuantity() {
        return FoodQuantity;
    }

    public boolean SetFoodType(String foodType) {
        if(foodType.length()<3)
            return false;
        foodType=foodType.toUpperCase();
        if(foodType.equals("FASTFOOD")){
            this.foodType=FoodType.Fastfood;
            return true;
        }
        if(foodType.equals("STEW")){
            this.foodType=FoodType.Stew;
            return true;
        }
        if(foodType.equals("KEBAB")){
            this.foodType=FoodType.Kebab;
            return true;
        }
        return false;
    }


    public String GetFoodType() {
        return foodType.toString();
    }




}
