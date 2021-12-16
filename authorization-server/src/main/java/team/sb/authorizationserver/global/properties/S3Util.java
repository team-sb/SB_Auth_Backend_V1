package team.sb.authorizationserver.global.properties;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.global.config.S3Config;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Util {

    private final S3Config s3Config;
    private final AmazonS3Client amazonS3Client;

    public String upload(MultipartFile image) {
        String fileName = "nms/" + UUID.randomUUID() + image.getOriginalFilename();

        try {
            amazonS3Client.putObject(new PutObjectRequest(s3Config.getBucket(), fileName, image.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public void removeFile(String imagePath) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(s3Config.getBucket(), imagePath));
    }

    public String getFileUrl(String fileName) {
        return amazonS3Client.getUrl(s3Config.getBucket(), fileName).toString();
    }

}
