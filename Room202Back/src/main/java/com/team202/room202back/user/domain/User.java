package com.team202.room202back.user.domain;


import com.team202.room202back.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Entity
public class User extends BaseEntity {

    private String name;

    private LocalDate birthday;

    private String nickname;

}
