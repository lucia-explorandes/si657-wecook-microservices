package pe.edu.upc.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Cookbook {
    private Long id;
    private String name;
}
