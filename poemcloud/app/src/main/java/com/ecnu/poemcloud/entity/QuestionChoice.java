package com.ecnu.poemcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionChoice extends Question {

    public int id_question;

    public String text;

    public String answer;

    public boolean single_flag;

    public String option_a;

    public String option_b;

    public String option_c;

    public String option_d;

}
