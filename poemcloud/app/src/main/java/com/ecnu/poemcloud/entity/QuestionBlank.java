package com.ecnu.poemcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBlank extends Question {

    public int id_question;

    public String text;

    public String answer;


}
