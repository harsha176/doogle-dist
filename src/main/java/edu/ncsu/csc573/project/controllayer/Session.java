/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc573.project.controllayer;

/**
 *
 * @author krishna
 */
public class Session {
    private String username;
    private static Session session = null;
    private Session(String username) {
        this.username = username;
    }

    public static Session createInstance(String username) {
        if(session == null) {
            session = new Session(username);
        } else if( session != null && !session.getUsername().equals(username)) {
            session.setUsername(username);
        }
        return session;
    }
    
    public static Session getInstance() throws Exception{
        if(session != null) {
            return session;
        } else {
            throw new Exception("User not logged in");
        }
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
      
}
