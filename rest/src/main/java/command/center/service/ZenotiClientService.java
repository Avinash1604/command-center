package command.center.service;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZenotiClientService {

    private RestTemplate restTemplate;
    private AuthService authService;

    public Map getAppointments() {
        Map centers = this.getCenters();
        List<String> listOfIds = ((List<Map>)centers.get("centers")).stream().map(data -> (String)data.get("id")).collect(Collectors.toList());

        List<Map> leadResponse = new ArrayList<>();
        listOfIds.stream().forEach(id -> {
            leadResponse.addAll(this.getAppointments(id));
        });
        Map finalResponse =  new HashMap();
        finalResponse.put("results", leadResponse);
        return finalResponse;
    }

    private List<Map> getAppointments(String centerId){
        String url = "https://api.zenoti.com/v1/appointments?center_id="+centerId+"&start_date=2019-09-15&end_date=2019-09-22";
        HttpHeaders headers = this.getHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<Map>> response =  restTemplate.exchange( url,
                HttpMethod.GET, entity,  new ParameterizedTypeReference<List<Map>>() {
                });
        return response.getBody();
    }


    public Map getCenters() {
        String url = "https://api.zenoti.com/v1/centers?CatalogEnabled=true";
        HttpHeaders headers = this.getHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Map> response =  restTemplate.exchange( url,
                HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer "+this.authService.getZenotiAuthDetails().getAccess_token());
        headers.add("Accept", "application/json");
        return headers;
    }
}
