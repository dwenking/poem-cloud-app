package com.ecnu.poemcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBasis {

    public int id_question;

    public String title;

    // (0是填空题，1是单选题，2是多选题)
    public int question_type;

    public int id_theme;

    public int point;
}
