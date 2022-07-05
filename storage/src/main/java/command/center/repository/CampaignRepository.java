package command.center.repository;


import command.center.model.CampaignEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends MongoRepository<CampaignEntity, Integer> {
    List<CampaignEntity> findAllByStatus(String status);
}