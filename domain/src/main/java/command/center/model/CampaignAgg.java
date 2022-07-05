package command.center.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CampaignAgg {
    public String campaignName;
    public Long leadCount;
    public String id;
    public Double revenueSpend;
    public Long appointmentBooked;
}
