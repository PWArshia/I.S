package Common;

import java.lang.ref.SoftReference;
import java.time.LocalDate;

public class AdminAction {

    private int code;
    private LocalDate date;
    private String title;
    private String description;
    private String Name;
    private String N_Code;


//    --------------------------------------------------->Cons

    public AdminAction() {}

    public AdminAction(String title, String description, LocalDate date , Admin admin , int code) {
        this.SetTitle(title);
        this.SetDescription(description);
        this.SetDate(date);
        this.Name = admin.GetName();
        this.N_Code= admin.GetNationalCode();
        this.SetCode(code);
    }


    public AdminAction(String s) {

        String Split[]=s.split(Commons.Commons);
        this.SetCode(Integer.parseInt(Split[0]));
        this.SetName(Split[1]);
        this.SetNationalCode(Split[2]);
        this.SetTitle(Split[3]);
        this.SetDescription(Split[4]);
        this.SetDate(LocalDate.parse(Split[5]));


    }

    @Override
    public String toString() {
        return this.GetCode()+"&"+this.GetName()+"&"+this.GetN_Code()+"&"+this.GetTitle()+"&"+this.GetDescription()+"&"+this.getDate();
    }

//    ----------------------------------------------------->Setter
    public boolean SetTitle(String title) {
        if (title.length()>10){
            return false;
        }
        this.title = title;
        return true;
    }
    public boolean SetDescription(String description) {
        this.description = description;
        return true;
    }

    public boolean SetDate(LocalDate date) {
        if (date.compareTo(LocalDate.now()) != 0 ){
            return false;
        }
        this.date = date;
        return true;
    }
    public boolean SetNationalCode(String NCode) {
        this.N_Code = NCode;
        return true;
    }
    public boolean SetName(String Name) {
        this.Name = Name;
        return true;
    }
    public boolean SetCode(int Code) {
        if(Code <=0){
            return false;
        }
        this.code = Code;
        return true;
    }



//    ----------------------------------------------------------------->Getter

    public LocalDate getDate() {
        return this.date;
    }

    public String GetTitle() {
        return this.title;
    }
    public String GetDescription() {
        return this.description;
    }
    public String GetName() {
        return this.Name;
    }
    public String GetN_Code() {
        return this.N_Code;
    }
    public int GetCode() {
        return this.code;
    }




}
