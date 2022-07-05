package command.center.service;

import command.center.model.ZenotiAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    RestTemplate restTemplate;

    private ZenotiAuth zenotiAuth;

    @PostConstruct
    public void init(){
       this.generateZenotiAuth();
    }

    public void generateZenotiAuth() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username","apiuser");
        map.add("password","Welcome@123");
        map.add("grant_type","password");
        map.add("clientid","revivemd");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<ZenotiAuth> response =  restTemplate.exchange("https://api.zenoti.com/Token",
                HttpMethod.POST, entity, ZenotiAuth.class);
        zenotiAuth = response.getBody();
    }

    public ZenotiAuth getZenotiAuthDetails(){
        return zenotiAuth;
    }

}
