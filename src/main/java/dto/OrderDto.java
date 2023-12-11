package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDto {
    private LocalDateTime created;
    private String type;
    private String model;
    private String problem;
    private Long sum;
}
