package team.sb.authorizationserver.domain.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.image.entity.Image;
import team.sb.authorizationserver.domain.image.repository.ImageRepository;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.global.properties.S3Util;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final S3Util s3Util;
    private final ImageRepository imageRepository;

    public Image addImage(MultipartFile reqImage) {
        String imagePath = s3Util.upload(reqImage);

        return imageRepository.save(
                Image.builder()
                        .imagePath(imagePath)
                        .imageUrl(s3Util.getFileUrl(imagePath))
                        .build()
        );
    }

}
