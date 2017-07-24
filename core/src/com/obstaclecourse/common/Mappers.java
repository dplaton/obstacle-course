package com.obstaclecourse.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.obstaclecourse.component.BoundsComponent;
import com.obstaclecourse.component.MovementComponent;
import com.obstaclecourse.component.PositionComponent;

/**
 * Created by platon on 21/07/2017.
 */

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS_MAPPER = ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT_MAPPER = ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION_MAPPER = ComponentMapper.getFor(PositionComponent.class);

}
