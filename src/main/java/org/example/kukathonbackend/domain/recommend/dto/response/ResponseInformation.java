package org.example.kukathonbackend.domain.recommend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.coyote.Response;
import org.example.kukathonbackend.domain.recommend.presentation.InformationEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInformation {
    String content;



    public static ResponseInformation of(InformationEnum informationEnum) {
        return new ResponseInformation(informationEnum.getInformation());
    }
}
