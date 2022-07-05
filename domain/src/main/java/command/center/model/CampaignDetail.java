package command.center.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDetail {
     public String name;
    public Long leadCount;
    public String id;
    public Double spend;
    @Builder.Default
    public CampaignStatus status = CampaignStatus.ACTIVE;
}
