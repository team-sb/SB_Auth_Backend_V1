package team.sb.resourceserver.domain.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.sb.resourceserver.domain.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RequestMapping("/api")
@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity<?> userInfo() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(principal.getUid());
        System.out.println(principal.getName());

        return ResponseEntity.ok(principal);
    }

}
