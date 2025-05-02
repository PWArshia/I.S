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
    private double BuyPrice;
    private int BuyQuantity;



    public Foods(){}


    public Foods(int id, String FoodName, String foodType, double FoodPrice, int FoodQuantity, double BuyPrice) {
        this.SetID(id);
        this.SetFoodName(FoodName);
        this.SetFoodType(foodType);
        this.SetFoodPrice(FoodPrice);
        this.SetFoodQuantity(FoodQuantity);
        this.SetBuyPrice(BuyPrice);
    }

    @Override
    public String toString() {
        return this.GetID()+Commons.Commons + this.GetFoodName() + Commons.Commons
                + this.GetFoodType() + Commons.Commons + this.GetFoodPrice() + Commons.Commons
                    + this.GetFoodQuantity()+ Commons.Commons+ this.GetBuyPrice();
    }


    public boolean SetBuyPrice(double buyPrice) {
        if(buyPrice >=0){
            this.BuyPrice = buyPrice;
            return true;
        }
        return false;
    }



    public double GetBuyPrice() {
        return this.BuyPrice;
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
