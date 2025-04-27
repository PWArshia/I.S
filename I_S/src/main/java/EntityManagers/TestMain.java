package EntityManagers;
import Common.Foods;
import Common.Members;
import Common.Rooms;
import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        FoodsManager manager=new FoodsManager("FoodList");

        Foods F1=new Foods(1,"asfg","fastfood",11,2);
        Foods F2=new Foods(1,"ghjfh","fastfood",52,3);
        Foods F3=new Foods(1,"fhkkf","fastfood",63,4);
        Foods F4=new Foods(1,"fkdk","fastfood",22,8);



        manager.UpdateFood(F1,F2);



    }

}
