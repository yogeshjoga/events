package org.api.events.service.relativeservice;

import org.api.events.models.Relative;

import java.util.List;

public interface IRelativeService {
    List<Relative> getAllRelatives();
    Relative saveRelative(Relative relative);
}
