package com.zzz.controller.model;

import lombok.Data;

import java.util.List;

/**
 * Created by on 12/23 0023.
 */
@Data
public class MailForm {

    private String subject;
    private String text;
    private List<Integer> ids;

}
