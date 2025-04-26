package Common;


import org.jetbrains.annotations.NotNull;

public class Members {
    @NotNull
    private String Name;
    private int Age;
    private enum Gender {
        MALE, FEMALE
    }
    Gender gender;
    private String LastName;
    private String National_Code;
    private String PhoneNumber;
    private int ID;

    public Members() {}


    public Members( String Name,String LastName ,String National_Code , int Age,String gender ,String PhoneNumber ,int ID) {
        this.SetName(Name);
        this.SetAge(Age);
        this.SetGender(gender);
        this.SetLastName(LastName);
        this.SetNationalCode(National_Code);
        this.SetPhoneNumber(PhoneNumber);
        this.SetID(ID);
    }


    public int SetName( String name) {
        boolean check=true;
        if(name.length()==0)
            check=false;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if ((ch >= 'A' && ch <= 'Z')) {
                check=true;
            }
            else if (ch >= 'a' && ch <= 'z') {
                check=true;
            }
            else if ((ch!=' ')) {
                check=true;
            }
            else {
                check=false;
            }
        }

        if (!check) {
            return 0;
        }
        else {
            this.Name = name;
            return 1;
        }

    }



    public int SetAge( int age) {
        if(age>=18) {
            this.Age = age;
            return 1;
        }
        else {
            return 0;
        }
    }

    public int SetGender( String gender) {
        if(gender.length()==0)
            return 0;
        gender = gender.toLowerCase();
        if (gender.equals("male")) {
            this.gender = Gender.MALE;
            return 1;
        }
        else if (gender.equals("female")) {
            this.gender = Gender.FEMALE;
            return 1;
        } else if (gender.equals("boy")) {
            this.gender = Gender.MALE;
            return 1;
        } else if (gender.equals("girl")) {
            this.gender = Gender.FEMALE;
            return 1;
        }
        else return 0;
    }

    public int SetLastName( String lastName) {
        boolean check=true;
        if(lastName.length()==0)
            check=false;
        for (int i = 0; i < lastName.length(); i++) {
            char ch = lastName.charAt(i);
            if ((ch >= 'A' && ch <= 'Z')) {
                check=true;
            }
            else if (ch >= 'a' && ch <= 'z') {
                check=true;
            }
            else if ((ch!=' ')) {
                check=true;
            }
            else {
                check=false;
            }

        }
        if (!check) {
            return 0;
        }
        else {
            this.LastName = lastName;
            return 1;
        }

    }


    public int SetNationalCode( String nationalCode) {
        boolean check=true;
        if(nationalCode.length()!=10)
            check=false;
        for (int i = 0; i < nationalCode.length(); i++) {
            char ch = nationalCode.charAt(i);
            if(!(ch>='0' && ch<='9'))
                check=false;
        }
        if (!check) {
            return 0;
        }
        else {
            this.National_Code = nationalCode;
            return 1;
        }
    }

    public int SetPhoneNumber( String phoneNumber) {
        boolean check=true;
        if(phoneNumber.length()!=11)
            check=false;
        for (int i = 0; i < phoneNumber.length(); i++) {
            char ch = phoneNumber.charAt(i);
            if(!(ch>='0' && ch<='9'))
                check=false;
        }
        if (!check) {
            return 0;
        }
        else {
            this.PhoneNumber = phoneNumber;
            return 1;
        }
    }


    public int SetID(int ID) {
        boolean check=true;
        if(ID<0 || ID>=10000)
            check=false;
        if (check) {
            this.ID = ID;
            return 1;
        }
        else {
            return 0;
        }

    }



    public int GetID() {
        return ID;
    }


    public String GetName() {
        return this.Name;
    }
    public int GetAge() {
        return this.Age;
    }
    public String GetGender(){
        if(this.gender == Gender.MALE){
            return "male";
        }
        else {
            return "female";
        }
    }
    public String GetLastName() {
        return this.LastName;
    }
    public String GetNationalCode() {
        return this.National_Code;
    }
    public String GetPhoneNumber() {
        return this.PhoneNumber;
    }


    @Override
    public String toString(){
        return this.GetName() + Commons.Commons + this.GetLastName() + Commons.Commons +
                this.GetNationalCode() + Commons.Commons + this.GetAge() + Commons.Commons + this.GetGender() +
                Commons.Commons + this.GetPhoneNumber() + Commons.Commons + this.GetID();
    }






}
