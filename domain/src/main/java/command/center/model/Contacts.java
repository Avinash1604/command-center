package command.center.model;

import lombok.Data;

import java.util.List;

@Data
public class Contacts { 

   private String date_updated;
   private String date_created;
   private List<IntegrationLinks> integration_links;
   private List<Phones> phones;
   private String title;
   private String display_name;
   private String created_by;
   private List<Emails> emails;
   private String organization_id;
   private String updated_by;
   private String name;
   private String id;
   private String lead_id;

}