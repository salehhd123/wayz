package com.example.wayz.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeRange {
    private String rangeAsString;
    private LocalDateTime start;
    private LocalDateTime end;
}
