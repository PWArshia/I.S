package Common;

import EntityManagers.DessertManager;
import EntityManagers.DrinksManager;
import EntityManagers.FoodsManager;
import EntityManagers.MemberManager;

import java.io.IOException;
import java.time.LocalDate;

public class Receipt {
    private static final LocalDate NOW = LocalDate.now();
    private Members member;
    private MemberManager memberManager;
    private Dessert[] dessert=new Dessert[100];
    private DessertManager dessertManager;
    private int dessertQuantity[];
    private Drinks[] drink=new Drinks[100];
    private DrinksManager drinksManager;
    private int drinkQuantity[];
    private Foods food[]=new Foods[100];
    private FoodsManager foodsManager;
    private int foodQuantity[];
    private double total;
    private int ID;



    public Receipt(int member1,MemberManager memberManager1,int[] desserts,DessertManager dessertManager1,
                   int dessertQuantity1[],int[] drinks1, DrinksManager drinksManager1, int drinkQuantity1[],
                        int[] foods1, FoodsManager foodsManager1,int foodQuantity1[],int ID) throws IOException {
        this.foodQuantity=foodQuantity1;
        this.drinkQuantity=drinkQuantity1;
        this.dessertQuantity=dessertQuantity1;
        this.drinkQuantity=drinkQuantity1;
        this.foodQuantity=foods1;
        this.foodsManager=foodsManager1;
        this.memberManager=memberManager1;
        this.drinksManager=drinksManager1;
        this.dessertManager=dessertManager1;




        this.SetDessert(desserts);
        this.SetDrink(drinks1);
        this.SetFoods(foods1);
        this.SetMember(member1);
        this.SetID(ID);
        this.CalculateTotal();

    }


    public Receipt() {}


    public boolean SetID(int ID) {
        if(ID>=0) {
            this.ID = ID;
            return true;
        }
        return false;
    }

    public int GetID() {
        return ID;
    }


    public boolean SetMember(int memberCode) throws IOException {
        String MemberResult=memberManager.SearchMember(memberCode);
        if(MemberResult.equals(null)) {
            return false;
        }
        String MR[]=MemberResult.split(Commons.Commons);

        this.member=new Members(MR[0],MR[1],MR[2],Integer.parseInt(MR[3]),MR[4],MR[5],Integer.parseInt(MR[6]));
        return true;

    }

    public boolean SetDessert(int[] dessertCodes) throws IOException {
        int cCount=0;
        for (int x = dessertCodes.length-1;x>=0;x--){
            if (dessertCodes[x]!=0) {
                cCount=x+1;
                break;
            }
        }
        for (int x=0;x<cCount;x++) {
            String ResultSearch=dessertManager.Search(dessertCodes[x]);
            dessertManager.ReduceDessertQuantity(dessertCodes[x],dessertQuantity[x]);
            if(ResultSearch.equals(null)) {
                return false;
            }
            String R[]=ResultSearch.split(Commons.Commons);
            Dessert D1=new Dessert(Integer.parseInt(R[0]),R[1],Double.parseDouble(R[2]),
                    this.dessertQuantity[x],Double.parseDouble(R[4]));
            this.dessert[x]=D1;

        }
        return true;
    }


    public Dessert[] GetDessertsArray() {
        return dessert;
    }
    public int GetDessertArrayQuantity() {

        for (int i=dessert.length-1;i>=0;i--) {
            if(dessert[i]!=null) {
                return i+1;
            }
        }
        return 0;
    }



