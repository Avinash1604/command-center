package command.center.service;

import command.center.model.*;
import command.center.repository.CampaignRepository;
import command.center.repository.RevenueSpentOnChannelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DataStorageService {

  @Autowired
  RevenueSpentOnChannelRepository revenueSpentOnChannelRepository;

  @Autowired
  CampaignRepository campaignRepository;

  public RevenueSpentOnChannel saveChannelDetail(RevenueSpentOnChannel revenueSpentOnChannel) {
    RevenueSpentOnChannelEntity entity = new RevenueSpentOnChannelEntity();
    entity.setChannelName(revenueSpentOnChannel.getChannelName());
    entity.setLeadCount(revenueSpentOnChannel.getLeadCount());
    entity.setSpend(revenueSpentOnChannel.getSpend());
    entity.setStatus(revenueSpentOnChannel.getStatus().name());
    return mapToDto(revenueSpentOnChannelRepository.save(entity));
  }

  public List<RevenueSpentOnChannel> getRevenueSpentOnChannel(Optional<CampaignStatus> status) {
    String strStatus = CampaignStatus.ACTIVE.name();
    if (status.isPresent()){
      strStatus = status.get().name();
    }
    return this.revenueSpentOnChannelRepository.findAllByStatus(strStatus).stream().map(
            data -> mapToDto(data)
    ).collect(Collectors.toList());
  }

  public CampaignDetail saveCampaign(CampaignDetail campaignDetails) {
    CampaignEntity entity = new CampaignEntity();
    entity.setName(campaignDetails.getName());
    entity.setLeadCount(campaignDetails.getLeadCount());
    entity.setSpend(campaignDetails.getSpend());
    entity.setStatus(campaignDetails.getStatus().name());
    return mapToCampaignDto(this.campaignRepository.save(entity));
  }

  public List<CampaignDetail> getCampaign(Optional<CampaignStatus> status) {
    String strStatus = CampaignStatus.ACTIVE.name();
    if (status.isPresent()){
      strStatus = status.get().name();
    }
    return this.campaignRepository.findAllByStatus(strStatus).stream().map(
            data -> mapToCampaignDto(data)
    ).collect(Collectors.toList());
  }

  private RevenueSpentOnChannel mapToDto(RevenueSpentOnChannelEntity entity) {
    RevenueSpentOnChannel channel = new RevenueSpentOnChannel();
    channel.setChannelName(entity.getChannelName());
    channel.setSpend(entity.getSpend());
    channel.setStatus(CampaignStatus.valueOf(entity.getStatus()));
    channel.setLeadCount(entity.getLeadCount());
    channel.setId(entity.getId());
    return channel;
  }

  private CampaignDetail mapToCampaignDto(CampaignEntity entity) {
    CampaignDetail detail = new CampaignDetail();
    detail.setName(entity.getName());
    detail.setSpend(entity.getSpend());
    detail.setStatus(CampaignStatus.valueOf(entity.getStatus()));
    detail.setLeadCount(entity.getLeadCount());
    detail.setId(entity.getId());
    return detail;
  }

}
