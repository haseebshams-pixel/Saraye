/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saraye;

/**
 *
 * @author Haseeb
 */
public abstract class User {
    String username;
    int type;
    String password;

    String get_ussername(){
        return this.username;
    }

    int get_usertype(){
        return this.type;
    }

    public boolean login(String username, String password,int type){
        this.username = username;
        this.password = password;
        this.type = type;
        Database db = new Database();
        boolean output = db.login(this.username, this.password,this.type);
        return output;
    }
}