package com.zzz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by on 2017/12/22.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailVo implements Serializable {

    private static final long serialVersionUID = 7731173442605232301L;

    private String from;
    private String replyTo;
    private List<String> to;
    private Date sentDate;
    private String subject;
    private String text;

}
