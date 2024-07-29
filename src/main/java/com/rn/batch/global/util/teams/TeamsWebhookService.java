package com.rn.batch.global.util.teams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamsWebhookService {

    private final TeamsWebApiService teamsWebApiService;

    public void sendTeamsMessage(String title, String message, String url) {
        TeamsWebhookRequestDto.WebhookBody webhookBody = TeamsWebhookRequestDto.WebhookBody.builder()
                .type("TextBlock")
                .title(title)
                .text(message)
                .build();

        TeamsWebhookRequestDto.WebhookContent webhookContent = new TeamsWebhookRequestDto.WebhookContent();
        webhookContent.getBody().add(webhookBody);

        TeamsWebhookRequestDto.Attachment attachment = new TeamsWebhookRequestDto.Attachment();
        attachment.setContent(webhookContent);

        TeamsWebhookRequestDto requestDto = new TeamsWebhookRequestDto();
        requestDto.getAttachments().add(attachment);

        teamsWebApiService.requestPost(requestDto, String.class, url);
    }
}
