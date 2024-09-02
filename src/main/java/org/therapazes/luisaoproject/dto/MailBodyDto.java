package org.therapazes.luisaoproject.dto;

import lombok.Builder;

@Builder
public record MailBodyDto(String to, String subject, String text, boolean isHtml) {
}
