package EntityManagers;
import Common.*;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        MemberManager memberManager=new MemberManager("MembersList");
        FoodsManager foodsManager=new FoodsManager("FoodList");
        DessertManager dessertManager=new DessertManager("Desserts");
        DrinksManager drinksManager=new DrinksManager("DrinksList");
        ReceiptsManager receiptsManager=new ReceiptsManager("Receipts",memberManager,
                foodsManager,drinksManager,dessertManager);
        Members M=new Members("kamy","Esmaeilpour","0250561409",20,
                "male","09126700629",25);


        Foods F=new Foods(65,"Koobide","kebab",50,20,30);
        int FoodCodes[]={F.GetID()};
        int FoodCount[]={3};



        Dessert D1=new Dessert(92,"MirzaGhasemi",40,20,10);
        int DessertCodes[]={D1.GetId()};
        int DessertCount[]={6};



        Drinks D2=new Drinks(43,"milkshake","Milkshake",50,10,20);
        int DrinkCodes[]={D2.GetID()};
        int DrinkCount[]={8};

        Receipt receipt=new Receipt(M.GetID(),memberManager,DessertCodes,dessertManager,DessertCount,DrinkCodes,drinksManager
        ,DrinkCount,FoodCodes,foodsManager,FoodCount,56);


        InvoiceManager invoiceManager=new InvoiceManager("InvoiceList",receiptsManager.GetReceiptsArray());
        invoiceManager.SetReceipt();



    }

}
