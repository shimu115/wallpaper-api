package com.shimu.wallpaper.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resolution {
    private Integer width;
    private Integer height;

    public String toString() {
        return String.format("%dx%d", width, height);
    }
}
