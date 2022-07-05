package command.center.service;


import command.center.model.CampaignAgg;
import command.center.model.CampaignDetail;
import command.center.model.CampaignStatus;
import command.center.model.RevenueSpentOnChannel;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceService {


    private RestTemplate restTemplate;
    private DataStorageService dataStorageService;

    public Map getLeads(Optional<String> leadId){
        String url = "https://api.close.com/api/v1/lead/?_fields=id,name,contacts,date_created,date_updated";
        if (leadId.isPresent()){
            url = "https://api.close.com/api/v1/lead/"+leadId.get()+"/?_fields=id,name,contacts,date_created,date_updated";
        }
        HttpHeaders headers = this.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Map> response =  restTemplate.exchange(url,
                HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }

    public Map saveLead(Map map) {
        return map ;
    }

    public Map getLeadByTimeFrame(String timeFrame) {

        HttpHeaders headers = this.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(JsonUtils.leadByTimeCount(timeFrame), headers);

        ResponseEntity<Map> response =  restTemplate.exchange("https://api.close.com/api/v1/data/search/",
                HttpMethod.POST, entity, Map.class);
        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic YXBpXzF1SEtLdEo3aFlLQWRUMEQ2SVNzSmIuMXdvWnZteTNVOEU3VzQ1ejlvTDFRTzo=");
        headers.add("Accept", "application/json");
        headers.add("Cookie", "_csrf_token=afa33b596d9eab787271b7ea4ec6fc6732cdd003");
        return headers;
    }

    public Map getDashBoardData() {
        Map month =  this.getLeadByTimeFrame("this-month");
        Map byWeek =  this.getLeadByTimeFrame("this-week");
        Map today =  this.getLeadByTimeFrame("today");
        Map yesterday =  this.getLeadByTimeFrame("yesterday");
        Map lastWeek =  this.getLeadByTimeFrame("last-week");
        Map lastMonth =  this.getLeadByTimeFrame("last-month");

        Map data = new HashMap();
        data.put("totalLeadsByMonth", month);
        data.put("totalLeadsToday", today);
        data.put("totalLeadsInWeek", byWeek);
        data.put("totalLeadsLastMonth", lastMonth);
        data.put("totalLeadsYesterday", yesterday);
        data.put("totalLeadsLastWeek", lastWeek);
      return data;
    }

    public Map getLeadFilter(Optional<String> campaignName, Optional<String> startDate, Optional<String> endDate) {
        HttpHeaders headers = this.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(JsonUtils.getLeadFilters(campaignName, startDate, endDate), headers);

        ResponseEntity<Map> response =  restTemplate.exchange("https://api.close.com/api/v1/data/search/",
                HttpMethod.POST, entity, Map.class);

        List<Map> responseData = (List<Map>) response.getBody().get("data");
        List<String> listOfIds = responseData.stream().map( data -> (String)data.get("id")).collect(Collectors.toList());
       List<Map> leadResponse = new ArrayList<>();
        listOfIds.stream().forEach(id -> {
            leadResponse.add(this.getLeads(Optional.of(id)));
        });

        Map finalResponse = new HashMap();
        finalResponse.put("results", leadResponse);
        return finalResponse;

    }

    public Map getLeadCampaigns() {
        List<Map> responseData =  (List<Map>)this.getLeads(Optional.empty()).get("data");
        Set<String> listOfIds = responseData.stream().map( data -> (String)data.get("name")).collect(Collectors.toSet());
       Map data = new HashMap();
       data.put("results", listOfIds);
       return data;
    }

    public RevenueSpentOnChannel saveRevenueSpentOnChannel(RevenueSpentOnChannel channel) {
        return dataStorageService.saveChannelDetail(channel);
    }

    public List<RevenueSpentOnChannel> getRevenueSpentOnChannel(Optional<CampaignStatus> status) {
        return dataStorageService.getRevenueSpentOnChannel(status);
    }

    public CampaignDetail saveCampaign(CampaignDetail campaign) {
        return dataStorageService.saveCampaign(campaign);
    }

    public List<CampaignDetail> getCampaign(Optional<CampaignStatus> status) {
        return dataStorageService.getCampaign(status);
    }

    public Map getLeadsAggregate() {
        List<Map> responseData =  (List<Map>)this. getAllLeads(0).get("data");
       // Set<String> listOfIds = responseData.stream().map( data -> (String)data.get("name")).collect(Collectors.toSet());
        Map<Object, List<Map>> groupedCampaignData =
                responseData.stream().collect(Collectors.groupingBy(res -> res.get("name")));
       List<CampaignAgg> aggResponse = groupedCampaignData.entrySet().stream().map(res -> {
           CampaignAgg campaignAgg = new CampaignAgg();
           campaignAgg.setCampaignName(res.getKey().toString());
           campaignAgg.setLeadCount(res.getValue().stream().count());
           campaignAgg.setAppointmentBooked(10L);
           campaignAgg.setRevenueSpend(1000.0);
           return campaignAgg;
       }).collect(Collectors.toList());

        Map data = new HashMap();
        data.put("results", aggResponse);
        return data;
    }

    public List<Map> getAllLeadData(){
        int count = 0;
        List<Map> response = new ArrayList();
        while (count < 19785) {
            response.addAll((List<Map>)this. getAllLeads(count).get("data"));
            count = count +200;
        }
        return response;
    }

    public Map getAllLeads(int skip){
        String url = "https://api.close.com/api/v1/lead/?_fields=id,name,contacts,date_created,date_updated";
        HttpHeaders headers = this.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Map> response =  restTemplate.exchange("https://api.close.com/api/v1/lead/?_fields=id,name,contacts,date_created,date_updated&_limit=200&_skip="+skip,
                HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }
}
