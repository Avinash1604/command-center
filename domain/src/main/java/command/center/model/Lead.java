package command.center.model;

import lombok.Data;

import java.util.List;

@Data
public class Lead {

   private String date_updated;
   private String date_created;
   private String name;
   private String id;
   private List<Contacts> contacts;

}
