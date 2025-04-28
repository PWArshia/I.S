package Common;

public class Drinks {

        private int id;
        private String DrinkName;
        private enum DrinkType {
            Smoothie,Milkshake,Coffee;
            public String toString() {
                if(this == Smoothie)
                    return "SMOOTHIE";
                if (this == Milkshake)
                    return "MILKSHAKE";
                if (this == Coffee)
                    return "COFFEE";
                return "UNKNOWN";
            }

        }
        private DrinkType drinkType;
        private double DrinkPrice;
        private int DrinkQuantity;
        private double BuyPrice;



        public Drinks(){}


        public Drinks(int id, String DrinkName, String DrinkType, double DrinkPrice, int DrinkQuantity, double BuyPrice) {
            this.SetID(id);
            this.SetDrinkName(DrinkName);
            this.SetDrinkType(DrinkType);
            this.SetDrinkPrice(DrinkPrice);
            this.SetDrinkQuantity(DrinkQuantity);
            this.SetBuyPrice(BuyPrice);
        }

        @Override
        public String toString() {
            return this.GetID()+Commons.Commons + this.GetDrinkName() + Commons.Commons
                    + this.GetDrinkType() + Commons.Commons + this.GetDrinkPrice() +
                        Commons.Commons + this.GetDrinkQuantity()+ Commons.Commons+this.GetBuyPrice();
        }



        public boolean SetBuyPrice(double BuyPrice) {
            if (BuyPrice >= 0){
                this.BuyPrice = BuyPrice;
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



        public boolean SetDrinkName(String DrinkName) {
            if(DrinkName.length()<3)
                return false;
            for (int x=0; x<DrinkName.length(); x++) {
                if(!((DrinkName.charAt(x)==' ') || (DrinkName.charAt(x)>='a' && DrinkName.charAt(x)<='z')
                        || (DrinkName.charAt(x)>='A' && DrinkName.charAt(x)<='Z')))
                    return false;
            }
            this.DrinkName = DrinkName;
            return true;
        }


        public String GetDrinkName() {
            return DrinkName;
        }



        public boolean SetDrinkPrice(double DrinkPrice) {
            if(DrinkPrice<0)
                return false;
            this.DrinkPrice = DrinkPrice;
            return true;
        }


        public double GetDrinkPrice() {
            return DrinkPrice;
        }

        public boolean SetDrinkQuantity(int DrinkQuantity) {
            if(DrinkQuantity<0)
                return false;
            this.DrinkQuantity = DrinkQuantity;
            return true;
        }
        public int GetDrinkQuantity() {
            return DrinkQuantity;
        }

        public boolean SetDrinkType(String DrinkType) {
            if(DrinkType.length()<3)
                return false;
            DrinkType=DrinkType.toUpperCase();
            if(DrinkType.equals("SMOOTHIE")){
                this.drinkType= Drinks.DrinkType.Smoothie;
                return true;
            }
            if(DrinkType.equals("MILKSHAKE")){
                this.drinkType= Drinks.DrinkType.Milkshake;
                return true;
            }
            if(DrinkType.equals("COFFEE")){
                this.drinkType= Drinks.DrinkType.Coffee;
                return true;
            }
            return false;
        }


        public String GetDrinkType() {
            return drinkType.toString();
        }




    }


