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
   *
   * INSERT INTO bikeuser (fname, lname, memberlevel, email, phone , username , passw , membersince)
   VALUES ('Niklas', 'Karlsson', 0, 'cykeltur@gmail.com', 0703032191 , 'cykeltur' , AES_ENCRYPT('1234','tackforkaffet') , CURDATE());


   DELIMITER //
   CREATE FUNCTION insert_new_user(in_fname varchar(50),in_lname varchar(50),in_memberlevel varchar(50),in_email varchar(50),in_phone varchar(50),in_username varchar(50), in_passw varchar(50)) RETURNS smallint(6)
   BEGIN
   DECLARE pw VARBINARY(56);
   SET userNameAvalible = (SELECT username FROM bikeuser WHERE userName=in_username);
   if exists(SELECT * from bikeuser WHERE userName= tryusername AND pw=AES_ENCRYPT(trypassword,'tackforkaffet'))
   THEN
   RETURN 0;
   ELSE
   INSERT INTO bikeuser (fname, lname, memberlevel, email, phone , username , passw , membersince)
   VALUES (in_fname, in_lname, in_memberlevel, in_email, in_phone , in_username , AES_ENCRYPT(in_passw,'tackforkaffet') , CURDATE());
   RETURN 1;
   END IF;
   END//
   DELIMITER ;



   **/


}
