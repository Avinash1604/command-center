package command.center.controller;

import command.center.model.CampaignDetail;
import command.center.model.CampaignStatus;
import command.center.model.RevenueSpentOnChannel;
import command.center.service.ClientServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin("*")
public class LeadController {

    @Autowired
    ClientServiceService clientServiceService;

    @GetMapping()
    public Map getLeads(@RequestParam("leadId") Optional<String> leadId) {
       return clientServiceService.getLeads(leadId);
    }

    @GetMapping("campaigns/agg")
    public Map getLeadsAggregate() {
        return clientServiceService.getLeadsAggregate();
    }

    @PostMapping()
    public Map saveLeads(@RequestBody Map map) {
        return clientServiceService.saveLead(map);
    }

    @GetMapping("/leadsFilter")
    public Map getLeadFilter(@RequestParam("campaignName") Optional<String> campaignName, @RequestParam("startDate") Optional<String> startDate, @RequestParam("endDate") Optional<String> endDate) {
        campaignName = getValueOrEmpty(campaignName);
        startDate = getValueOrEmpty(startDate);
        endDate = getValueOrEmpty(endDate);
        return clientServiceService.getLeadFilter(campaignName, startDate, endDate);
    }

    @GetMapping("/campaigns")
    public Map getLeadCampaign() {
        return clientServiceService.getLeadCampaigns();
    }

    @PostMapping("revenue-spent-on-campaign")
    public RevenueSpentOnChannel saveRevenueSpentOnChannel(@RequestBody RevenueSpentOnChannel channel) {
        return clientServiceService.saveRevenueSpentOnChannel(channel);
    }

    @GetMapping("/revenue-spent-on-campaign")
    public List<RevenueSpentOnChannel> getRevenueSpentOnChannel(@RequestParam("status") Optional<CampaignStatus> status) {
        return clientServiceService.getRevenueSpentOnChannel(status);
    }

    @PostMapping("campaign")
    public CampaignDetail saveCampaign(@RequestBody CampaignDetail campaign) {
        return clientServiceService.saveCampaign(campaign);
    }

    @GetMapping("/campaign")
    public List<CampaignDetail> getCampaign(@RequestParam("status") Optional<CampaignStatus> status) {
        return clientServiceService.getCampaign(status);
    }

    private Optional<String> getValueOrEmpty(Optional<String> str) {
        if (str.isPresent()) {
            if (str.get().isEmpty()) {
                return Optional.empty();
            }
        }
        return str;
    }

}
