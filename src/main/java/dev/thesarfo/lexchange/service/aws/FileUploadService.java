package dev.thesarfo.lexchange.service.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * FileUploadService is the medium between our application and Amazon Web Service
 * Users might update a profile photo, or even add photos of books when creating them, this is the
 * medium through which those photos get saved in the cloud
 *
 * @author Ernest Sarfo
 * @since 1.0
 */

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final AmazonS3 amazonS3;


    @Value("${aws.s3.bucketName}")
    private String bucketName;

    /**
     *
     * @param key the access key to an aws s3 bucket
     * @param file the file to be uploaded, this should explicitly be an image type
     * @throws IOException if the file is of an invalid type, or cannot be parsed
     */
    public void uploadFile(String key, MultipartFile file) throws IOException{
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), null);
        amazonS3.putObject(putObjectRequest);
    }
}
