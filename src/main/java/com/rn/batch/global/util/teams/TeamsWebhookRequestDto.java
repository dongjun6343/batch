package com.rn.batch.global.util.teams;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamsWebhookRequestDto {
    private String type = "message";
    private List<Attachment> attachments = new ArrayList<>();

    @Data
    public static class Attachment {
        private String contentType = "";
        private String contentUrl = "";
        private WebhookContent content;
    }

    @Data
    public static class WebhookContent {
        private List<WebhookBody> body = new ArrayList<>();
    }

    @Data
    @Builder
    public static class WebhookBody {
        private String type;
        private String title;
        private String text;
    }
}
