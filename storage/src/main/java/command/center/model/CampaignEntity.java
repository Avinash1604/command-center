package command.center.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("CampaignEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignEntity {
    @Id
    private String id;

    String name;
    Long leadCount;
    Double spend;

    @Builder.Default
    String status = CampaignStatus.ACTIVE.name();

}
