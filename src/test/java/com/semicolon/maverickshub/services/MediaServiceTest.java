package com.semicolon.maverickshub.services;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.semicolon.maverickshub.dtos.requests.UploadMediaRequest;
import com.semicolon.maverickshub.dtos.response.MediaResponse;
import com.semicolon.maverickshub.dtos.response.UpdateMediaResponse;
import com.semicolon.maverickshub.dtos.response.UploadMediaResponse;
import com.semicolon.maverickshub.models.Category;
import com.semicolon.maverickshub.models.Media;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.semicolon.maverickshub.models.Category.ACTION;
import static com.semicolon.maverickshub.services.utils.TestUtils.TEST_VIDEO_LOCATION;
import static com.semicolon.maverickshub.services.utils.TestUtils.buildUploadMediaRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/data.sql"})
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;

//    @Test
//    public void test_uploadMedia() {
//        String fileLocation = "C:\\Users\\DELL\\Desktop\\Semicolon\\vid.mp4";
//        Path path = Paths.get(fileLocation);
//        UploadMediaRequest request = new UploadMediaRequest();
//        try {
//            var inputStream = Files.newInputStream(path);
//            {
//                MultipartFile file = new MockMultipartFile("thanos profile pic", inputStream);
//                request.setMediaFile(file);
//                UploadMediaResponse response = mediaService.upload(request);
//                assertThat(response).isNotNull();
//                assertThat(response.getUrl()).isNotNull();
//
//            }
//        } catch (IOException e) {
//            assertThat(e).isNotNull();
//        }
//    }

    @Test
    public void uploadMediaTest(){
        Path path = Paths.get(TEST_VIDEO_LOCATION);
        try(var inputStream =Files.newInputStream(path)){
            UploadMediaRequest request  = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(request);
            log.info("response ->{}", response);
            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        }
        catch (IOException exception){
            assertThat(exception).isNotNull();
        }
    }



    @Test
    public void test_getMediaById(){
        Media media = mediaService.getMediaBy(100L);
        log.info("found content -->{}", media);
        assertThat(media).isNotNull();

    }
    @Test
    @DisplayName("test update media files")
public void updateMediaTest() throws JsonPointerException {
        Category category = mediaService.getMediaBy(103L).getCategory();
        assertThat(category).isNotEqualTo(ACTION);
        List<JsonPatchOperation> operations = List.of(
                new ReplaceOperation(new JsonPointer("/category"), new TextNode(ACTION.name()))
        );
        JsonPatch updateMediaRequest = new JsonPatch(operations);
        UpdateMediaResponse response = mediaService.update(103L, updateMediaRequest);
        assertThat(response).isNotNull();
        category = mediaService.getMediaBy(103L).getCategory();
        assertThat(category).isEqualTo(ACTION);
    }

    @Test
    public void getMediaForUserTest(){
        Long userId = 200L;
        List<MediaResponse> media = mediaService.getMediaFor(userId);
        assertThat(media).hasSize(3);
    }

}
