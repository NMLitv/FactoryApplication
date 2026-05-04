package firstproject.factoryapplication.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleTaskDto {
    private Long id;
    private Long employeeId;
    private List<Long> taskIds;
}
