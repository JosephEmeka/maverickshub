package com.semicolon.maverickshub.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.semicolon.maverickshub.dtos.requests.UploadMediaRequest;
import com.semicolon.maverickshub.dtos.response.MediaResponse;
import com.semicolon.maverickshub.dtos.response.UpdateMediaResponse;
import com.semicolon.maverickshub.dtos.response.UploadMediaResponse;
import com.semicolon.maverickshub.exceptions.MediaNotFoundException;
import com.semicolon.maverickshub.exceptions.MediaUpdateFailedException;
import com.semicolon.maverickshub.exceptions.MediaUploadFailedException;
import com.semicolon.maverickshub.models.Media;
import com.semicolon.maverickshub.models.User;
import com.semicolon.maverickshub.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MavericksHubMediaService implements MediaService {

    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        User user =  userService.getById(request.getUserId());
        try{
            Uploader uploader = cloudinary.uploader();
        Map<? , ?> response = uploader.upload(request.getMediaFile().getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));
        String url = response.get("url").toString();
        Media media = modelMapper.map(request, Media.class);
        media.setUrl(url);
        media.setUploader(user);
        media = mediaRepository.save(media);
        return modelMapper.map(media, UploadMediaResponse.class);
    }
        catch(IOException exception) {
        throw new MediaUploadFailedException("media upload failed");
        }
    }
    @Override
    public UpdateMediaResponse update(Long mediaId, JsonPatch jsonPatch) {
        //1. get target Object
       try{ Media media = getMediaBy(mediaId);
        //2. convert object from above to JsonNode (use ObjectMapper)
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mediaNode = objectMapper.convertValue(media, JsonNode.class);
        //3. apply jsonPatch to mediaNode
        mediaNode =jsonPatch.apply(mediaNode);
        //4. convert mediaNode to media object
        media = objectMapper.convertValue(mediaNode, Media.class);
        mediaRepository.save(media);
        return modelMapper.map(media, UpdateMediaResponse.class);
       }
       catch (JsonPatchException exception){
           throw new MediaUpdateFailedException("media upload failed");
       }
    }


    @Override
    public Media getMediaBy(Long id){
        return mediaRepository.findById(id)
                .orElseThrow(()-> new MediaNotFoundException("media not found"));
    }

    @Override
    public List<MediaResponse> getMediaFor(Long userId){
        List <Media> media = mediaRepository.findAllMediaFor(userId);
        return media.stream()
                .map(mediaItem -> modelMapper.map(mediaItem, MediaResponse.class))
                        .toList();
        }
    }




