package Interfaces;

import java.util.Date;

/**
 * Created by NIK1114 on 2016-09-15.
 */
public interface InsertNewUser {

  boolean InsertNewUser(String fname, String lname, int memberlevel, String email, int phone , String username , String passw , Date membersince  );
  /**
   * TO use the: boolean InsertNewUser(String fname, String lname, int memberlevel, String email, int phone , String username , String passw , Date membersince  )
   *
   * INSERT INTO bikeuser (fname, lname, memberlevel, email, phone , username , passw , membersince)
   * VALUES (String fname, String lname, int memberlevel, String email, int phone , String username , String passw , Date membersince);
   *
   **/


}
