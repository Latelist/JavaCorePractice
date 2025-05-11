package salad_leaf.spring_mvc_library.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import salad_leaf.spring_mvc_library.model.Role;
import salad_leaf.spring_mvc_library.model.User;
import salad_leaf.spring_mvc_library.repository.UserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SocialAppService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(SocialAppService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Делаем запрос к GitHub
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Получаем данные из GitHub
        String githubId = oAuth2User.getAttribute("id").toString();
        String username = oAuth2User.getAttribute("login");
        String email = oAuth2User.getAttribute("email");

        logger.info("GitHub login: {}", username);

        // Ищем пользователя в базе
        User user = userRepository.findByGithubId(githubId)
                .orElseGet(() -> {
                    // Если нет — создаём нового
                    User newUser = new User();
                    newUser.setGithubId(githubId);
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setRole(Role.USER);
                    return userRepository.save(newUser);
                });

        // Возвращаем пользователя с его ролью
        return new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())),
                oAuth2User.getAttributes(),
                "login" // имя атрибута, которое будет использоваться как имя пользователя
        );
    }
}
