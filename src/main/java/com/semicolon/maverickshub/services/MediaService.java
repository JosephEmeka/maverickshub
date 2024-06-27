package com.semicolon.maverickshub.services;


import com.github.fge.jsonpatch.JsonPatch;
import com.semicolon.maverickshub.dtos.requests.UploadMediaRequest;
import com.semicolon.maverickshub.dtos.response.MediaResponse;
import com.semicolon.maverickshub.dtos.response.UpdateMediaResponse;
import com.semicolon.maverickshub.dtos.response.UploadMediaResponse;
import com.semicolon.maverickshub.models.Media;

import java.util.List;

public interface MediaService  {
    UploadMediaResponse upload(UploadMediaRequest request);

    UpdateMediaResponse update(Long id, JsonPatch request);

    Media getMediaBy(Long id);

    List<MediaResponse> getMediaFor(Long userId);


}
