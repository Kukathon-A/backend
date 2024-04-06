package org.example.kukathonbackend.domain.recommend.presentation;


import lombok.RequiredArgsConstructor;
import org.example.kukathonbackend.domain.recommend.application.Recommend;
import org.example.kukathonbackend.domain.recommend.dto.request.RequestAi;
import org.example.kukathonbackend.domain.recommend.dto.response.ResponseAi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommendController")
@RequiredArgsConstructor
public class RecommendController {

    @PostMapping("/goal")
    public ResponseEntity<ResponseAi> recommned(@RequestBody RequestAi requestAi) {

        ResponseAi responseAi = Recommend.SelfDevelopmentRecommend(requestAi);

        return ResponseEntity.ok(responseAi);
    }


}
