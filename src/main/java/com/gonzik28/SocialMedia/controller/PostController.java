package com.gonzik28.SocialMedia.controller;

import com.gonzik28.SocialMedia.dto.*;
import com.gonzik28.SocialMedia.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@Tag(name = "Публикация", description = "Создан для работы с публикациями.")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Получение публикации по id")
    public ResponseEntity<ResponsePostDto> findById(@PathVariable
                                                    @Parameter(description = "Идентификатор публикации")
                                                    String id) {
        if (postService.findById(id) != null) {
            ResponsePostDto postDto = postService.findById(id);
            return ResponseEntity.ok(postDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/create/")
    @Operation(summary = "Создание публикации")
    public ResponseEntity<ResponsePostDto> create(@RequestBody
                                                  @Parameter(description = "Данные публикации")
                                                  RequestPostDto requestPostDto) {
        ResponsePostDto responsePostDto = postService.create(requestPostDto);
        return ResponseEntity.ok(responsePostDto);
    }

    @PostMapping(value = "/update/")
    @Operation(summary = "Изменение публикации")
    public ResponseEntity<ResponsePostDto> update(@RequestBody
                                                  @Parameter(description = "Изменненные данные публикации")
                                                  RequestPostDto requestPostDto) {
        ResponsePostDto responsePostDto = postService.update(requestPostDto);
        return ResponseEntity.ok(responsePostDto);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Удаление публикации по id")
    public ResponseEntity<Void> delete(@PathVariable
                                       @Parameter(description = "Идентификатор публикации")
                                       String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
