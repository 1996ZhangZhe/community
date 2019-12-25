package life.community.community.controller;

import life.community.community.dto.AccessTokenDTO;
import life.community.community.dto.GithubUser;
import life.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.Client_id}")
    private  String Client_id;
    @Value("${github.client_secret}")
    private  String Client_secret;
    @Value("${github.redirect_uri}")
    private  String Redirect_uri;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(Client_secret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println("id:"+user.getId() +"  name:"+user.getName());
        return "index";
    }
}
