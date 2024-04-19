package gre.jb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmailDTO {
    private String from;
    private String to;
    private String subject;
    private String body;
    private long jobId;
}
