package Interfaces;

import java.util.Date;

/**
 * Created by NIK1114 on 2016-09-15.
 */
public interface DeleteUser {

  boolean DeleteUser(String fname, String lname, int memberlevel, String email, int phone, String username, String passw, Date membersince);
  /**
   * TO use the: boolean DeleteUser(String fname, String lname, int memberlevel, String email, int phone , String username , String passw , Date membersince  )
   *
   * DELETE FROM bikeuser
   * WHERE fname = ? AND lname = ? AND memberlevel = ? AND email = ? AND phone =  ? AND username =  ? AND passw = ? AND membersince =  ? ;
   * 
   **/


}
