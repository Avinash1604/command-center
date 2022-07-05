package command.center.repository;


import command.center.model.CampaignStatus;
import command.center.model.RevenueSpentOnChannel;
import command.center.model.RevenueSpentOnChannelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevenueSpentOnChannelRepository extends MongoRepository<RevenueSpentOnChannelEntity, Integer> {

    List<RevenueSpentOnChannelEntity> findAllByStatus(String status);
}