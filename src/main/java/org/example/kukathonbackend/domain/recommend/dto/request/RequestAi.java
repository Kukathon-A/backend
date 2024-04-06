package org.example.kukathonbackend.domain.recommend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.kukathonbackend.domain.tasklist.domain.TagEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAi {

    TagEnum tag;

}
