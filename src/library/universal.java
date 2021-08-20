package library;

public class universal {
    static int id;
    static String role;

    void setuser(int a, String role){
        id = a;
        this.role = role;
    }

    int getid(){
        return id;
    }

    String getrole(){
        return role;
    }
}
