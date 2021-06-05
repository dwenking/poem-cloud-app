package com.ecnu.poemcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Common_sense {

    public int id_common_sense;

    public int id_theme;

    public String title;

    public String text;

    public String simple_description;

}
