package mappers.dto;

public class dtoEmployee {

    private int id;
    private String name;
    private String adress;
    private String email;
    private int phone;
    private int cc;
    private String roleId;
    private String pwd;

    public dtoEmployee(int id,String name,String adress,String email,int phone,int cc,String roleId,String pwd){
        this.id=id;
        this.name=name;
        this.adress=adress;
        this.email=email;
        this.phone=phone;
        this.cc=cc;
        this.roleId=roleId;
        this.pwd=pwd;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public String getAdress() {return adress;}

    public String getEmail() {return email;}

    public int getPhone() {return phone;}

    public int getCc() {return cc;}

    public String getRoleId() {return roleId;}

    public String getPwd() {return pwd;}
}
