package team.sb.resourceserver.domain.image.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.sb.resourceserver.domain.user.entity.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imagePath;
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Image(String imagePath, String imageUrl) {
        this.imagePath = imagePath;
        this.imageUrl = imageUrl;
    }

}
