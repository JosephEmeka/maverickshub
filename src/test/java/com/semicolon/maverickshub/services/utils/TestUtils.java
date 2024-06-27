package com.semicolon.maverickshub.services.utils;

import com.semicolon.maverickshub.dtos.requests.UploadMediaRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.semicolon.maverickshub.models.Category.ACTION;

public class TestUtils {
    public static final String TEST_IMAGE_LOCATION = "C:\\Users\\DELL\\Documents\\GitHub\\maverickshub\\src\\main\\resources\\static\\img.png";
    public static final String TEST_VIDEO_LOCATION = "C:\\Users\\DELL\\Desktop\\Semicolon\\vid.mp4";

    public static UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest uploadRequest = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("media",inputStream);
        uploadRequest.setMediaFile(file);
        uploadRequest.setUserId(201L);
        uploadRequest.setCategory(ACTION);
        return uploadRequest;
    }
}
