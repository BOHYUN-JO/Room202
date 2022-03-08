package com.team202.room202back.board.domain;


import com.team202.room202back.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Entity
public class Board extends BaseEntity {

    private String name;

}
