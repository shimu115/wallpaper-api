package com.shimu.wallpaper.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分辨率")
public class Resolution {

    @Schema(description = "分辨率宽度")
    private Integer width;
    @Schema(description = "分辨率高度")
    private Integer height;

    public String toString() {
        return String.format("%dx%d", width, height);
    }
}
