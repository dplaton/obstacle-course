package com.obstaclecourse.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.obstaclecourse.component.BoundsComponent;

/**
 * Created by platon on 21/07/2017.
 */

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS_MAPPER = ComponentMapper.getFor(BoundsComponent
                                                                                                               .class);

}
