package Common;

public class Admin extends Members {

    boolean owner;
    private String password;


//  ---------------------------------------------------------->cons

    public Admin() {}

    public Admin(String name , String lastname , String NCodde , int age , String gender , String phone, int id , boolean owner , String password) {
        super.SetName(name);
        super.SetAge(age);
        super.SetGender(gender);
        super.SetLastName(lastname);
        super.SetNationalCode(NCodde);
        super.SetPhoneNumber(phone);
        super.SetID(id);
        this.owner = owner;
        this.SetPassword(password);
    }

    @Override
    public String toString() {
        return super.toString()+"&"+GetOwner()+"&"+GetPassword();
    }

    //    ------------------------------------------------------------------->
    public boolean SetPassword(String pass) {
        if(pass.length()<5 || pass.length()>10) {
            return false;
        }
        this.password = pass;
        return true;
    }

    public boolean SetOwner(String s) {

        boolean b = false;
        if(s.equals("true")){
            b = true;
        }
        this.owner = b;


        return true;
    }




//    ------------------------------------------------------------------->Getter

    public boolean GetOwner() {
        return this.owner;
    }
    public String GetPassword() {
        return this.password;
    }


}