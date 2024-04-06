package org.example.kukathonbackend.domain.recommend.application;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.kukathonbackend.domain.recommend.dto.request.RequestAi;
import org.example.kukathonbackend.domain.recommend.dto.response.ResponseAi;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class Recommend {

    public static ResponseAi SelfDevelopmentRecommend(RequestAi requestAi) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String tag = switch(requestAi.getTag()){
            case READING -> "독서";
            case VIDEO_CONTENT -> "영상_컨텐츠";
            case WEBTOON -> "웹툰";
            case RELAXATION -> "휴식";
            case STUDYING -> "공부";
            case SNS -> "SNS";
            case OTHER -> "기타";
            default -> "기타"; // 기본값 설정
        };




        try {
            HttpPost request = new HttpPost("https://clovastudio.stream.ntruss.com/testapp/v1/chat-completions/HCX-003");

            StringEntity params = new StringEntity("{\n" +
                    "  \"messages\" : [ {\n" +
                    "    \"role\" : \"system\",\n" +
                    "    \"content\" : \"- 당신은 자기계발추천 전문가입니다.\\r\\n- 시간에 알맞은 자기계발활동을 추천합니다\\r\\n- 자기계발활동은 총 3개를 추천해야합니다.\\r\\n- 운동, 스트레칭같은 움직이는 활동은 추천하지 않습니다.\\r\\n- 지하철 및 버스같은 공공장소에서 할 수 있는 자기계발활동만을 추천합니다.\\n- 최대한 다양한 자기계발활동을 추천합니다.\\r\\n\\r\\ninput : 버스 및 지하철에서 30분 동안 할 수 있는 자기계발활동을 추천해줘\\r\\noutput : 자기계발서 독서하기 | 주식 공부하기 | 유튜브로 악기 공부하기\"\n" +
                    "  }, {\n" +
                    "    \"role\" : \"user\",\n" +
                    "    \"content\" : \"input : 나는 " + tag + "을 가장 좋아해. 버스 및 지하철에서 30분 동안 할 수 있는 자기계발활동을 추천해줘\\r\\noutput : \"\n" +
                    "  } ],\n" +
                    "  \"topP\" : 0.8,\n" +
                    "  \"topK\" : 0,\n" +
                    "  \"maxTokens\" : 256,\n" +
                    "  \"temperature\" : 0.8,\n" +
                    "  \"repeatPenalty\" : 5.02,\n" +
                    "  \"stopBefore\" : [ ],\n" +
                    "  \"includeAiFilters\" : true,\n" +
                    "  \"seed\" : 0\n" +
                    "}", ContentType.create("application/json", StandardCharsets.UTF_16));

            request.setEntity(params);

            request.addHeader("X-NCP-CLOVASTUDIO-API-KEY", "NTA0MjU2MWZlZTcxNDJiY63jYug0FDdswQfuPc3APjJSqVN89avhAcRqL1FVh6SB");
            request.addHeader("X-NCP-APIGW-API-KEY", "eqXwFOLTnhQPnrdo8Iub3Y3ZCAUXFzbeolSBxPR1");
            request.addHeader("X-NCP-CLOVASTUDIO-REQUEST-ID", "f7fb56ea-5a39-4f4a-b91f-c72972b68274");

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JSONObject jsonResult = new JSONObject(result);
                JSONObject resultObject = jsonResult.getJSONObject("result");
                JSONObject messageObject = resultObject.getJSONObject("message");
                String content = messageObject.getString("content");
                System.out.println(content);

                return ResponseAi.setContent(content);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}

