package org.api.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.api.events.models.Presentation;
import org.api.events.models.Receiving;
import org.api.events.models.Relative;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRelatives {
    List<Relative> realative;
    List<Presentation> presentation;
    List<Receiving> receiving;
   // List<Relative>

}
