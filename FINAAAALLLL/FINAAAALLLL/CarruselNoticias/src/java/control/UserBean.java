/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.io.Serializable;

/**
 *
 * @author johanna
 */
public class UserBean implements Serializable {
    private String user;
    private String role;

    public UserBean(String user, String role){
        this.user = user;
        this.role = role;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return user;
    }


}
