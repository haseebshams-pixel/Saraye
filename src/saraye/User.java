package saraye;

public abstract class User {
    String username;
    int type;
    String password;

    public String get_username(){
        return this.username;
    }

    public int get_usertype(){
        return this.type;
    }

    public boolean login(String username, String password, int type){
        this.username = username;
        this.password = password;
        this.type = type;

        Database db = new Database();
        boolean output = db.login(this.username, this.password, this.type);
        return output;
    }
}
