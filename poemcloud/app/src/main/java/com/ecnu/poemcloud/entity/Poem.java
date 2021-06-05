package com.ecnu.poemcloud.entity;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poem {

    public int id_poem;

    public int id_theme;

    public int id_author;

    public String period;

    public String title;

    public String paragraphs;

    public List<String> getSentenceList() {
        return Arrays.asList(this.paragraphs.split("\r\n"));
    }

}