    public boolean SetDrink(int[] drinkCodes) throws IOException {
        int cArray=0;
        for (int x = drinkCodes.length-1;x>=0;x--) {
            if (drinkCodes[x]!=0) {
                cArray=x+1;
                break;
            }
        }
        for (int x=0;x<cArray;x++) {
            String ResultSearch=drinksManager.SearchDrink(drinkCodes[x]);
            drinksManager.ReduceDrinkQuantity(drinkCodes[x],drinkQuantity[x]);
            if(ResultSearch.equals(null)) {
                return false;
            }
            String R[]=ResultSearch.split(Commons.Commons);
            Drinks D2=new Drinks(Integer.parseInt(R[0]),R[1],R[2],
                    Double.parseDouble(R[3]),this.drinkQuantity[x],Double.parseDouble(R[5]));
            this.drink[x]=D2;
        }
        return true;
    }

    public Drinks[] GetDrinksArray() {
        return drink;
    }
    public int GetDrinkArrayQuantity() {
        for (int i=drink.length-1;i>=0;i--) {
            if(drink[i]!=null) {
                return i+1;
            }
        }
        return 0;
    }




    public boolean SetFoods(int[] foodCodes) throws IOException {
        int cArray=0;
        for (int x = foodCodes.length-1;x>=0;x--) {
            if (foodCodes[x]!=0) {
                cArray=x+1;
                break;
            }
        }
        for (int x=0;x<cArray;x++) {
            String ResultSearch=foodsManager.SearchFood(foodCodes[x]);
            foodsManager.ReduceDrinkQuantity(foodCodes[x],foodQuantity[x]);
            if(ResultSearch.equals(null)) {
                return false;
            }
            String R[]=ResultSearch.split(Commons.Commons);
            Foods F=new Foods(Integer.parseInt(R[0]),R[1],R[2],
                    Double.parseDouble(R[3]),this.foodQuantity[x],Double.parseDouble(R[5]));
            this.food[x]=F;
        }
        return true;
    }


    public Foods[] GetFoodsArray() {
        return food;
    }



    public int GetFoodArrayQuantity() {
        for (int i=food.length-1;i>=0;i--) {
            if(food[i]!=null) {
                return i+1;
            }
        }
        return 0;
    }


    public double CalculateTotal() {
        this.total=0;
        int cFoods=GetFoodArrayQuantity();
        for (int x=0;x<cFoods;x++) {
            this.total+=(this.food[x].GetFoodPrice()-this.food[x].GetBuyPrice())*this.foodQuantity[x];
        }


        int cDrinks=GetDrinkArrayQuantity();
        for (int x=0;x<cDrinks;x++) {
            this.total+=(this.dessert[x].GetPrice()-this.dessert[x].GetBuyPrice())*this.dessertQuantity[x];
        }


        int cDesserts=GetDessertArrayQuantity();
        for (int x=0;x<cDesserts;x++) {
            this.total+=(this.drink[x].GetDrinkPrice()-this.drink[x].GetBuyPrice())*this.drinkQuantity[x];
        }


        return this.total;
    }



    public LocalDate GetDate(){
        return NOW;
    }


    @Override
    public String toString() {
        String result=this.GetID()+Commons.Commons+NOW.toString()+Commons.Commons+"Member"+Commons.Commons+member.toString();
        int cFoods=GetFoodArrayQuantity();
        result+=Commons.Commons+"Foods";
        for (int i=0;i<cFoods;i++) {
            result +=Commons.Commons+this.food[i].toString();
        }

        result+=Commons.Commons+"Dessert";

        int cDesserts=GetDessertArrayQuantity();
        for (int i=0;i<cDesserts;i++) {
            result +=Commons.Commons+this.dessert[i].toString();
        }

        result+=Commons.Commons+"Drinks";

        int cDrinks=GetDrinkArrayQuantity();
        for (int i=0;i<cDrinks;i++) {
            result +=Commons.Commons+this.drink[i].toString();
        }
        result+=Commons.Commons+"Total";

        result+=Commons.Commons+this.total;
        return result;
    }


    public Dessert[] GetDessertArray() {
        return dessert;
    }
    public Drinks[] GetDrinkArray() {
        return drink;
    }
    public Foods[] GetFoodArray() {
        return food;
    }





}
