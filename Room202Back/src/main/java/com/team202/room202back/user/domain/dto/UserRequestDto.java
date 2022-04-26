package com.team202.room202back.user.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String name;

    private LocalDate birthday;

    private String nickname;

}
