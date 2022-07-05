package command.center.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ZenotiAuth { 

   private Integer CultureId;
   private Boolean CanAccessSignalR;
   private Boolean IsAuthenticated;
   @JsonProperty(".refresh")
   private String refresh;
   private String OrganizationId;
   private String token_type;
   private Integer CurrencyId;
   @JsonProperty(".issued")
   private String issued;
   private Boolean is_tandc_accepted;
   private String TokenId;
   private Boolean CanRenewToken;
   private Integer expires_in;
   private String UserName;
   private String ZoneId;
   @JsonProperty(".expires")
   private String expires;
   private String access_token;
   private String refresh_token;
   private String CenterName;
   private String RoleName;
   private String AppId;
   private String UserId;
   private Integer TimeZoneId;
   private String CenterId;
   private String LoginUserType;
   private String AccountName;

}


