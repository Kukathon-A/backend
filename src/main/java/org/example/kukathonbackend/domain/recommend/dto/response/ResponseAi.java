package org.example.kukathonbackend.domain.recommend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAi {
    List<String> content;

    public static ResponseAi setContent(String contentList) {
        List<String> content = Arrays.stream(contentList.split("\\|"))
                .map(String::trim)
                .collect(Collectors.toList());
        return new ResponseAi(content);
    }

}
