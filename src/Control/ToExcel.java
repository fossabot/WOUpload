/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


import Model.DataBase;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;

public class ToExcel {

    public static int total_rows = 12000;
    public static int total_inserted = 0;
    
    public static void insertBD(JTable table, List list, String site){
         
        list.removeAll(); 
        list.add(emptyRows(table)+ "");
        total_inserted = 0;
         int r = 0;
         
         while(r!=total_rows){
               try{
                     String id = table.getValueAt(r, 0).toString();
                     String mod = table.getValueAt(r, 1).toString();
                   
                     if(id.equals("")| (id ==  null)){
                          //list.add("Cant Insert : NULL" + id);
                     }else{
                            String insertWO,insertMO;
                            if(site.equals("CRC")){
                                DataBase db = new DataBase("CR");
                                insertWO = "INSERT INTO wo_status VALUES ('"+ id +"','0-Created',current_timestamp,'Planning',1)";
                                Statement st = db.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                st.executeUpdate(insertWO);

                                insertMO = "INSERT INTO wo_status_mod VALUES ('"+ id +"','"+ mod +"','',current_timestamp,'Planning')";
                                Statement st2 = db.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                st.executeUpdate(insertMO);

                                db.disconnect();
                                list.add("-> Orden Insertada: " + id);
                                total_inserted = total_inserted + 1;
                                
                                    }else{
                                
                                            DataBase db = new DataBase("US");
                                         
                                            insertMO = "INSERT INTO wo_status_mod VALUES ('"+ id +"','"+ mod +"','',current_timestamp,'Planning')";
                                            Statement st2 = db.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            st2.executeUpdate(insertMO);
                                            
                                            insertWO = "INSERT INTO wo_stat VALUES ('"+ id +"','0-Created',current_timestamp,'Planning'1)";
                                            Statement st = db.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            st.executeUpdate(insertWO);
                                            db.disconnect();
                                            list.add("-> Order Inserted: " + id);
                                            total_inserted = total_inserted + 1;
                            }    
                        }
                } catch(Exception e){
                    list.add(e.toString());
                   }
             r++;
         
         }   
         list.add("Total insertados:" + total_inserted);
    }
  
    public static void changeBD(JTable table, List list, String site,String state){
         
        list.removeAll();     
         int r = 0;
         
         while(r!=total_rows){
               try{
                     String id = table.getValueAt(r, 0).toString();
                    
                   
                     if(id.equals("")| (id ==  null)){
                          //list.add("Cant Insert : NULL" + id);
                     }else{
                            String insertWO;
                            if(site.equals("CRC")){
                                DataBase db = new DataBase("CR");
                                insertWO = "INSERT INTO wo_status VALUES ('"+ id +"','"+state+"',current_timestamp,'Planning',1)";
                                Statement st = db.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                st.executeUpdate(insertWO);

                       
                                db.disconnect();
                                list.add("-> Orden : " + id + " a estado: " + state );
                                
                                    }else{
                                      
                                            DataBase db = new DataBase("US");
                                            insertWO = "INSERT INTO wo_stat VALUES ('"+ id +"','"+state+"',current_timestamp,'Planning',1)";
                                            Statement st = db.connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                            st.executeUpdate(insertWO);
                                             list.add("-> Order : " + id + " to state: " + state );
                                            db.disconnect();
                                           
                                
                            }    
                        }
                } catch(Exception e){
                    
                   }
             r++;
         
         }    
    }
  
  public static int emptyRows(JTable table) {
    int emptyRows = 0;
    rowSearch: for (int row = 0; row < table.getRowCount(); row++) { //Iterate through all the rows
        for (int col = 0; col < table.getColumnCount(); col++) { //Iterate through all the columns in the row
            if (table.getValueAt(row, col) != null) { //Check if the box is empty
                continue rowSearch; //If the value is not null, the row contains stuff so go onto the next row
            }
        }
        emptyRows++; //Conditional never evaluated to true so none of the columns in the row contained anything
    }
    return emptyRows;
}
}
