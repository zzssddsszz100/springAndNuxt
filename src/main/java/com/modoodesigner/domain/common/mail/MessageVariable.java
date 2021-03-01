package com.modoodesigner.domain.common.mail;

import lombok.*;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class MessageVariable {
    private final String key;
    private final Object value;

    public static MessageVariable from(String key, Object value) {
        return new MessageVariable(key, value);
    }

}
